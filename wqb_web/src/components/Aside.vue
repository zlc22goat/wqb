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

    <el-menu-item index="/Admin">
      <i class="el-icon-s-opportunity"></i>
      <span slot="title">学生管理</span>
    </el-menu-item>

    <el-menu-item index="/User">
      <i class="el-icon-s-custom"></i>
      <span slot="title">导航二</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      // isCollapse: false
    }
  },
  props: {
    isCollapse: Boolean
  }
}
</script>

<style scoped>

</style>