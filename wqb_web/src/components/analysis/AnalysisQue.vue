<template>
  <div>
    <div style = "width: 49%;  float: left; border: dashed 1px; margin-top: 10px">
      <div id="mastery" style="width: 100%;height:600px; margin-top: 5px"></div>
    </div>

    <div style = "width: 49%;  float: right; border: dashed 1px; margin-top: 10px">
      <div id="level" style="width: 100%;height:600px; margin-top: 5px"></div>
    </div>

    <div style = "width: 49%;  float: left; border: dashed 1px; margin-top: 10px">
      <div id="course" style="width: 100%;height:600px; margin-top: 5px"></div>
    </div>

    <div style = "width: 49%;  float: right; border: dashed 1px; margin-top: 10px">
      <div id="review" style="width: 100%;height:600px; margin-top: 5px"></div>
    </div>

    <div style = "width: 100%; float: left; border: dashed 1px; margin-top: 10px">
      <div id="newData" style="width: 100%;height:600px; margin-top: 5px"></div>
    </div>

    <div style = "width: 100%; float: left; border: dashed 1px; margin-top: 10px">
      <div id="updateData" style="width: 100%;height:600px; margin-top: 5px"></div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
  name: "AnalysisQue",
  data() {
    return {
      hasData: this.$route.query.pushData,
      student: '',
      studentId: '',
      masteryData: [],
      levelData: [],
      courseData: [],
      reviewData: [],
      lastMonth: [],
      newData: [],
      updateData: [],
    }
  },
  methods: {
    init() {
      if (typeof this.hasData != "undefined") {
        console.log(this.hasData)
        this.studentId = this.hasData.sid
      } else {
        this.student = JSON.parse(sessionStorage.getItem('CurUser'))
        this.studentId = this.student.sid
      }
    },
    getMasteryChart() {
      this.$axios.get(this.$httpUrl+'/question/getMastery?id='+this.studentId).then(res=>res.data).then(res=>{
        for (let i = 0; i < res.data.length; i++) {
          this.masteryData[i] = {
            value: '',
            name: ''
          }
          this.masteryData[i].value = res.data[i].count
          switch (res.data[i].mastery) {
            case 0: this.masteryData[i].name = "完全不会"; break;
            case 1: this.masteryData[i].name = "略知一二"; break;
            case 2: this.masteryData[i].name = "已然掌握"; break;
          }
        }
        // console.log(this.data)
        var myChart = this.$echarts.init(document.getElementById('mastery'));

        // 指定图表的配置项和数据
        var option = {
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '5%',
            left: '5%'
          },
          title: {
            // 设置饼图标题，位置设为顶部居中
            text: "错题掌握程度",
            left: "center"
          },
          series: [
            {
              // name: '错题掌握程度',
              type: 'pie',
              radius: ['30%', '55%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: true,
                fontWeight: 'bold',
                formatter: "{b} : {c} ({d}%)"
              },
              // emphasis: {
              //   label: {
              //     show: true,
              //     position: 'center'
              //   },
              // },
              labelLine: {
                show: true
              },
              data: this.masteryData
            }
          ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      })
    },
    getLevelChart() {
      this.$axios.get(this.$httpUrl+'/question/getLevel?id='+this.studentId).then(res=>res.data).then(res=>{
        for (let i = 0; i < res.data.length; i++) {
          this.levelData[i] = {
            value: '',
            name: ''
          }
          this.levelData[i].value = res.data[i].count
          switch (res.data[i].level) {
            case 1: this.levelData[i].name = "容易"; break;
            case 2: this.levelData[i].name = "较易"; break;
            case 3: this.levelData[i].name = "中等"; break;
            case 4: this.levelData[i].name = "较难"; break;
            case 5: this.levelData[i].name = "困难"; break;
          }
        }
        console.log(this.data)
        var myChart = this.$echarts.init(document.getElementById('level'));

        // 指定图表的配置项和数据
        var option = {
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '5%',
            left: '5%'
          },
          title: {
            // 设置饼图标题，位置设为顶部居中
            text: "错题难度",
            left: "center"
          },
          series: [
            {
              // name: '错题掌握程度',
              type: 'pie',
              radius: ['30%', '55%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: true,
                fontWeight: 'bold',
                formatter: "{b} : {c} ({d}%)"
              },
              // emphasis: {
              //   label: {
              //     show: true,
              //     position: 'center'
              //   },
              // },
              labelLine: {
                show: true
              },
              data: this.levelData
            }
          ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      })
    },
    getCourseChart() {
      this.$axios.get(this.$httpUrl+'/question/getCourse?id='+this.studentId).then(res=>res.data).then(res=>{
        for (let i = 0; i < res.data.length; i++) {
          this.courseData[i] = {
            value: '',
            name: ''
          }
          this.courseData[i].value = res.data[i].count
          this.courseData[i].name = res.data[i].cname
        }
        // console.log(this.data)
        var myChart = this.$echarts.init(document.getElementById('course'));

        // 指定图表的配置项和数据
        var option = {
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '5%',
            left: '5%'
          },
          title: {
            // 设置饼图标题，位置设为顶部居中
            text: "错题所属科目",
            left: "center"
          },
          series: [
            {
              // name: '错题掌握程度',
              type: 'pie',
              radius: ['30%', '55%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: true,
                fontWeight: 'bold',
                formatter: "{b} : {c} ({d}%)"
              },
              // emphasis: {
              //   label: {
              //     show: true,
              //     position: 'center'
              //   },
              // },
              labelLine: {
                show: true
              },
              data: this.courseData
            }
          ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      })
    },
    getReviewChart() {
      this.$axios.get(this.$httpUrl+'/question/getReview?id='+this.studentId).then(res=>res.data).then(res=>{
        for (let i = 0; i < res.data.length; i++) {
          this.reviewData[i] = {
            value: '',
            name: ''
          }
          this.reviewData[i].value = res.data[i].count
          if (res.data[i].review >= 10) {
            this.reviewData[i].name = "亟待复习"
          } else if (res.data[i].review <= 2) {
            this.reviewData[i].name = "近期已复习"
          } else {
            this.reviewData[i].name = "有段时间没看了"
          }
        }
        // console.log(this.data)
        var myChart = this.$echarts.init(document.getElementById('review'));

        // 指定图表的配置项和数据
        var option = {
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '5%',
            left: '5%'
          },
          title: {
            // 设置饼图标题，位置设为顶部居中
            text: "错题复习情况",
            left: "center"
          },
          series: [
            {
              // name: '错题掌握程度',
              type: 'pie',
              radius: ['30%', '55%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: true,
                fontWeight: 'bold',
                formatter: "{b} : {c} ({d}%)"
              },
              // emphasis: {
              //   label: {
              //     show: true,
              //     position: 'center'
              //   },
              // },
              labelLine: {
                show: true
              },
              data: this.reviewData
            }
          ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      })
    },
    getNewChart() {
      this.$axios.get(this.$httpUrl+'/question/getNewQue?id='+this.studentId).then(res=>res.data).then(res=>{
        for (let i = 0; i < res.data.length; i++) {
          this.lastMonth[i] = res.data[i].month
          this.newData[i] = res.data[i].count
        }
        var myChart = this.$echarts.init(document.getElementById('newData'));

        // 指定图表的配置项和数据
        var option = {
          title: {
            show: true,
            text: '最近一年添加的错题数量',
            left: 'center'
          },
          xAxis: {
            type: 'category',
            data: this.lastMonth
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              data: this.newData,
              type: 'bar',
              showBackground: false,
              itemStyle: {
                normal: {
                  color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                    offset: 0,
                    color: "#f31212" // 0% 处的颜色
                  }, {
                    offset: 0.2,
                    color: "#fa9108" // 60% 处的颜色
                  }, {
                    offset: 0.4,
                    color: "#5efe01" // 100% 处的颜色
                  }, {
                    offset: 0.6,
                    color: "#08fab1" // 60% 处的颜色
                  }, {
                    offset: 0.8,
                    color: "#0879fa" // 60% 处的颜色
                  }, {
                    offset: 1,
                    color: "#7d08fa" // 60% 处的颜色
                  }], false),
                  label: {
                    show: true,     //开启显示
                    position: 'top',    //在上方显示
                    textStyle: {        //数值样式
                      color: 'black',
                      fontSize: 16
                    }
                  }
                }
              },
            }
          ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      })
    },
    getUpdateChart() {
      this.$axios.get(this.$httpUrl+'/question/getUpdateQue?id='+this.studentId).then(res=>res.data).then(res=>{
        for (let i = 0; i < res.data.length; i++) {
          // this.lastMonth[i] = res.data[i].month
          this.updateData[i] = res.data[i].count
        }
        var myChart = this.$echarts.init(document.getElementById('updateData'));

        // 指定图表的配置项和数据
        var option = {
          title: {
            show: true,
            text: '最近一年复习的错题次数',
            left: 'center'
          },
          xAxis: {
            type: 'category',
            data: this.lastMonth
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              data: this.updateData,
              type: 'bar',
              showBackground: false,
              itemStyle: {
                normal: {
                  color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                    offset: 0,
                    color: "#f31212" // 0% 处的颜色
                  }, {
                    offset: 0.2,
                    color: "#fa9108" // 60% 处的颜色
                  }, {
                    offset: 0.4,
                    color: "#5efe01" // 100% 处的颜色
                  }, {
                    offset: 0.6,
                    color: "#08fab1" // 60% 处的颜色
                  }, {
                    offset: 0.8,
                    color: "#0879fa" // 60% 处的颜色
                  }, {
                    offset: 1,
                    color: "#7d08fa" // 60% 处的颜色
                  }], false),
                  label: {
                    show: true,     //开启显示
                    position: 'top',    //在上方显示
                    textStyle: {        //数值样式
                      color: 'black',
                      fontSize: 16
                    }
                  }
                }
              },
            }
          ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      })
    },
  },
  mounted() {
    this.init();
    this.getMasteryChart();
    this.getLevelChart();
    this.getCourseChart();
    this.getReviewChart();
    this.getNewChart();
    this.getUpdateChart();
  }
}
</script>

<style scoped>

</style>