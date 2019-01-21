// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '../static/css/rest.scss' // rsest css
import '../static/css/index.scss' // global css
import '../static/fonts/iconfont.css' // fonts css
import App from './App'
import router from './router'
import store from './store'
import { CookieUtil } from '@/utils/tools'
import VueHighlightJS from 'vue-highlightjs'

var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8083/api'

// 将API方法绑定到全局

Vue.prototype.$axios = axios
Vue.use(ElementUI)
Vue.use(VueHighlightJS)
Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requireAuth)) { // 判断该路由是否需要登录权限
    // if (!sessionStorage.getItem('radts_token')) {
    if (!CookieUtil.getCookie('radts_token')) {
      next({
        path: '/',
        query: {redirect: to.fullPath}// 将跳转的路由path作为参数，登录成功后跳转到该路由
      })
    } else {
      next()
    }
  } else {
    next()
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
