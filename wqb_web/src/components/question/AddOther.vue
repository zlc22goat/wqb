<template>
  <div>
    <div style = "width: 50%;  float: left">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="题干" prop="body">
          <el-col :span="20">
            <el-input v-model="form.body">
              <el-button style="padding-right:10px" slot="suffix" type="text" @click="uploadPic(0)">上传图片</el-button>
            </el-input>
            <el-dialog title="上传描述题干的图片"
                       :visible.sync="centerDialogVisible"
                       width="30%"
                       center>
              <el-upload
                  class="upload-demo"
                  drag
                  action="http://localhost:8092/upload"
                  :on-success="uploadBodyPic"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :before-remove="beforeRemove"
                  :on-error="handleError">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
              <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="uploadPic(0)">确 定</el-button>
            </span>
            </el-dialog>
          </el-col>
        </el-form-item>

        <el-form-item label="答案" prop="answer">
          <el-col :span="20">
            <el-input v-model="form.answer">
              <el-button style="padding-right:10px" slot="suffix" type="text" @click="uploadPic(1)">上传图片</el-button>
            </el-input>
            <el-dialog title="上传描述答案的图片"
                       :visible.sync="centerDialogVisible1"
                       width="30%"
                       center>
              <el-upload
                  class="upload-demo"
                  drag
                  action="http://localhost:8092/upload"
                  :on-success="uploadAnswerPic"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :before-remove="beforeRemove"
                  :on-error="handleError">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
              <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="uploadPic(1)">确 定</el-button>
            </span>
            </el-dialog>
          </el-col>

        </el-form-item>

        <el-form-item label="解析" prop="detail">
          <el-col :span="20">
            <el-input v-model="form.detail">
              <el-button style="padding-right:10px" slot="suffix" type="text" @click="uploadPic(2)">上传图片</el-button>
            </el-input>
            <el-dialog title="上传题目解析的图片"
                       :visible.sync="centerDialogVisible2"
                       width="30%"
                       center>
              <el-upload
                  class="upload-demo"
                  drag
                  action="http://localhost:8092/upload"
                  :on-success="uploadDetailPic"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :before-remove="beforeRemove"
                  :on-error="handleError">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
              <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="uploadPic(2)">确 定</el-button>
            </span>
            </el-dialog>
          </el-col>
        </el-form-item>

        <el-form-item label="难度" prop="level">
          <el-col :span="20">
            <div class="block">
              <span class="demonstration">请选择难度</span>
              <el-rate v-model="form.level" @change="rateChange"></el-rate>
            </div>
          </el-col>
        </el-form-item>

        <el-form-item label="所属课程" prop="cName">
          <el-select v-model="form.courseId" filterable placeholder="请选择所属课程" style="margin-left: 5px;">
            <el-option
                v-for="item in categoryOptions"
                :key="item.cid"
                :label="item.cname"
                :value="item.cid">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input v-model="form.remark"></el-input>
          </el-col>
        </el-form-item>

        <el-button type="danger" @click="resetForm">重 置</el-button>
        <el-button type="primary" @click="doSave">确 定</el-button>
      </el-form>
    </div>
    <div style="width: 50%;  float: right">
      <label>题目预览</label>
      <el-form ref="form" :model="form" label-width="80px">

        <el-form-item label="" prop="body">
          <el-col :span="20">
            <td>{{form.body}}</td>
            <el-image :src="form.bodyPic" v-if="form.bodyPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item>

        <el-form-item label="" prop="level">
          <el-col :span="20">
            <div class="block">
              <el-rate v-model="form.level"></el-rate>
            </div>
          </el-col>
        </el-form-item>

        <el-form-item label="答案" prop="answer">
          <el-col :span="20">
            <td>{{form.answer}}</td>
            <el-image :src="form.answerPic" v-if="form.answerPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item>

        <el-form-item label="解析" prop="detail">
          <el-col :span="20">
            <td>{{form.detail}}</td>
            <el-image :src="form.detailPic" v-if="form.detailPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item>

      </el-form>
    </div>
  </div>

</template>


<script>

export default {
  name: "AddOther",
  data() {
    return {
      centerDialogVisible: false,
      centerDialogVisible1: false,
      centerDialogVisible2: false,
      categoryOptions: [],
      hasData: this.$route.query.index,
      student: '',
      form: {
        id: '',
        body: '',
        bodyPic: '',
        answer: '',
        answerPic: '',
        // myAnswerOption: '',
        // myAnswer: '',
        // myAnswerPic: '',
        detail: '',
        detailPic: '',
        level: 0,
        courseId: '',
        // cName: '',
        type: '1',
        studentId: '',
        remark: '',
        // mastery: ''
      }
    }
  },
  methods:{
    init(){
      this.student = JSON.parse(sessionStorage.getItem('CurUser'))
      if (typeof this.hasData != "undefined") {
        this.form.id = this.hasData.id
        this.form.body = this.hasData.body
        this.form.bodyPic = this.hasData.bodyPic
        this.form.answer = this.hasData.answer
        this.form.answerPic = this.hasData.answerPic
        this.form.detail = this.hasData.detail
        this.form.detailPic = this.hasData.detailPic
        this.form.level = this.hasData.level
        this.form.courseId = this.hasData.cname
        this.form.remark = this.hasData.remark
      }
    },
    getOneCategory() {
      this.$axios.get(this.$httpUrl+'/course/list').then(res=>res.data).then(res=>{
        this.categoryOptions = res.data;
      })
    },
    uploadPic(i) {
      switch (i) {
        case 0: this.centerDialogVisible = !this.centerDialogVisible; break;
        case 1: this.centerDialogVisible1 = !this.centerDialogVisible1; break;
        case 2: this.centerDialogVisible2 = !this.centerDialogVisible2; break;
      }
    },
    doSave(){
      this.$refs.form.validate((valid) => {
        if (valid) {
          if(this.form.id){
            this.mod();
          }else{
            console.log("save")
            this.save();
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    mod() {
      let dataOb = {
        question: this.form,
      }
      this.$axios.post(this.$httpUrl+'/question/update',dataOb).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){

          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.resetForm()
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
      this.form.studentId = this.student.sid
      let dataOb = {
        question: this.form
      }
      this.$axios.post(this.$httpUrl+'/question/save', dataOb).then(res=>res.data).then(res=>{
        console.log("save")
        if(res.code===200){
          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.resetForm()
          this.$router.push("/QuestionList")
        }else{
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }
      })
    },

    resetForm(){
      this.form.body=''
      this.form.bodyPic=''
      this.form.answer = ''
      this.form.answerPic = ''
      this.form.detail = ''
      this.form.detailPic = ''
      this.form.level = 0
      this.form.courseId = ''
      this.form.remark = ''
    },
    rateChange(value) {
      console.log(value);
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
    uploadBodyPic(response) {
      console.log(response)
      this.form.bodyPic = response.data
    },
    uploadAnswerPic(response) {
      console.log(response)
      this.form.answerPic = response.data
    },
    uploadDetailPic(response) {
      console.log(response)
      this.form.detailPic = response.data
    },
  },
  created(){
    this.init()
    this.getOneCategory()
  }
}
</script>

<style scoped>

</style>