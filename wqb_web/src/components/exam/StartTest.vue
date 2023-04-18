<template>
  <div>

    <template>
      <div>
        <el-button type="danger" style="margin-left: 5px;" @click="save">提 交</el-button>
        <el-button type="success" style="margin-left: 5px;" @click="resetParam">重 置</el-button>
      </div>
    </template>

    <div>
      <span style="text-align: center; display:block;
      font-size: 30px; margin-top: 30px">{{exam.name}}</span>
      <el-form v-for="(item,i) in questionList" :key="i" style="margin-left: 80px; margin-top: 80px">

        <span>第{{i+1}}题</span>

        <template  v-if="item.type===0">

          <el-form-item label="" prop="body">
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

          <el-form-item label="你的答案" prop="myAnswerOption">
            <el-checkbox-group v-model="checkList[i]">
              <el-checkbox label="A"></el-checkbox>
              <el-checkbox label="B"></el-checkbox>
              <el-checkbox label="C"></el-checkbox>
              <el-checkbox label="D"></el-checkbox>
            </el-checkbox-group>
          </el-form-item>

          <el-divider></el-divider>

        </template>

        <template  v-if="item.type===1">

          <el-form-item label="" prop="body">
            <el-col :span="20">
              <td>{{item.body}}</td>
              <el-image :src="item.bodyPic" v-if="item.bodyPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-col>
          </el-form-item>

          <el-form-item label="你的答案" prop="myAnswer">
            <el-col :span="15">
              <el-input type="textarea" v-model="answerForms[i].myAnswer" @input="change($event)">
              </el-input>
              <el-upload
                  class="upload-demo"
                  action="http://localhost:8092/upload"
                  :on-success="(response, file, fileList) => {
                      return handleSuccess(response, file, fileList, i);
                    }"
                  :limit="1"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :before-remove="beforeRemove"
                  :on-error="handleError"
                  list-type="picture">
                <el-button style="margin-top: 5px" type="primary">上传<i class="el-icon-upload el-icon--right"></i></el-button>
              </el-upload>

            </el-col>

          </el-form-item>

          <el-divider></el-divider>

        </template>
      </el-form>

    </div>

    <el-dialog
        title=""
        :visible.sync="centerDialogVisible"
        width="30%"
        center>

      <span style="text-align: center; display:block;
      font-size: 20px;" v-if="this.exam.mark === ''">很遗憾，都错了，不要灰心，记得时常巩固喔！！！</span><br>
      <span style="text-align: center; display:block;
      font-size: 20px;" v-if="this.exam.mark !== ''">你的分数为{{this.exam.mark}}分</span><br>
      <span style="text-align: center; display:block;" v-if="this.exam.mark === 100">恭喜你，满分！！！</span>
      <span style="text-align: center; display:block;" v-if="this.exam.mark !== 100">填空解答的判分仅供参考，自己再检查检查吧</span>

      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>

    <el-backtop></el-backtop>
  </div>
</template>

<script>
export default {
  name: "StartTest",
  data() {
    return {
      centerDialogVisible: false,
      perMark: 0,
      exam: this.$route.query.pushData.exam,
      questionList: [],
      checkList: [],
      answerForms: [],
      correctAnswer: [],
      question: {
        id: '',
        type: ''
      },
    }
  },
  methods: {
    init(){
      this.questionList = this.$route.query.pushData.questionList
      for(let i=0;i<this.questionList.length;i++) {
        this.checkList[i] = []
        this.answerForms[i] = {
          myAnswer: '',
          myAnswerPic: '',
          questionId: ''
        }
        this.answerForms[i].questionId = this.questionList[i].id
      }
      this.perMark = 100 / this.questionList.length
    },
    // 计算分数
    searchAnswerAndJudge(result, i) {
      this.question = this.questionList[i]
      if (this.question.type === 0) {
        // 这里类型不同，不能用===
        if (result.myAnswerOption == this.question.answerOption) {
          this.exam.mark = Number(this.perMark) + Number(this.exam.mark);
        }
      } else {
        if (result.myAnswer == this.question.answer) {
          this.exam.mark = Number(this.perMark) + Number(this.exam.mark);
        }
      }
    },

    // 如果标签嵌套太深，会导致无法获取到 DOM，这是我们需要 $forceUpdate() 强制刷新，才可获取
    change() {
      this.$forceUpdate()
    },
    save(){
      for(let i = 0; i < this.questionList.length; i++) {
        let dataOb = {
          answer: this.answerForms[i],
          strings: this.checkList[i]
        }
        this.$axios.post(this.$httpUrl+'/answer/save', dataOb).then(res=>res.data).then(res=>{
          if(res.code===200){
            this.searchAnswerAndJudge(res.data, i)
          }else{
            this.$message({
              message: '操作失败！',
              type: 'error'
            });
          }
        })

      }
      this.centerDialogVisible = true
      this.resetParam()
      this.exam.mark = ''
    },
    updateExam() {
      let dataOb2 = {
        exam: this.exam,
        questionList: this.questionList
      }
      this.$axios.post(this.$httpUrl+'/exam/update', dataOb2).then(res=>res.data).then(res=>{
        if(res.code==200){
          this.$message({
            message: '操作成功！',
            type: 'success'
          });
        }else{
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }
      })
    },
    resetParam(){
      for(let j = 0; j < this.questionList.length; j++) {
        this.answerForms[j].myAnswer = ''
        this.answerForms[j].myAnswerPic = ''
        this.checkList[j] = []
      }
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
    handleSuccess(response, file, fileList, i) {
      console.log(response, file, fileList, i);
      this.answerForms[i].myAnswerPic = response.data
    },
  },
  created() {
    this.init()
  }
}
</script>

<style scoped>

</style>