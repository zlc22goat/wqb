<template>
  <div>
    <div style="margin-bottom: 5px;">
      <el-input v-model="body" placeholder="请输入题干" suffix-icon="el-icon-search" style="width: 200px;"
                @keyup.enter.native="loadPost"></el-input>
      <el-select v-model="mastery" filterable placeholder="请选择掌握程度" style="margin-left: 5px;">
        <el-option
            v-for="item in masterys"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>

      <el-select v-model="courseId" filterable placeholder="请选择科目" style="margin-left: 5px;">
        <el-option
            v-for="item in categoryOptions"
            :key="item.cid"
            :label="item.cname"
            :value="item.cid">
        </el-option>
      </el-select>

      <el-select v-model="type" filterable placeholder="请选择题型" style="margin-left: 5px;">
        <el-option
            v-for="item in types"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>

      <el-select v-model="level" filterable placeholder="请选择难度" style="margin-left: 5px;">
        <el-option
            v-for="item in levels"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>

      <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
      <el-button type="info" style="margin-left: 5px;" @click="resetParam">重置</el-button>
    </div>
    <el-table
        :data="tableData"
        style="width: 100%" :header-cell-style = "{ background: '#f3f6fd', color: '#555'}" border>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">

            <el-form-item label="题干:">
              <td>{{props.row.body}}</td>
              <el-image :src="props.row.bodyPic" v-if="props.row.bodyPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-form-item><br>

            <el-form-item label="A:" v-if="props.row.type === 0">
              <td>{{ props.row.optiona }}</td>
              <el-image :src="props.row.optionaPic" v-if="props.row.optionaPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-form-item>

            <el-form-item label="B:" v-if="props.row.type === 0">
              <td>{{ props.row.optionb }}</td>
              <el-image :src="props.row.optionbPic" v-if="props.row.optionbPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-form-item>

            <el-form-item label="C:" v-if="props.row.type === 0">
              <td>{{ props.row.optionc }}</td>
              <el-image :src="props.row.optioncPic" v-if="props.row.optioncPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-form-item>

            <el-form-item label="D:" v-if="props.row.type === 0">
              <td>{{ props.row.optiond }}</td>
              <el-image :src="props.row.optiondPic" v-if="props.row.optiondPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-form-item><br>

            <el-form-item label="答案:" v-if="props.row.type === 0">
              <td>{{ props.row.answerOption }}</td>
            </el-form-item>
            <br>

            <el-form-item label="答案:" v-if="props.row.type === 1">
              <td>{{ props.row.answer }}</td>
              <el-image :src="props.row.answerPic" v-if="props.row.answerPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-form-item>

            <el-form-item label="解析:">
              <td>{{ props.row.detail }}</td>
              <el-image :src="props.row.detailPic" v-if="props.row.detailPic !== ''">
                <div slot="error" class="image-slot"></div>
              </el-image>
            </el-form-item>

          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="学科" prop="cName" width="100">
        <template slot-scope="scope">
          {{scope.row.cname}}
        </template>
      </el-table-column>
      <el-table-column label="题型" prop="type" width="110">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.type === 1 ? 'primary' : 'success'"
              disable-transitions>{{scope.row.type === 1 ? '填空解答题' : '选择题'}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="难度" prop="level" width="150">
        <template slot-scope="scope">
          <div class="block">
            <el-rate v-model="scope.row.level" disabled></el-rate>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="题干" prop="body"></el-table-column>

      <el-table-column label="掌握程度" prop="mastery" width="100">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.mastery === 0 ? 'danger' :
              (scope.row.mastery === 1 ? 'info' : 'success')"
              disable-transitions>{{scope.row.mastery === 0 ? '完全不会' :
              (scope.row.mastery === 1 ? '略懂一二' : '已然掌握')}}</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="创建时间" prop="createTime" width="200"></el-table-column>
      <el-table-column label="最后更新时间" prop="updateTime" width="200"></el-table-column>
      <el-table-column prop="operate" label="操作" width="180">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="doMod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @confirm="del(scope.row.id)"
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
  </div>

</template>

<script>
export default {
  name: "QuestionList",
  data() {
    return {
      tableData: [],
      categoryOptions: [],
      pageSize: 10,
      pageNum: 1,
      total: 0,
      mastery: '',
      masterys: [
        {
          value: '0',
          label: '完全不会'
        }, {
          value: '1',
          label: '略懂一二'
        }, {
          value: '2',
          label: '已然掌握'
        }
      ],
      type: '',
      types: [
        {
          value: '0',
          label: '选择题'
        }, {
          value: '1',
          label: '填空解答题'
        }
      ],
      level: '',
      levels: [
        {
          value: '1',
          label: '容易'
        }, {
          value: '2',
          label: '较易'
        }, {
          value: '3',
          label: '中等'
        }, {
          value: '4',
          label: '较难'
        }, {
          value: '5',
          label: '困难'
        }
      ],
      student: '',
      id: '',
      body: '',
      courseId: '',
      cName: '',
      studentId: '',
      remark: '',
    }
  },
  methods: {
    del(id){
      console.log(id)
      this.$axios.get(this.$httpUrl+'/question/del?id='+id).then(res=>res.data).then(res=>{
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
    doMod(row){
      if (row.type === 0) {
        this.$router.push({path: "/AddOption", query: {index: row}})
      } else {
        this.$router.push({path: "/AddOther", query: {index: row}})
      }
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
      this.body=''
      this.type=''
      this.courseId=''
      this.mastery=''
      this.level=''
      this.loadPost()
    },
    loadPost(){
      this.$axios.post(this.$httpUrl+'/question/selectByParam',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          body: this.body,
          level: this.level,
          courseId: this.courseId,
          type: this.type,
          studentId: this.student.sid,
          mastery: this.mastery
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
      this.$axios.get(this.$httpUrl+'/course/list').then(res=>res.data).then(res=>{
        console.log(res)
        this.categoryOptions = res.data;
      })
    },
    init(){
      this.student = JSON.parse(sessionStorage.getItem('CurUser'))
    },
  },
  beforeMount() {
    this.init()
    this.loadPost()
    this.getOneCategory()
  }
}
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
  width: 30%;
}
</style>