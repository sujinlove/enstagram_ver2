import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/views/MainPage'
import MyPage from '@/views/MyPage'
import AccountPage from '@/views/AccountPage'
import FeedPage from '@/views/FeedPage'
import LoginPage from '@/views/LoginPage'

Vue.use(Router)

export default new Router({
  mode: 'history',
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
    },
    {
      path: '/account',
      name: 'AccountPage',
      component: AccountPage
    },
    {
      path: '/feed',
      name: 'FeedPage',
      component: FeedPage
    },
    {
      path: '/login',
      name: 'LoginPage',
      component: LoginPage
    }
  ]
})
