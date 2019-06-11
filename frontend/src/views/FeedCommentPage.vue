<template>
  <section>
    <app-header :page="PageName"/>
    <div class="feed-comment-view">
      <feed-comments :feedTime="this.getTime(this.feed.regdate)" :feed="feed" :user="user" :commentList="commentList" :page="PageName" v-on:getCommentList="getCommentList"/>
    </div>
  </section>
</template>

<script>
import axios from 'axios'
import Header from '../components/common/Header.vue'
import FeedComments from '../components/FeedComments'

export default {
  props: ['feed_num'],
  data () {
    return {
      PageName: 'FeedCommentPage',
      feed: {},
      user: {},
      commentList: []
    }
  },
  components: {
    'app-header': Header,
    FeedComments
  },
  created () {
    this.getFeedInfo()
  },
  methods: {
    getFeedInfo () {
      axios.post('/api/feed/' + this.feed_num, {
      }).then(response => {
        this.feed = response.data
        this.getUserInfo()
        this.getCommentList()
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    getUserInfo () {
      axios.post('/api/user/' + this.feed.accnt_num, {
      }).then(response => {
        this.user = response.data
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    getCommentList () {
      console.log('getCommentList')
      axios.post('/api/replyList', {
        feed_num: this.feed.feed_num
      }).then(response => {
        this.commentList = response.data
        console.log(this.commentList)
      })
    },
    getTime (uploadTime) {
      uploadTime = new Date(uploadTime)
      var now = new Date()
      var time = (now - uploadTime) / 1000
      var returnTime
      if (time > 60) {
        returnTime = Math.floor(time / 60) + '분 전'
        if (time >= 3600) {
          returnTime = Math.floor(time / 60 / 60) + '시간 전'
        }
        if (time >= 86400) {
          returnTime = Math.floor(time / 60 / 60 / 24) + '일 전'
        }
        if (time >= 604800) {
          returnTime = uploadTime.getMonth() + 1 + '월 ' + uploadTime.getDate() + '일'
        }
      } else {
        returnTime = Math.floor(time) + '초 전'
        if (Math.floor(time) === 0) {
          returnTime = '방금 전'
        }
      }
      return returnTime
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
