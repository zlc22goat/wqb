<template>
  <div>
    <div style="width: 50%;  float: left">
<!--      <label>题目预览</label>-->
      <el-form ref="form" :model="hasData" label-width="80px">

        <el-form-item label="题干" prop="body">
          <el-col :span="20">
            <td>{{hasData.body}}</td>
            <el-image :src="hasData.bodyPic" v-if="hasData.bodyPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item><br>

        <el-form-item label="A" prop="optiona">
          <el-col :span="20">
            <td>{{hasData.optiona}}</td>
            <el-image :src="hasData.optionaPic" v-if="hasData.optionaPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item><br>

        <el-form-item label="B" prop="optionb">
          <el-col :span="20">
            <td>{{hasData.optionb}}</td>
            <el-image :src="hasData.optionbPic" v-if="hasData.optionbPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item><br>

        <el-form-item label="C" prop="optionc">
          <el-col :span="20">
            <td>{{hasData.optionc}}</td>
            <el-image :src="hasData.optioncPic" v-if="hasData.optioncPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item><br>

        <el-form-item label="D" prop="optiond">
          <el-col :span="20">
            <td>{{hasData.optiond}}</td>
            <el-image :src="form.optiondPic" v-if="hasData.optiondPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item><br>

        <el-form-item label="" prop="level">
          <el-col :span="20">
            <div class="block">
              <el-rate v-model="hasData.level" disabled></el-rate>
            </div>
          </el-col>
        </el-form-item><br>

        <el-form-item label="你的答案" prop="answerOption">
          <el-checkbox-group v-model="checkList">
            <el-checkbox label="A"></el-checkbox>
            <el-checkbox label="B"></el-checkbox>
            <el-checkbox label="C"></el-checkbox>
            <el-checkbox label="D"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-button type="danger" @click="resetForm">重 选</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
        <el-button type="warning" @click="showHistory">查看历史</el-button>

      </el-form>
    </div>

    <div style="width: 50%; float:right;" v-if="this.isAnswer === true">
      <el-form ref="form" :model="hasData" label-width="80px">
        <el-form-item label="正确答案:">
          <td>{{ this.correctAnswer }}</td>
        </el-form-item>
        <br>

        <el-form-item label="解析:">
          <td>{{ this.hasData.detail }}</td>
          <el-image :src="this.hasData.detailPic" v-if="this.hasData.detailPic !== ''">
            <div slot="error" class="image-slot"></div>
          </el-image>
        </el-form-item>

        <el-button type="primary" @click="returnBack"
                   v-if="this.isCorrect === false">这次看明白了，下次再复习</el-button>
        <el-button type="danger" @click="returnBack"
                   v-if="this.isCorrect === true">不太会，猜对的</el-button>
        <el-button type="success" @click="returnOk"
                   v-if="this.isCorrect === true">这题会了</el-button>
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
<!--            <el-image :src="item.bodyPic" v-if="item.bodyPic !== ''">-->
<!--              <div slot="error" class="image-slot"></div>-->
<!--            </el-image>-->
          </el-col>
        </el-form-item>

        <el-form-item label="我的答案" prop="myAnswerOption">
          <el-col :span="20">
            <td>{{item.myAnswerOption}}</td>
<!--            <el-image :src="item.bodyPic" v-if="item.bodyPic !== ''">-->
<!--              <div slot="error" class="image-slot"></div>-->
<!--            </el-image>-->
          </el-col>
        </el-form-item>

        <el-divider></el-divider>

      </el-form>

    </el-drawer>
  </div>
</template>

<script>

export default {
  name: "ReviewOption",
  data() {
    return {
      isShowHistory: false,
      isAnswer: false,
      isCorrect: false,
      centerDialogVisible: false,
      hasData: this.$route.query.pushData,
      checkList: [],
      myHistoryAnswer: [],
      correctAnswer: '',
      myAnswer: '',
      form: {
        id: '',
        remark: '',
        mastery: ''
      }
    }
  },
  methods:{
    init(){
      if (typeof this.hasData != "undefined") {
        this.form.id = this.hasData.id
        this.form.remark = this.hasData.remark
      }
    },
    searchAnswerAndJudge(op) {
      this.$axios.post(this.$httpUrl+'/question/findById?id=' + this.hasData.id).then(res=>res.data).then(res=>{
        this.correctAnswer = res.data.answerOption
        if (op === this.correctAnswer) {
          this.$message({
            message: '恭喜你！答对啦！！！',
            type: 'success'
          });
          this.isCorrect = true
        } else {
          this.$message({
            message: '很遗憾，答错了。。。',
            type: 'error'
          });
        }
      })
      this.isAnswer = true
    },
    mod() {
      let dataOb = {
        question: this.hasData,
        list: this.checkList
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
      let dataOb = {
        id: this.hasData.id,
        strings: this.checkList
      }
      this.$axios.post(this.$httpUrl+'/answer/save', dataOb).then(res=>res.data).then(res=>{
        if(res.code===200){
          this.searchAnswerAndJudge(res.data.myAnswerOption)
        }else{
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }
      })
      this.checkList = []
    },
    showHistory() {
      this.isShowHistory = true
      this.$axios.post(this.$httpUrl+'/answer/selectAllAnswer?id=' + this.hasData.id).then(res=>res.data).then(res=>{
        this.myHistoryAnswer = res.data
      })
    },
    resetForm(){
      this.checkList = []
    },
    returnBack() {
      if (this.hasData.mastery !== 0)  this.hasData.mastery = this.hasData.mastery - 1
      this.mod()
    },
    returnOk() {
      if (this.hasData.mastery !== 2)  this.hasData.mastery = this.hasData.mastery + 1
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