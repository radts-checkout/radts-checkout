import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/home/Login'
import Index from '@/components/home/Index'
import Instructions from '@/components/manage/Instructions'
import page1 from '@/components/manage/page1'
import page2 from '@/components/manage/page2'
import page3 from '@/components/manage/page3'
import page4 from '@/components/manage/page4'

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
          component: Instructions
        },
        {
          path: '/manage/page1',
          name: 'page1',
          component: page1
        },
        {
          path: '/manage/page2',
          name: 'page2',
          component: page2
        },
        {
          path: '/manage/page3',
          name: 'page3',
          component: page3
        },
        {
          path: '/manage/page4',
          name: 'page4',
          component: page4
        }
      ]
    }
  ]
})
