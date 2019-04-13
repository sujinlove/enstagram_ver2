import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/views/MainPage'
import MyPage from '@/views/MyPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MainPage',
      component: MainPage
    },
    {
      path: '/mypage',
      name: 'MyPage',
      component: MyPage
    }
  ]
})
