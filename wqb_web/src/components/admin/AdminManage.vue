<template>
    <div>
        <div style="margin-bottom: 5px;">
            <el-input v-model="sname" placeholder="请输入姓名" suffix-icon="el-icon-search" style="width: 200px;"
                      @keyup.enter.native="loadPost"></el-input>
            <el-select v-model="gender" filterable placeholder="请选择性别" style="margin-left: 5px;">
                <el-option
                        v-for="item in genders"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
          <el-select v-model="squadId" filterable placeholder="请选择班级" style="margin-left: 5px;">
            <el-option
                v-for="item in squadCategoryOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-select>
            <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
            <el-button type="info" style="margin-left: 5px;" @click="resetParam">重置</el-button>
            <el-button type="success" style="margin-left: 5px;" @click="add">新增</el-button>
        </div>
      <el-table :data="tableData" :header-cell-style = "{ background: '#f3f6fd', color: '#555'}" border>
        <el-table-column prop="sno" label="账号" width="160">
        </el-table-column>
        <el-table-column prop="sname" label="姓名" width="120">
        </el-table-column>
        <el-table-column prop="gender" label="性别" width="60">
          <template slot-scope="scope">
            <el-tag
                :type="scope.row.gender === 1 ? 'primary' : 'success'"
                disable-transitions>{{scope.row.gender === 1 ? '男' : '女'}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="班级" width="150"></el-table-column>
<!--        <el-table-column prop="major" label="专业" width="60"></el-table-column>-->
<!--        <el-table-column prop="gradeName" label="年级" width="180"></el-table-column>-->
        <el-table-column prop="phone" label="电话" width="280"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="280"></el-table-column>
<!--        <el-table-column prop="analysis" label="学情分析">-->
<!--          <template slot-scope="scope">-->
<!--            <el-button size="small" type="warning" @click="analysisQuestion(scope.row)">错题分析</el-button>-->
<!--            <el-button size="small" type="warning" @click="analysisExam(scope.row)">试卷分析</el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
        <el-table-column prop="operate" label="操作">
          <template slot-scope="scope">
            <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
            <el-popconfirm
                title="确定删除吗？"
                @confirm="del(scope.row.sid)"
                style="margin-left: 5px;"
            >
              <el-button slot="reference" size="small" type="danger">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
<!--        控制分页-->
      <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[5, 10, 20,30]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>

        <el-dialog
                title="操作"
                :visible.sync="centerDialogVisible"
                width="30%"
                center>

            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="账号" prop="sno">
                    <el-col :span="20">
                        <el-input v-model="form.sno"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="姓名" prop="sname">
                    <el-col :span="20">
                        <el-input v-model="form.sname"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="密码" prop="spassword">
                    <el-col :span="20">
                        <el-input type="password" v-model="form.spassword" show-password autocomplete="off"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                    <el-radio-group v-model="form.gender">
                        <el-radio label="1" value="1" :key="1">男</el-radio>
                        <el-radio label="0" value="0" :key="0">女</el-radio>
                    </el-radio-group>
                </el-form-item>
<!--                <el-form-item label="专业" prop="major">-->
<!--                  <el-col :span="20">-->
<!--                    <el-input v-model="form.major"></el-input>-->
<!--                  </el-col>-->
<!--                </el-form-item>-->
<!--                <el-form-item label="年级" prop="gradeName">-->
<!--&lt;!&ndash;                  这里的form.gradeid是将要提交上去的，赋值给student中的gradeid字段&ndash;&gt;-->
<!--&lt;!&ndash;                  而下面的item.gradeId是从grade中查出的，id名为gradeId，区分一下&ndash;&gt;-->
<!--                  <el-select v-model="form.gradeid" filterable placeholder="请选择年级" style="margin-left: 5px;">-->
<!--                    <el-option-->
<!--                        v-for="item in categoryOptions"-->
<!--                        :key="item.gradeId"-->
<!--                        :label="item.gradeName"-->
<!--                        :value="item.gradeId">-->
<!--                    </el-option>-->
<!--                  </el-select>-->
<!--                </el-form-item>-->
              <el-form-item label="班级" prop="name">
                  <el-select v-model="form.squadId" filterable placeholder="请选择班级" style="margin-left: 5px;">
                    <el-option
                        v-for="item in squadCategoryOptions"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="联系电话" prop="phone">
                    <el-col :span="20">
                        <el-input v-model="form.phone"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="centerDialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "AdminManage",
        data() {
            let checkDuplicate =(rule,value,callback)=>{
                if(this.form.sid){
                    return callback();
                }
                this.$axios.get(this.$httpUrl+"/student/findByNo?no="+this.form.sno).then(res=>res.data).then(res=>{
                    if(res.code!=200){

                        callback()
                    }else{
                        callback(new Error('账号已经存在'));
                    }
                })
            };

            return {
              tableData: [],
              categoryOptions: [],
              squadCategoryOptions: [],
              pageSize: 10,
              pageNum: 1,
              total: 0,
              sno: '',
              sname: '',
              spassword:'',
              squadId: '',
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
              centerDialogVisible: false,
              form: {
                sid:'',
                sno:'',
                sname:'',
                spassword:'',
                phone:'',
                gender: '',
                major: '',
                createTime: '',
                gradeName: '',
                roleid: '',
                gradeid: '',
                squadId: '',
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
                    ]
                }
            }
        },
        methods:{
            resetForm() {
                this.$refs.form.resetFields();
                this.form.sid = ''
                this.form.gradeid = ''
            },
            del(sid){
                console.log(sid)

                this.$axios.get(this.$httpUrl+'/student/del?id='+sid).then(res=>res.data).then(res=>{
                    console.log(res)
                    if(res.code==200){

                        this.$message({
                            message: '操作成功！',
                            type: 'success'
                        });
                        this.loadPost()
                    }else{
                        this.$message({
                            message: '操作失败！',
                            type: 'error'
                        });
                    }

                })
            },
            mod(row){
                this.getOneCategory()
                this.getSquadCategory()
                this.centerDialogVisible = true
                this.$nextTick(()=>{
                  this.form = row
                  //赋值到表单
                  this.form.gender = row.gender +''
                })
            },
            add(){
                this.getOneCategory() // 查询grade列表
                this.getSquadCategory()
                this.centerDialogVisible = true
                this.$nextTick(()=>{
                    this.resetForm()
                })

            },
            doSave(){
                this.$axios.post(this.$httpUrl+'/student/saveTea',this.form).then(res=>res.data).then(res=>{
                    console.log("dosave")
                    console.log(this.form)
                    if(res.code===200){

                        this.$message({
                            message: '操作成功！',
                            type: 'success'
                        });
                        this.centerDialogVisible = false
                        this.loadPost()
                        this.resetForm()
                    }else{
                        this.$message({
                            message: '操作失败！',
                            type: 'error'
                        });
                    }

                })
            },
            doMod(){
                this.$axios.post(this.$httpUrl+'/student/update',this.form).then(res=>res.data).then(res=>{
                    console.log(res)
                    if(res.code==200){

                        this.$message({
                            message: '操作成功！',
                            type: 'success'
                        });
                        this.centerDialogVisible = false
                        this.loadPost()
                        this.resetForm()
                    }else{
                        this.$message({
                            message: '操作失败！',
                            type: 'error'
                        });
                    }

                })
            },
            save(){
              // console.log(this.form)
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        if(this.form.sid){
                          console.log("mod"+this.form)
                            this.doMod();
                        }else{
                          console.log("save")
                          console.log(this.form)
                            this.doSave();
                        }
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });

            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pageNum=1
                this.pageSize=val
                this.loadPost()
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageNum=val
                this.loadPost()
            },
            resetParam(){
                this.sname=''
                this.gender=''
                this.squadId=''
                this.loadPost()
            },
            loadPost(){
                this.$axios.post(this.$httpUrl+'/student/selectByName',{
                    pageSize:this.pageSize,
                    pageNum:this.pageNum,
                    param:{
                        sname:this.sname,
                        gender:this.gender,
                        roleid: 2,
                        squadId: this.squadId
                    }
                }).then(res=>res.data).then(res=>{
                    console.log(res)
                    if(res.code==200){
                        this.tableData=res.data
                        this.total=res.total
                    }else{
                        alert('获取数据失败')
                    }

                })
            },
          getOneCategory() {
            this.$axios.get(this.$httpUrl+'/grade/list').then(res=>res.data).then(res=>{
              // console.log(res)
              this.categoryOptions = res.data;
            })
          },
          getSquadCategory() {
            this.$axios.get(this.$httpUrl+'/squad/list').then(res=>res.data).then(res=>{
              // console.log(res)
              this.squadCategoryOptions = res.data;
            })
          },
          analysisQuestion(row) {
            this.$router.push({path: "/AnalysisQue", query: {pushData: row}})
          },
          analysisExam(row) {
            this.$router.push({path: "/AnalysisExam", query: {pushData: row}})
          },
        },
        beforeMount() {
            this.getSquadCategory()
            this.loadPost()
        }
    }
</script>

<style scoped>

</style>