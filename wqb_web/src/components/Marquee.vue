<template>
  <div class="wrap">
    <div ref="box" class="box">
      <div ref="marquee" class="marquee">{{message.context}}</div>
      <div ref="copy" class="copy"></div>
    </div>
    <div ref="node" class="node">{{message.context}}</div>
  </div>
</template>
<script>
export default {
  name : 'Marquee',
  // props: ['lists'], // 父组件传入数据， 数组形式 [ "连雨不知春去","一晴方觉夏深"]
  data () {
    return {
      student: '',
      message: '' // 数组文字转化后的字符串
    }
  },
  methods: {
    move () {
      // 获取文字message 的计算后宽度  （由于overflow的存在，直接获取不到，需要独立的node计算）
      let width = this.$refs.node.getBoundingClientRect().width
      // this.$refs.copy.innerText = this.message // 文字副本填充
      let distance = 0 // 位移距离
      // 设置位移
      setInterval(() => {
        distance = distance - 1
        // 如果位移超过文字宽度，则回到起点
        if (-distance >= width) {
          distance = 1300
        }
        this.$refs.box.style.transform = 'translateX(' + distance + 'px)'
      }, 20)
    },
    getRecentMessage() {
      this.student = JSON.parse(sessionStorage.getItem('CurUser'))
      this.$axios.get(this.$httpUrl+'/message/selectRecent?squadId=' + this.student.squadId).then(res=>res.data).then(res=>{
        this.message = res.data;
      })
    },
  },
  // 把父组件传入的arr转化成字符串
  // mounted: function () {
  //   for (let i = 0; i < this.lists.length; i++) {
  //     this.message += ' ' + this.lists[i]
  //   }
  // },
  // 更新的时候运动
  updated: function () {
    this.move()
  },
  beforeMount(){
    this.getRecentMessage()
  }
}
</script>
<style scoped>

.wrap {
  overflow: hidden;
}

.box {
  width: 80000%;
}

.box div {
  float: left;
}

.marquee {
  margin: 0 16px 0 0;
}

.node {
  position: absolute;
  z-index: -999;
  top: -999999px;
}
</style>
