<template>
  <div>

    <div id="demo">
      <span style="text-align: center; display:block;
      font-size: 30px; margin-top: 30px">{{exam.name}}</span>
      <el-form v-for="(item,i) in questionList" :key="i" style="margin-left: 80px; margin-top: 80px">

        <template  v-if="item.type===0">

          <el-form-item label="题干" prop="body">
            <el-col :span="20">
              <td>{{item.body}}</td>
              <el-image :src="item.bodyPic" v-if="item.bodyPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-col>
          </el-form-item>

          <el-form-item label="A" prop="optiona">
            <el-col :span="20">
              <td>{{item.optiona}}</td>
              <el-image :src="item.optionaPic" v-if="item.optionaPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-col>
          </el-form-item>

          <el-form-item label="B" prop="optionb">
            <el-col :span="20">
              <td>{{item.optionb}}</td>
              <el-image :src="item.optionbPic" v-if="item.optionbPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-col>
          </el-form-item>

          <el-form-item label="C" prop="optionc">
            <el-col :span="20">
              <td>{{item.optionc}}</td>
              <el-image :src="item.optioncPic" v-if="item.optioncPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-col>
          </el-form-item>

          <el-form-item label="D" prop="optiond">
            <el-col :span="20">
              <td>{{item.optiond}}</td>
              <el-image :src="item.optiondPic" v-if="item.optiondPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-col>
          </el-form-item>

          <el-divider></el-divider>

        </template>

        <template  v-if="item.type===1">

          <el-form-item label="题干" prop="body">
            <el-col :span="20">
              <td>{{item.body}}</td>
              <el-image :src="item.bodyPic" v-if="item.bodyPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-col>
          </el-form-item>

          <el-divider></el-divider>

        </template>
      </el-form>

    </div>

    <div style="margin-bottom: 5px; float:right;">
      <el-button type="danger" style="margin-left: 5px;" @click="modExam">修改</el-button>
      <el-button type="success" style="margin-left: 5px;" @click="startTest">在线测试</el-button>
      <el-button type="warning" style="margin-left: 5px;" @click="exportPDF">导出</el-button>
    </div>
  </div>
</template>

<script>
import htmlToPdf from '@/utils/htmlToPdf'
export default {
  name: "ReviewExamDetail",
  data() {
    return {
      exam: this.$route.query.pushData.exam,
      questionList: [],
      htmlTitle: '',
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
    exportPDF () {
      htmlToPdf.downloadPDF(document.querySelector('#demo'), this.exam.name)
    }

  },
  created() {
    this.init()
  }
}
</script>

<style scoped>

</style>