<template>
  <el-container style="height: 100%; border: 1px solid #eee">
<!--    边框部分-->
    <el-aside :width="aside_width" style="background-color: rgb(238, 241, 246); height: 100%; margin-left: -1px">
      <!--设置边框属性 变量isCollapse是否折叠       -->
      <Aside :isCollapse="isCollapse"></Aside>
    </el-aside>

    <el-container style="height: 100%">
<!--      头部-->
      <el-header style="text-align: right; font-size: 12px; height: 100%; border-bottom: rgba(64,64,64,0.3) 1px solid">
        <Header @doCollapse="doCollapse" :icon="icon"></Header>
      </el-header>

<!--      页面主要部分-->
      <el-main style="height: 100%">
        <router-view/>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";
export default {
  name: "Index",
  components: {Header, Aside},
  methods: {
    doCollapse() {
      this.isCollapse = !this.isCollapse
      if(!this.isCollapse) {// 展开状态
        this.aside_width = '200px'
        this.icon = 'el-icon-s-fold'
      } else {
        this.aside_width = '64px'
        this.icon = 'el-icon-s-unfold'
      }
    }
  },
  data() {
    return {
      isCollapse: false, // 默认值
      aside_width: '200px',
      icon: 'el-icon-s-fold'
    }
  }
}
</script>

<style scoped>
.el-header {
  #background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-main {
  padding: 5px;
}

.el-aside {
  color: #333;
}
</style>