<template>
    <div style="text-align: center;background-color: #f1f1f3;height: 100%;padding: 0px;margin: 0px;">
        <h1 style="font-size: 50px;">{{'欢迎你！'+user.sname}}</h1>
        <el-descriptions  title="个人中心" :column="2" size="40" border>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-s-custom"></i>
                    账号
                </template>
                {{user.sno}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-mobile-phone"></i>
                    电话
                </template>
                {{user.phone}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-location-outline"></i>
                    性别
                </template>
                <el-tag :type="user.gender == '1' ? 'primary' : 'danger'" disable-transitions>
                  <i :class="user.gender==1?'el-icon-male':'el-icon-female'"></i>
                  {{user.gender==1?"男":"女"}}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-tickets"></i>
                    角色
                </template>
                <el-tag
                        type="success"
                        disable-transitions>{{user.roleid===0?"管理员":"学生"}}</el-tag>

            </el-descriptions-item>

          <el-descriptions-item v-if="user.roleid === 1">
            <template slot="label">
              <i class="el-icon-school"></i>
              专业
            </template>
            {{user.major}}
          </el-descriptions-item>
          <el-descriptions-item v-if="user.roleid === 1">
            <template slot="label">
              <i class="el-icon-s-management"></i>
              年级
            </template>
            {{user.gradeName}}
          </el-descriptions-item>
        </el-descriptions>

        <DateUtils></DateUtils>
    </div>
</template>

<script>
    import DateUtils from "./DateUtils";
    export default {
        name: "Home",
        components: {DateUtils},
        data() {

            return {
                user:{}
            }
        },
        computed:{

        },
        methods:{
          init(){
            this.user = JSON.parse(sessionStorage.getItem('CurUser'))
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