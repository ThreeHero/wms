import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store' //  引入vuex store实例

import Layout from '@/layout'
import login from '@/views/login'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'layout',
    component: Layout,
    children: [
      {
        path: '',
        component: () => import('@/views/person')
      }
    ]
  },
  {
    path: '/',
    name: 'layout',
    component: Layout,
    children: [
      {
        path: 'user',
        component: () => import('@/views/user')
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: login
  }
]

const router = new VueRouter({
  routes
})

// 路由前置守卫
router.beforeEach((to, from, next) => {
  if (store.state.token) {
    // 含有token
    if (to.path === '/login') {
      next('/')
    }
    next()
  } else {
    // 没有token
    if (to.path === '/login') {
      next()
    } else {
      next('/login')
    }
  }
})

export default router
