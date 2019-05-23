import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/views/MainPage'
import MyPage from '@/views/MyPage'
import AccountPage from '@/views/AccountPage'
import FeedPage from '@/views/FeedPage'
import NotFound from '@/views/NotFound'
import FeedUploadPage from '@/views/FeedUploadPage'

Vue.use(Router)

export default new Router({
  mode: 'history',
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
      path: '/feed/:feed_num',
      name: 'FeedPage',
      component: FeedPage,
      props: true
    },
    {
      path: '/feedUpload',
      name: 'FeedUploadPage',
      component: FeedUploadPage
    }

  ]
})
