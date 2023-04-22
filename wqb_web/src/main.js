import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css';
import axios from 'axios';// 跨域
import VueRouter from 'vue-router';
import htmlToPdf from '@/utils/htmlToPdf'
import router from "@/router";
import store from "./store"
import * as echarts from 'echarts';

Vue.prototype.$echarts = echarts
Vue.prototype.$httpUrl = 'http://localhost:8092';
Vue.prototype.$axios = axios;
Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(VueRouter);
Vue.use(htmlToPdf)

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
