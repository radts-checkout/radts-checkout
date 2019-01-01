// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css' // 避免后期打包样式不同，要放在import App from './App';之前
import '../static/style/index.scss' // global css
import App from './App'
import router from './router'

var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8083/api'
// 将API方法绑定到全局
Vue.prototype.$axios = axios
Vue.use(ElementUI)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
