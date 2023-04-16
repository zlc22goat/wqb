<template>
  <div>
    <div style="margin-bottom: 5px;">
      <el-button type="danger" style="margin-left: 5px;" @click="modExam">修改</el-button>
      <el-button type="success" style="margin-left: 5px;" @click="startTest">在线测试</el-button>
      <el-button type="warning" style="margin-left: 5px;" @click="exportPDF">导出</el-button>
    </div>


  </div>
</template>

<script>
export default {
  name: "ReviewExamDetail",
  data() {
    return {
      exam: this.$route.query.pushData.exam,
      questionList: [],

    }
  },
  methods: {
    init(){
      this.questionList = this.$route.query.pushData.questionList
    },
    modExam() {
      let dataOb = this.$route.query.pushData
      this.$router.push({path: "/QuestionList", query: {pushData: dataOb}})
    },
    startTest() {

    },
    exportPDF() {

    },

  },
  created() {
    this.init()
  }
}
</script>

<style scoped>

</style>