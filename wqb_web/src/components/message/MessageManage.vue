<template>
  <div>
    <div style="margin-bottom: 5px;">
      <el-input v-model="context" placeholder="请输入想查询的通知内容" suffix-icon="el-icon-search" style="width: 200px;"
                @keyup.enter.native="loadPost"></el-input>
      <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
      <el-button type="info" style="margin-left: 5px;" @click="resetParam">重置</el-button>
      <el-button type="success" style="margin-left: 5px;" @click="add">新增</el-button>
    </div>
    <el-table :data="tableData" :header-cell-style = "{ background: '#f3f6fd', color: '#555'}" border>
      <el-table-column prop="messageId" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="context" label="通知内容" width="600">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="300"></el-table-column>
      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @confirm="del(scope.row.messageId)"
              style="margin-left: 5px;"
          >
            <el-button slot="reference" size="small" type="danger">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
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

      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="通知内容" prop="context">
          <el-col :span="20">
            <el-input v-model="form.context"></el-input>
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
  name: "MessageManage",
  data() {
    return {
      tableData: [],
      pageSize: 10,
      pageNum: 1,
      total: 0,
      student: '',
      squadCategoryOptions: [],
      messageId:'',
      context: '',
      createTime: '',
      centerDialogVisible: false,
      form: {
        messageId:'',
        context: '',
        createTime: '',
        squadId: '',
      }
    }
  },
  methods:{
    resetForm() {
      this.$refs.form.resetFields();
      this.form.messageId = ''
    },
    del(MessageId){

      this.$axios.get(this.$httpUrl+'/message/del?id='+MessageId).then(res=>res.data).then(res=>{
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
      // console.log(row)
      this.centerDialogVisible = true
      this.$nextTick(()=>{
        //赋值到表单
        this.form = row
      })
    },
    add(){
      this.centerDialogVisible = true
      this.$nextTick(()=>{
        this.resetForm()
      })

    },
    doSave(){
      this.form.squadId = this.student.squadId
      this.$axios.post(this.$httpUrl+'/message/save',this.form).then(res=>res.data).then(res=>{
        console.log("dosave")
        // console.log(this.form)
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
      this.$axios.post(this.$httpUrl+'/message/update',this.form).then(res=>res.data).then(res=>{
        // console.log(res)
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
          // console.log(this.form.messageId)
          if(this.form.messageId){
            console.log("mod")
            this.doMod();
          }else{
            console.log("save")
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
      this.context=''
      this.loadPost()
    },
    getSquadCategory() {
      this.$axios.get(this.$httpUrl+'/squad/list').then(res=>res.data).then(res=>{
        // console.log(res)
        this.squadCategoryOptions = res.data;
      })
    },
    loadPost(){
      this.$axios.post(this.$httpUrl+'/message/selectByContext',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          context:this.context,
          squadId: this.student.squadId
        }
      }).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code===200){
          this.tableData=res.data
          this.total=res.total
        }else{
          alert('获取数据失败')
        }

      })
    },
  },
  beforeMount() {
    this.student = JSON.parse(sessionStorage.getItem('CurUser'))
    this.loadPost()
  }
}
</script>

<style scoped>

</style>