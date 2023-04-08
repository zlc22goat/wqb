<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {

  },
  data() {
    return {
      student: JSON.parse(sessionStorage.getItem('CurUser'))
    }
  },
  watch: { // 监视对象
    '$store.state.menu': {
      handler(val, old) {
        console.log(val)
        console.log(old)
        if (!old && this.student && this.student.sno) {
          this.$store.commit("setRouter",val)
        }
      },
      immediate: true
    }
  }
}
</script>

<style>
#app {
  height: 100%;
}
</style>
