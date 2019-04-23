import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/views/MainPage'
import MyPage from '@/views/MyPage'
import AccountPage from '@/views/AccountPage'
import FeedPage from '@/views/FeedPage'
import LoginPage from '@/views/LoginPage'
import NotFound from '@/views/NotFound'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '*',
      name: 'NotFound',
      component: NotFound
    },
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
      path: '/login',
      name: 'LoginPage',
      component: LoginPage
    },
    {
      path: '/feed',
      name: 'FeedPage',
      component: FeedPage
    }
  ]
})
