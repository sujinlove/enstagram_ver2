import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/views/MainPage'
import MyPage from '@/views/MyPage'
import AccountPage from '@/views/AccountPage'
import AccountEditPage from '@/views/AccountEditPage'
import AccountFollowerPage from '@/views/AccountFollowerPage'
import AccountFollowingPage from '@/views/AccountFollowingPage'
import FeedPage from '@/views/FeedPage'
import FeedCommentPage from '@/views/FeedCommentPage'
import FeedHeartPage from '@/views/FeedHeartPage'
import NotFound from '@/views/NotFound'
import FeedUploadPage from '@/views/FeedUploadPage'
import UserPage from '@/views/UserPage'
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
      path: '/account/edit',
      name: 'AccountEditPage',
      component: AccountEditPage
    },
    {
      path: '/feed/:feed_num',
      name: 'FeedPage',
      component: FeedPage,
      props: true
    },
    {
      path: '/feed/:feed_num/heart',
      name: 'FeedHeartPage',
      component: FeedHeartPage,
      props: true
    },
    {
      path: '/feed/:feed_num/comments',
      name: 'FeedCommentPage',
      component: FeedCommentPage,
      props: true
    },
    {
      path: '/feedUpload',
      name: 'FeedUploadPage',
      component: FeedUploadPage
    },
    {
      path: '/user/:user_id',
      name: 'UserPage',
      component: UserPage,
      props: true
    },
    {
      path: '/user/:user_id/followers',
      name: 'AccountFollowerPage',
      component: AccountFollowerPage,
      props: true
    },
    {
      path: '/user/:user_id/following',
      name: 'AccountFollowingPage',
      component: AccountFollowingPage,
      props: true
    }
  ]
})
