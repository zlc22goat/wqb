<template>
  <el-menu
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          style="height: 100vh"
          default-active="/Home"
          :collapse="isCollapse"
          :collapse-transition="false"
          router
      >
        <el-menu-item index="/Home">
          <i class="el-icon-s-home"></i>
          <span slot="title">首页</span>
        </el-menu-item>

        <el-submenu :index="'/'+item.menuclick" v-for="(item,i) in menuData" :key="i">
          <template slot="title">
            <i :class="item.menuicon"></i>
            <span slot="title">{{item.menuname}}</span>
          </template>

          <el-menu-item :index="'/'+subItem.menuclick"
                        v-for="subItem in item.childMenu"
                        :key="subItem.menuclick">
            <template slot="title">
              <span slot="title">{{subItem.menuname}}</span>
            </template>

          </el-menu-item>
        </el-submenu>
      </el-menu>
</template>

<script>
export default {
  name: "Aside",
  data(){
    return {
      menuData: [],
      student: JSON.parse(sessionStorage.getItem('CurUser'))
    }
  },
  created() {
    this.getMenuList()
  },
  methods: {
    getMenuList() {
      this.$axios.get(this.$httpUrl + "/menu/getAllMenu?roleId=" + this.student.roleid).then(res=>res.data).then(res=>{
        if (res.code != 200) return this.$message.error("获取失败")
        this.menuData = res.data
        // console.log(this.menuData)
      })
    }
  },

  // computed:{
  //   "menuData":{
  //     get(){
  //       return this.$store.state.menu
  //     }
  //   }
  // },
  props:{
    isCollapse:Boolean
  }
}
</script>

<style scoped>

</style>