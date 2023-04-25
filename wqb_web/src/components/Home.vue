<template>
    <div style="text-align: center;background-color: #f1f1f3;height: 100%;padding: 0px;margin: 0px;">
        <h1 style="font-size: 50px;">{{'欢迎你！'+student.sname}}</h1>
        <el-descriptions  title="个人中心" :column="2" size="40" border>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-s-custom"></i>
                    账号
                </template>
                {{student.sno}}
            </el-descriptions-item>
          <el-descriptions-item v-if="student.roleid === 0">
            <template slot="label">
              <i class="el-icon-phone"></i>
              电话
            </template>
            {{student.phone}}
          </el-descriptions-item>
            <el-descriptions-item v-if="student.roleid !== 0">
                <template slot="label">
                    <i class="el-icon-coordinate"></i>
                    班级
                </template>
                {{student.name}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-location-outline"></i>
                    性别
                </template>
                <el-tag :type="student.gender == '1' ? 'primary' : 'danger'" disable-transitions>
                  <i :class="student.gender==1?'el-icon-male':'el-icon-female'"></i>
                  {{student.gender==1?"男":"女"}}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-tickets"></i>
                    角色
                </template>
                <el-tag
                        type="success"
                        disable-transitions>{{student.roleid===0?"管理员":(student.roleid===1?"学生":"班主任")}}</el-tag>

            </el-descriptions-item>

          <el-descriptions-item v-if="student.roleid === 1">
            <template slot="label">
              <i class="el-icon-school"></i>
              专业
            </template>
            {{student.major}}
          </el-descriptions-item>
          <el-descriptions-item v-if="student.roleid === 1">
            <template slot="label">
              <i class="el-icon-s-management"></i>
              年级
            </template>
            {{student.gradeName}}
          </el-descriptions-item>
        </el-descriptions>

        <DateUtils></DateUtils>
      <Marquee style="font-size: 40px;"></Marquee>
    </div>
</template>

<script>
    import DateUtils from "./DateUtils";
    import Marquee from "@/components/Marquee.vue";
    export default {
        name: "Home",
        components: {Marquee, DateUtils},
        data() {

            return {
                student: {},
                message: []
            }
        },
        computed:{

        },
        methods:{
          init(){
            this.student = JSON.parse(sessionStorage.getItem('CurUser'))
          },

        },
        created(){
            this.init()
        }
    }
</script>

<style scoped>
    .el-descriptions{
        width:90%;

        margin: 0 auto;
        text-align: center;
    }
</style>