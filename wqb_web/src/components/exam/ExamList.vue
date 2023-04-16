<template>
  <div>
    <!--    查询-->
    <div style="margin-bottom: 5px;">
      <el-input v-model="name" placeholder="请输入试卷名" suffix-icon="el-icon-search" style="width: 200px;"
                @keyup.enter.native="loadPost"></el-input>
      <el-select v-model="state" filterable placeholder="是否完成" style="margin-left: 5px;">
        <el-option
            v-for="item in states"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>

      <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
      <el-button type="info" style="margin-left: 5px;" @click="resetParam">重置</el-button>
    </div>

    <!--    显示-->
    <el-table
        :data="tableData"
        style="width: 100%"
        :header-cell-style = "{ background: '#f3f6fd', color: '#555'}" border>

      <!--      表格显示-->
      <el-table-column label="试卷名" prop="name" width="240">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>

      <el-table-column label="题目数量" prop="number" width="120">
        <template slot-scope="scope">
          {{scope.row.number}}
        </template>
      </el-table-column>

      <el-table-column label="完成情况" prop="type" width="120">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.state === 0 ? 'danger' : 'success'"
              disable-transitions>{{scope.row.state === 0 ? '未完成' : '已完成'}}</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="得分" prop="mark" width="120">
        <template slot-scope="scope">
          {{scope.row.mark}}
        </template>
      </el-table-column>

      <el-table-column label="创建时间" prop="createTime" width="240"></el-table-column>

      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="viewDetail(scope.row)">详情</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @confirm="del(scope.row.id)"
              style="margin-left: 5px;">
            <el-button slot="reference" type="danger">删除</el-button>
          </el-popconfirm>
<!--          <el-button @click="viewDetail(scope.row)" type="warning" style="margin-left: 5px;">导出</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <!--    分页-->
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10, 20,30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>

  </div>
</template>

<script>
export default {
  name: "ExamList",
  data() {
    return {
      multipleSelection: [],
      tableData: [],
      centerDialogVisible: false,
      pageSize: 10,
      pageNum: 1,
      total: 0,
      states: [
        {
          value: '0',
          label: '未完成'
        }, {
          value: '1',
          label: '已完成'
        }
      ],
      name: '',
      state: '',
      // examForm: {
      //   id: '',
      //   name: '',
      //   studentId: '',
      //   state: 0,
      // },
      studentId: '',
      student: '',
      remark: '',
    }
  },
  methods: {
    del(id){
      // console.log(id)
      this.$axios.get(this.$httpUrl+'/exam/del?id='+id).then(res=>res.data).then(res=>{
        // console.log(res)
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
    viewDetail(row){
      this.$axios.get(this.$httpUrl+'/exam/selectDetail?id='+row.id).then(res=>res.data).then(res=>{
        this.multipleSelection = res.data

        let dataOb = {
          exam: row,
          questionList: this.multipleSelection
        }
        // console.log(dataOb)
        this.$router.push({path: "/ReviewExamDetail", query: {pushData: dataOb}})
      })

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
      this.name = ''
      this.state = ''
      this.loadPost()
    },
    loadPost(){
      this.$axios.post(this.$httpUrl+'/exam/selectByParam',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          name: this.name,
          state: this.state,
          studentId: this.studentId
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
    init(){
      this.student = JSON.parse(sessionStorage.getItem('CurUser'))
      this.studentId = this.student.sid
    },
  },
  beforeMount() {
    this.init()
    this.loadPost()
  }
}
</script>

<style scoped>

</style>