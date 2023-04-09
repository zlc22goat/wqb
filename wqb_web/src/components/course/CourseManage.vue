<template>
  <div>
    <div style="margin-bottom: 5px;">
      <el-input v-model="cname" placeholder="请输入想查询的课程名" suffix-icon="el-icon-search" style="width: 200px;"
                @keyup.enter.native="loadPost"></el-input>
      <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
      <el-button type="info" style="margin-left: 5px;" @click="resetParam">重置</el-button>
      <el-button type="success" style="margin-left: 5px;" @click="add">新增</el-button>
    </div>
    <el-table :data="tableData" :header-cell-style = "{ background: '#f3f6fd', color: '#555'}" border>
      <el-table-column prop="cid" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="cname" label="课程名" width="600">
      </el-table-column>
      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @confirm="del(scope.row.cid)"
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
        <el-form-item label="课程名" prop="cname">
          <el-col :span="20">
            <el-input v-model="form.cname"></el-input>
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
  name: "CourseManage",
  data() {
    return {
      tableData: [],
      pageSize: 10,
      pageNum: 1,
      total: 0,
      cid:'',
      cname: '',
      centerDialogVisible: false,
      form: {
        cid:'',
        cname: ''
      }
    }
  },
  methods:{
    resetForm() {
      this.$refs.form.resetFields();
      this.form.cid = ''
      this.form.cname = ''
    },
    del(cid){

      this.$axios.get(this.$httpUrl+'/course/del?id='+cid).then(res=>res.data).then(res=>{
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
      console.log(row)
      this.centerDialogVisible = true
      this.$nextTick(()=>{
        //赋值到表单
        this.form.cid = row.cid
        this.form.cname = row.cname
      })
    },
    add(){
      this.centerDialogVisible = true
      this.$nextTick(()=>{
        this.resetForm()
      })

    },
    doSave(){
      this.$axios.post(this.$httpUrl+'/course/save',this.form).then(res=>res.data).then(res=>{
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
      this.$axios.post(this.$httpUrl+'/course/update',this.form).then(res=>res.data).then(res=>{
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
          console.log(this.form.cid)
          if(this.form.cid){
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
    loadGet(){
      this.$axios.get(this.$httpUrl+'/course/list').then(res=>res.data).then(res=>{
        console.log(res)
      })
    },
    resetParam(){
      this.cname=''
      this.loadPost()
    },
    loadPost(){
      this.$axios.post(this.$httpUrl+'/course/selectByName',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          cname:this.cname
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
    //this.loadGet();
    this.loadPost()
  }
}
</script>

<style scoped>

</style>