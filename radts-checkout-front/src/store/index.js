import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  // strict: process.env.NODE_ENV !== 'production',
  state: {
    openTab: [], // 所有打开的路由
    activeIndex: '/manage/instructions', // 激活状态
    userInfo: {}
  },
  mutations: {
    // 添加tabs
    add_tabs (state, data) {
      if (this.state.openTab.indexOf(data) < 0) {
        this.state.openTab.push(data)
      }
    },
    // 删除tabs
    delete_tabs (state, route) {
      let index = 0
      for (let option of state.openTab) {
        if (option.route === route) {
          break
        }
        index++
      }
      this.state.openTab.splice(index, 1)
    },
    // 设置当前激活的tab
    set_active_index (state, index) {
      this.state.activeIndex = index
    },
    // 设置详情信息
    save_detail_info (state, info) {
      this.state.userInfo = info
    }
  }
  // modules: {

  // },
  // getters: {

  // },
  // actions: {

  // }
})
