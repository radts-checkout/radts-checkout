import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/home/Login'
import Index from '@/components/home/Index'
import Instructions from '@/components/manage/Instructions'
import Setting from '@/components/manage/Setting'
import page1 from '@/components/manage/page1'
import page2 from '@/components/manage/page2'
import page3 from '@/components/manage/page3'
import page4 from '@/components/manage/page4'
// import { CookieUtil } from '@/utils/tools'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/index',
      name: 'Index',
      component: Index,
      redirect: '/manage/instructions',
      children: [
        {
          path: '/manage/instructions',
          name: '使用说明',
          component: Instructions,
          meta: {
            requireAuth: true // 添加该字段，表示进入这个路由是需要登录的
          }
        },
        {
          path: '/manage/setting',
          name: '参数设置',
          component: Setting,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/manage/page1',
          name: 'page1',
          component: page1,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/manage/page2',
          name: 'page2',
          component: page2,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/manage/page3',
          name: 'page3',
          component: page3,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/manage/page4',
          name: 'page4',
          component: page4,
          meta: {
            requireAuth: true
          }
        }
      ]
    }
  ]
})

// Router.beforeEach((to, from, next) => {
//   let radtsToken = CookieUtil.getCookie('radts_token')
//   if (!radtsToken) {
//     next({
//       path: '/',
//       query: {redirect: to.fullPath}// 将跳转的路由path作为参数，登录成功后跳转到该路由
//     })
//   } else {
//     next({
//       path: '/',
//       query: {redirect: to.fullPath}// 将跳转的路由path作为参数，登录成功后跳转到该路由
//     })
//   }
// })
