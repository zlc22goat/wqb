<template>
  <div class="box">
    <div class="zhuce">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="账户名" prop="sno">
          <el-input v-model="ruleForm.sno" clearable></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="sname">
          <el-input v-model="ruleForm.sname" clearable></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="ruleForm.gender">
            <el-radio label="1">男</el-radio>
            <el-radio label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="专业" prop="major">
          <el-input v-model="ruleForm.major" clearable></el-input>
        </el-form-item>
        <el-form-item label="年级" prop="gradeName">
          <el-select v-model="ruleForm.gradeid" filterable placeholder="请选择年级" style="margin-left: 5px;">
            <el-option
                v-for="item in categoryOptions"
                :key="item.gradeId"
                :label="item.gradeName"
                :value="item.gradeId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="ruleForm.phone" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="spassword">
          <el-input type="password" v-model="ruleForm.spassword" clearable></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">注册</el-button>
          <el-button @click="returnForm">取消</el-button>
<!--          <el-button @click="test">ok</el-button>-->
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
export default {
  name: "Register",
  data() {
    let checkDuplicate =(rule,value,callback)=>{
      if(this.ruleForm.sid){
        return callback();
      }
      this.$axios.get(this.$httpUrl+"/student/findByNo?no="+this.ruleForm.sno).then(res=>res.data).then(res=>{
        if(res.code!=200){

          callback()
        }else{
          callback(new Error('账号已经存在'));
        }
      })
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm.spassword) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      categoryOptions: this.$route.query.index,
      total: 0,
      sno: '',
      sname: '',
      gender: '',
      genders: [
        {
          value: '0',
          label: '女'
        }, {
          value: '1',
          label: '男'
        }
      ],
      ruleForm: {
        sid: '',
        sno: '',
        sname: '',
        gender: '1',
        major: '',
        gradeid: '',
        gradeName: '',
        phone: '',
        spassword: '',
        checkPass: ''
      },
      rules: {
        sno: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'},
          {validator:checkDuplicate,trigger: 'blur'}
        ],
        sname: [
          {required: true, message: '请输入名字', trigger: 'blur'}
        ],
        spassword: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ],
        phone: [
          {required: true,message: "手机号不能为空",trigger: "blur"},
          {pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur"}
        ],
        checkPass: [
          {required: true, message: '请确认密码', trigger: 'blur'},
            { validator: validatePass, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // test() {
    //   this.categoryOptions = this.$route.query.index
    //   console.log(this.categoryOptions)
    // },
    getOneCategory() {
      this.$axios.get(this.$httpUrl+'/grade/list').then(res=>res.data).then(res=>{
        console.log(res)
        this.categoryOptions = res.data;
      })
    },

    returnForm() {
      // 返回login界面
      this.$router.push('/login')
    },

    submitForm(){
      this.$axios.post(this.$httpUrl+'/student/register', this.ruleForm).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code==200){
          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.$refs.ruleForm.resetFields();
          this.returnForm()
          alert('注册成功！')
        }else{
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
          alert('出问题啦！！！')
        }

      })
    },
  }
}
</script>
<style scoped>
.box {
  height: 100%;
  background-color: #2e4e6e;
}
.zhuce {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 450px;
  height: 450px;
  background-color: #fff;
  border-radius: 3px;
}
.el-form-item {
  margin-top: 30px;
  width: 400px;
}
</style>

