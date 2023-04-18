<template>
  <div>
    <div style="width: 50%;  float: left">
      <!--      <label>题目预览</label>-->
      <el-form ref="form" :model="form" label-width="80px">

        <el-form-item label="题干" prop="body">
          <el-col :span="20">
            <td>{{form.body}}</td>
            <el-image :src="form.bodyPic" v-if="form.bodyPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item><br>

        <el-form-item label="" prop="level">
          <el-col :span="20">
            <div class="block">
              <el-rate v-model="form.level" disabled></el-rate>
            </div>
          </el-col>
        </el-form-item><br>

        <el-form-item label="你的答案" prop="myAnswer">
          <el-col :span="20">
            <el-input v-model="answerForm.myAnswer">
              <el-button style="padding-right:10px" slot="suffix" type="text" @click="uploadPic">上传图片</el-button>
            </el-input>
            <el-dialog title="上传描述你的答案的图片"
                       :visible.sync="centerDialogVisible"
                       width="30%"
                       center>
              <el-upload
                  class="upload-demo"
                  drag
                  action="http://localhost:8092/upload"
                  :on-success="uploadMyAnswerPic"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :before-remove="beforeRemove"
                  :on-error="handleError">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
              <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="uploadPic">确 定</el-button>
            </span>
            </el-dialog>
          </el-col>

        </el-form-item>

        <el-button type="danger" @click="resetForm">重 答</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
        <el-button type="info" @click="back">返 回</el-button>
        <el-button type="warning" @click="showHistory">查看历史</el-button>

      </el-form>
    </div>

    <div style="width: 50%; float:right;" v-if="this.isAnswer === true">

      <el-form ref="form" :model="answerForm" label-width="80px">
        <el-form-item label="你的答案" prop="MyAnswer">
          <el-col :span="20">
            <td>{{answerForm.myAnswer}}</td>
            <el-image :src="answerForm.myAnswerPic" v-if="answerForm.myAnswerPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item>
      </el-form>

      <el-form ref="form" :model="form" label-width="80px">

        <el-form-item label="正确答案">
          <el-col :span="20">
            <td>{{form.answer}}</td>
            <el-image :src="form.answerPic" v-if="form.answerPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item>
        <br>

        <el-form-item label="解析:">
          <td>{{ form.detail }}</td>
          <el-image :src="form.detailPic" v-if="form.detailPic !== ''">
            <div slot="error" class="image-slot"></div>
          </el-image>
        </el-form-item>

        <el-button type="primary" @click="returnBack">答错了，下次复习</el-button>
        <el-button type="danger" @click="returnBack">答对了，猜的</el-button>
        <el-button type="success" @click="returnOk">这题会了</el-button>
      </el-form>

    </div>

    <el-drawer
        title="历史做题记录"
        :visible.sync="isShowHistory">

      <el-form v-if="myHistoryAnswer === null">
        <span style="text-align: center; display:block;">暂时没有做题记录喔！</span>
      </el-form>

      <el-form v-for="(item,i) in myHistoryAnswer" :key="i" style="margin-left: 80px; margin-top: 80px">

        <el-form-item label="时间" prop="createTime">
          <el-col :span="20">
            <td>{{item.createTime}}</td>
          </el-col>
        </el-form-item>

        <el-form-item label="我的答案" prop="myAnswerOption">
          <el-col :span="20">
            <td>{{item.myAnswer}}</td>
            <el-image :src="item.myAnswerPic" v-if="item.myAnswerPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item>

        <el-divider></el-divider>

      </el-form>

    </el-drawer>
  </div>
</template>

<script>
export default {
  name: "ReviewOther",
  data() {
    return {
      isShowHistory: false,
      isAnswer: false,
      // isCorrect: false,
      centerDialogVisible: false,
      hasData: this.$route.query.pushData,
      // checkList :[],
      myHistoryAnswer: [],
      correctAnswer: '',
      correctAnswerPic: '',
      myAnswer: '',
      exam: '',
      form: {
        id: '',
        remark: '',
        mastery: ''
      },
      answerForm: {
        myAnswer: '',
        myAnswerPic: '',
        questionId: ''
      }
    }
  },
  methods:{
    init(){
      if (typeof this.hasData.questionList != "undefined") {
        this.form = this.hasData.questionList
        this.exam = this.hasData.exam
      } else if (typeof this.hasData != "undefined") {
        // this.form.id = this.hasData.id
        // this.form.remark = this.hasData.remark
        this.form = this.hasData
        this.answerForm.questionId = this.hasData.id
      }
      // if (typeof this.hasData != "undefined") {
      //   this.form.id = this.hasData.id
      //   this.form.remark = this.hasData.remark
      //
      // }
    },
    mod() {
      let dataOb = {
        question: this.form,
      }
      this.$axios.post(this.$httpUrl+'/question/update',dataOb).then(res=>res.data).then(res=>{
        // console.log(res)
        if(res.code==200){
          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.$router.push("/QuestionList")
        }else{
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }
      })

    },
    save(){
      // let dataOb = {
      //   id: this.hasData.id,
      //   answer: this.answerForm
      // }
      this.$axios.post(this.$httpUrl+'/answer/savePic', this.answerForm).then(res=>res.data).then(res=>{
        // console.log("save")
        if(res.code===200){
          // this.$message({
          //   message: '操作成功！',
          //   type: 'success'
          // });
          this.$message({
            message: '答题成功，自己比较一下答案吧。。。',
            type: 'success'
          });
        }else{
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }
      })
      this.isAnswer = true
    },
    showHistory() {
      this.isShowHistory = true
      this.$axios.post(this.$httpUrl+'/answer/selectAllAnswer?id=' + this.form.id).then(res=>res.data).then(res=>{
        this.myHistoryAnswer = res.data
      })
    },
    uploadPic() {
      this.centerDialogVisible = !this.centerDialogVisible
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleError() {
      this.$message.error("图片上传失败，请确认图片是否过大！")
    },
    beforeRemove(file) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    uploadMyAnswerPic(response) {
      // console.log(response)
      this.answerForm.myAnswerPic = response.data
    },
    resetForm(){
      // console.log(this.answerForm.myAnswer)
      this.answerForm.myAnswer = ''
      this.isAnswer = false
    },
    back() {
      if (this.exam === '') {
        this.$router.push({path: "/QuestionList"})
      } else {
        this.$axios.get(this.$httpUrl+'/exam/selectDetail?id=' + this.exam.id).then(res=>res.data).then(res=>{
          let dataOb = {
            exam: this.exam,
            questionList: res.data
          }
          this.$router.push({path: "/StartTest", query: {pushData: dataOb}})
        })

      }
    },
    returnBack() {
      if (this.form.mastery !== 0)  this.form.mastery = this.form.mastery - 1
      this.mod()
    },
    returnOk() {
      if (this.form.mastery !== 2)  this.form.mastery = this.form.mastery + 1
      this.mod()
    },
  },
  created(){
    this.init()
  }
}
</script>

<style scoped>

</style>