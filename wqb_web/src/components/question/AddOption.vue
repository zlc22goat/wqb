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

        <el-form-item label="选项A" prop="optiona">
          <el-col :span="20">
            <el-input v-model="form.optiona">
              <el-button style="padding-right:10px" slot="suffix" type="text" @click="uploadPic(1)">上传图片</el-button>
            </el-input>
            <el-dialog title="上传描述选项A的图片"
                       :visible.sync="centerDialogVisible1"
                       width="30%"
                       center>
              <el-upload
                  class="upload-demo"
                  drag
                  action="http://localhost:8092/upload"
                  :on-success="uploadOptionAPic"
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

        <el-form-item label="选项B" prop="optionb">
          <el-col :span="20">
            <el-input v-model="form.optionb">
              <el-button style="padding-right:10px" slot="suffix" type="text" @click="uploadPic(2)">上传图片</el-button>
            </el-input>
            <el-dialog title="上传描述选项B的图片"
                       :visible.sync="centerDialogVisible2"
                       width="30%"
                       center>
              <el-upload
                  class="upload-demo"
                  drag
                  action="http://localhost:8092/upload"
                  :on-success="uploadOptionBPic"
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

        <el-form-item label="选项C" prop="optionc">
          <el-col :span="20">
            <el-input v-model="form.optionc">
              <el-button style="padding-right:10px" slot="suffix" type="text" @click="uploadPic(3)">上传图片</el-button>
            </el-input>
            <el-dialog title="上传描述选项C的图片"
                       :visible.sync="centerDialogVisible3"
                       width="30%"
                       center>
              <el-upload
                  class="upload-demo"
                  drag
                  action="http://localhost:8092/upload"
                  :on-success="uploadOptionCPic"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :before-remove="beforeRemove"
                  :on-error="handleError">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
              <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="uploadPic(3)">确 定</el-button>
            </span>
            </el-dialog>
          </el-col>
        </el-form-item>

        <el-form-item label="选项D" prop="optiond">
          <el-col :span="20">
            <el-input v-model="form.optiond">
              <el-button style="padding-right:10px" slot="suffix" type="text" @click="uploadPic(4)">上传图片</el-button>
            </el-input>
            <el-dialog title="上传描述选项D的图片"
                       :visible.sync="centerDialogVisible4"
                       width="30%"
                       center>
              <el-upload
                  class="upload-demo"
                  drag
                  action="http://localhost:8092/upload"
                  :on-success="uploadOptionDPic"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :before-remove="beforeRemove"
                  :on-error="handleError">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
              <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="uploadPic(4)">确 定</el-button>
            </span>
            </el-dialog>
          </el-col>
        </el-form-item>

        <el-form-item label="答案" prop="answerOption">
          <el-checkbox-group v-model="checkList">
            <el-checkbox label="A">A</el-checkbox>
            <el-checkbox label="B">B</el-checkbox>
            <el-checkbox label="C">C</el-checkbox>
            <el-checkbox label="D">D</el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-form-item label="解析" prop="detail">
          <el-col :span="20">
            <el-input v-model="form.detail">
              <el-button style="padding-right:10px" slot="suffix" type="text" @click="uploadPic(5)">上传图片</el-button>
            </el-input>
            <el-dialog title="上传题目解析的图片"
                       :visible.sync="centerDialogVisible5"
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
              <el-button type="primary" @click="uploadPic(5)">确 定</el-button>
            </span>
            </el-dialog>
          </el-col>
        </el-form-item>

        <el-form-item label="难度" prop="level">
          <el-col :span="20">
            <div class="block">
              <span class="demonstration">请选择难度</span>
              <el-rate v-model="form.level"></el-rate>
            </div>
          </el-col>
        </el-form-item>

        <el-form-item label="所属课程" prop="cname">
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
        <el-button type="info" @click="back">返 回</el-button>
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

        <el-form-item label="A" prop="optiona">
          <el-col :span="20">
            <td>{{form.optiona}}</td>
            <el-image :src="form.optionaPic" v-if="form.optionaPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item>

        <el-form-item label="B" prop="optionb">
          <el-col :span="20">
            <td>{{form.optionb}}</td>
            <el-image :src="form.optionbPic" v-if="form.optionbPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item>

        <el-form-item label="C" prop="optionc">
          <el-col :span="20">
            <td>{{form.optionc}}</td>
            <el-image :src="form.optioncPic" v-if="form.optioncPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item>

        <el-form-item label="D" prop="optiond">
          <el-col :span="20">
            <td>{{form.optiond}}</td>
            <el-image :src="form.optiondPic" v-if="form.optiondPic !== ''">
              <div slot="error" class="image-slot"></div>
            </el-image>
          </el-col>
        </el-form-item>

        <el-form-item label="" prop="level">
          <el-col :span="20">
            <div class="block">
              <el-rate v-model="form.level" disabled></el-rate>
            </div>
          </el-col>
        </el-form-item>

        <el-form-item label="答案" prop="answerOption">
          <el-checkbox-group v-model="checkList">
            <el-checkbox label="A" disabled></el-checkbox>
            <el-checkbox label="B" disabled></el-checkbox>
            <el-checkbox label="C" disabled></el-checkbox>
            <el-checkbox label="D" disabled></el-checkbox>
          </el-checkbox-group>
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
  name: "AddOption",
  data() {
    return {
      centerDialogVisible: false,
      centerDialogVisible1: false,
      centerDialogVisible2: false,
      centerDialogVisible3: false,
      centerDialogVisible4: false,
      centerDialogVisible5: false,
      categoryOptions: [],
      hasData: this.$route.query.index,
      student: '',
      // checkedA: false,
      // checkedB: false,
      // checkedC: false,
      // checkedD: false,
      checkList :[],
      form: {
        id: '',
        body: '',
        bodyPic: '',

        optiona: '',
        optionaPic: '',
        optionb: '',
        optionbPic: '',
        optionc: '',
        optioncPic: '',
        optiond: '',
        optiondPic: '',
        answerOption: '',
        cname: '',
        // answer: '',
        // answerPic: '',
        // myAnswerOption: '',
        // myAnswer: '',
        // myAnswerPic: '',
        detail: '',
        detailPic: '',
        level: 0,
        courseId: '',
        // cName: '',
        type: '0',
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
        // this.form.id = this.hasData.id
        // this.form.body = this.hasData.body
        // this.form.bodyPic = this.hasData.bodyPic
        // this.form.optiona = this.hasData.optiona
        // this.form.optionaPic = this.hasData.optionaPic
        // this.form.optionb = this.hasData.optionb
        // this.form.optionbPic = this.hasData.optionbPic
        // this.form.optionc = this.hasData.optionc
        // this.form.optioncPic = this.hasData.optioncPic
        // this.form.optiond = this.hasData.optiond
        // this.form.optiondPic = this.hasData.optiondPic
        //
        // this.form.answerOption = this.hasData.answerOption
        // this.form.detail = this.hasData.detail
        // this.form.detailPic = this.hasData.detailPic
        // this.form.level = this.hasData.level
        // this.form.courseId = this.hasData.courseId
        // this.form.cname = this.hasData.cname
        // this.form.courseId = this.hasData.courseId
        // this.form.remark = this.hasData.remark
        this.form = this.hasData

        this.$axios.post(this.$httpUrl+'/question/getCheck', this.form).then(res=>res.data).then(res=>{
          this.checkList = res.data;
        })
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
        case 3: this.centerDialogVisible3 = !this.centerDialogVisible3; break;
        case 4: this.centerDialogVisible4 = !this.centerDialogVisible4; break;
        case 5: this.centerDialogVisible5 = !this.centerDialogVisible5; break;
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
        list: this.checkList
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
        question: this.form,
        list: this.checkList
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
      this.form.optiona = ''
      this.form.optionaPic = ''
      this.form.optionb = ''
      this.form.optionbPic = ''
      this.form.optionc = ''
      this.form.optioncPic = ''
      this.form.optiond = ''
      this.form.optiondPic = ''
      this.form.answerOption = ''
      this.form.detail = ''
      this.form.detailPic = ''
      this.form.level = 0
      this.form.courseId = ''
      this.form.remark = ''
      this.checkList = []
    },
    back() {
      this.$router.push({path: "/QuestionList"})
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
    uploadOptionAPic(response) {
      console.log(response)
      this.form.optionaPic = response.data
    },
    uploadOptionBPic(response) {
      console.log(response)
      this.form.optionbPic = response.data
    },
    uploadOptionCPic(response) {
      console.log(response)
      this.form.optioncPic = response.data
    },
    uploadOptionDPic(response) {
      console.log(response)
      this.form.optiondPic = response.data
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