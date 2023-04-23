<template>
  <div>
    <div style = "width: 49%;  float: left; border: dashed 1px; margin-top: 10px">
      <div id="state" style="width: 100%;height:600px; margin-top: 5px"></div>
    </div>

    <div style = "width: 49%;  float: right; border: dashed 1px; margin-top: 10px">
      <div id="mark" style="width: 100%;height:600px; margin-top: 5px"></div>
    </div>

    <div style = "width: 100%; float: left; border: dashed 1px; margin-top: 10px">
      <div id="newExam" style="width: 100%;height:600px; margin-top: 5px"></div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
  name: "AnalysisExam",
  data() {
    return {
      student: '',
      studentId: '',
      stateData: [],
      markData: [],
      lastMonth: [],
      newExamData: [],
    }
  },
  methods: {
    init() {
      this.student = JSON.parse(sessionStorage.getItem('CurUser'))
      this.studentId = this.student.sid
    },
    getStateChart() {
      this.$axios.get(this.$httpUrl+'/exam/getState?id='+this.studentId).then(res=>res.data).then(res=>{
        for (let i = 0; i < res.data.length; i++) {
          this.stateData[i] = {
            value: '',
            name: ''
          }
          this.stateData[i].value = res.data[i].stateCount
          switch (res.data[i].state) {
            case 0: this.stateData[i].name = "未完成"; break;
            case 1: this.stateData[i].name = "已完成"; break;
          }
        }
        // console.log(this.data)
        var myChart = this.$echarts.init(document.getElementById('state'));

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
            text: "组卷完成情况",
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
              data: this.stateData
            }
          ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      })
    },
    getMarkChart() {
      this.$axios.get(this.$httpUrl+'/exam/getMark?id='+this.studentId).then(res=>res.data).then(res=>{
        for (let i = 0; i < res.data.length; i++) {
          this.markData[i] = {
            value: '',
            name: ''
          }
          this.markData[i].value = res.data[i].markCount
          this.markData[i].name = res.data[i].markRemark
        }

        var myChart = this.$echarts.init(document.getElementById('mark'));

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
            text: "组卷测试结果",
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
              data: this.markData
            }
          ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      })
    },
    getNewExamChart() {
      this.$axios.get(this.$httpUrl+'/exam/getNewExam?id='+this.studentId).then(res=>res.data).then(res=>{
        for (let i = 0; i < res.data.length; i++) {
          this.lastMonth[i] = res.data[i].month
          this.newExamData[i] = res.data[i].monthCount
        }
        var myChart = this.$echarts.init(document.getElementById('newExam'));

        // 指定图表的配置项和数据
        var option = {
          title: {
            show: true,
            text: '最近一年组卷数量',
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
              data: this.newExamData,
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
    this.getStateChart();
    this.getMarkChart();
    this.getNewExamChart();
  }
}
</script>

<style scoped>

</style>