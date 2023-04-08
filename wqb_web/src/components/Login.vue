<template>
    <div class="loginBody">
        <div class="loginDiv">
            <div class="login-content">
                <h1 class="login-title">用户登录</h1>
                <el-form :model="loginForm" label-width="100px"
                         :rules="rules" ref="loginForm">
                    <el-form-item label="账号" prop="no">
                        <el-input style="width: 200px" type="text" v-model="loginForm.sno"
                                  autocomplete="off" size="small"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input style="width: 200px" type="password" v-model="loginForm.spassword"
                                  show-password autocomplete="off" size="small" @keyup.enter.native="confirm"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <el-button type="primary" @click="confirm" :disabled="confirm_disabled">确 定</el-button>
                      <el-button type="danger" @click="register()">注册</el-button>
                    </el-form-item>

                </el-form>
            </div>
        </div>
    </div>
</template>


<script>

    export default {
        name: "Login",
        data(){
            return{
              categoryOptions: [],
                confirm_disabled:false,
                loginForm:{
                    sno:'',
                    spassword:''
                },
                rules:{
                    sno: [
                        { required: true, message: '请输入账号', trigger: 'blur' }
                    ],
                    spassword: [
                        { required: true, message: '请输密码', trigger: 'blur' }
                    ],
                }
            }

        },

        methods:{
          register () { // 跳转到注册界面
            this.$axios.get(this.$httpUrl+'/grade/list').then(res=>res.data).then(res=>{

              this.categoryOptions = res.data;
              console.log(this.categoryOptions)
              this.$router.push({path: '/register', query: {index:this.categoryOptions}});
            })
          },

          getOneCategory() {
            this.$axios.get(this.$httpUrl+'/grade/list').then(res=>res.data).then(res=>{

              this.categoryOptions = res.data;
              console.log(this.categoryOptions)// 有数据
            })
          },

            confirm(){
                this.confirm_disabled=true;
                this.$refs.loginForm.validate((valid) => {
                    if (valid) { //valid成功为true，失败为false
                        //去后台验证用户名密码
                        this.$axios.post(this.$httpUrl+'/student/login',this.loginForm).then(res=>res.data).then(res=>{
                            console.log(res)
                            if(res.code==200){
                                //存储
                                sessionStorage.setItem("CurUser",JSON.stringify(res.data.student))

                                // console.log(res.data.menu)
                                // console.log(res.data.student)
                                this.$store.commit("setMenu",res.data.menu)
                                //跳转到主页
                                this.$router.replace('/Index');
                            }else{
                                this.confirm_disabled=false;
                                alert('校验失败，用户名或密码错误！');
                                return false;
                            }
                        });
                    } else {
                        this.confirm_disabled=false;
                        console.log('校验失败');
                        return false;
                    }
                });

            }
        }
    }
</script>

<style scoped>
    .loginBody {
        position: absolute;
        width: 100%;
        height: 100%;
        background-color: #B3C0D1;
    }
    .loginDiv {
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -200px;
        margin-left: -250px;
        width: 450px;
        height: 330px;
        background: #fff;
        border-radius: 5%;

    }
    .login-title {
        margin: 20px 0;
        text-align: center;
    }
    .login-content {
        width: 400px;
        height: 250px;
        position: absolute;
        top: 25px;
        left: 25px;
    }
</style>