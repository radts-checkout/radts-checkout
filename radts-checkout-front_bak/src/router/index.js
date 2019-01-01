import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/manage/Login'
import index from '@/components/home/Index'

Vue.use(Router)

export const constantRouterMap =  [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/index',
    name: 'index',
    component: index
  }
]

export default new Router({
  mode: 'history',
  routes: constantRouterMap
})

export const asyncRouterMap =  [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/index',
    name: 'index',
    component: index
  }
]