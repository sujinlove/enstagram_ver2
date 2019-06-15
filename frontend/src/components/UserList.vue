<template>
  <li class="user" v-if="list !== 'comment'">
    <div class="user-wrapper">
      <div class="user-pic">
        <div class="user-pic-inner">
          <img :src="this.user.profile" :alt="this.user.id + '님의 프로필 사진'" />
        </div>
      </div>
      <div class="user-info">
        <router-link :to="'/user/' + this.user.id" class="user-id">{{this.user.id}}</router-link>
        <div class="user-name">{{this.user.name}}</div>
      </div>
      <div class="follow-btn" v-if="this.user.accnt_num !== this.$store.state.user.accnt_num && (list === 'recommend' || list === 'following' || list === 'follower' || list === 'heartAccount')">
        <button @click="addFollow(user.accnt_num)" class="follow" v-if="this.$store.state.user.followingList.indexOf(this.user.accnt_num) === -1">팔로우</button>
        <button @click="cancelFollow(user.accnt_num)" class="unfollow" v-else>팔로잉</button>
      </div>
    </div>
  </li>
  <!-- Feed comment -->
  <li v-else-if="this.comment.parent_num === 0">
    <div class="user user-comment">
      <div class="user-pic" v-if="page == 'FeedPage' || page == 'FeedCommentPage'">
        <div class="user-pic-inner">
          <img :src="this.user.profile" :alt="this.user.id + '님의 프로필 사진'" />
        </div>
      </div>
      <div class="user-text">
        <router-link :to="'/user/' + this.user.id" class="user-id">{{this.user.id}}</router-link>
        <router-link to= "" class="user-id"></router-link>
        <span class="comment-text">{{this.comment.comment}}</span>
      </div>
      <div class="feed-others" v-if="page !== 'MyPage' && page !== 'MainPage' && page !== 'UserPage'">
        <time :datetime="this.comment.regdate">{{this.getTime(this.comment.regdate)}}</time>
        <button @click="$emit('setParentComment')">답글 달기</button>
      </div>
      <button class="icon-sprite ico-glyph-2 comment-more" @click="commentService" v-if="page === 'FeedPage' || page == 'FeedCommentPage' && (this.$store.state.user.accnt_num == this.comment.accnt_num || this.$store.state.user.feedList.indexOf(String(this.comment.feed_num)) !== -1)"><span>more</span></button>
    </div>
    <button class="recomment-more" v-if="reCommentList.length > 0 && recommentStatus === false && (page === 'FeedPage' || page === 'FeedCommentPage')" @click="changeRecommentStatus(true)">답글 보기 ({{this.reCommentList.length}}개)</button>
    <button class="recomment-more" v-if="reCommentList.length > 0 && recommentStatus === true && (page === 'FeedPage' || page === 'FeedCommentPage')" @click="changeRecommentStatus(false)">답글 숨기기</button>
    <feed-recomments v-show="recommentStatus === true" v-if="reCommentList.length > 0 && (page === 'FeedPage' || page === 'FeedCommentPage')" :key="recomment.reply_num" v-for="recomment in this.reCommentList" :user_num="recomment.accnt_num" :comment="recomment"></feed-recomments>
  </li>
  <!-- Feed comment End -->
</template>

<script>
import axios from 'axios'
import FeedReComments from '../components/FeedReComments'

export default {
  props: ['page', 'user_num', 'list', 'comment'],
  components: {
    'feed-recomments': FeedReComments
  },
  data () {
    return {
      user: {},
      reCommentList: [],
      recommentStatus: false
    }
  },
  created () {
    axios.get('/api/user/' + this.user_num).then((response) => {
      this.user = response.data
      if (this.list === 'comment') {
        this.getReCommentList()
      }
    })
  },
  methods: {
    addFollow (accntNum) {
      this.$store.dispatch('addFollow', {accntNum})
    },
    cancelFollow (accntNum) {
      this.$store.dispatch('cancelFollow', {accntNum})
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
    },
    commentService () {
      this.$store.commit('setPopupContent', 'commentService')
      this.$EventBus.$emit('showPopup')
      this.$store.commit('selectComment', this.comment)
    },
    getReCommentList () {
      axios.post('/api/replyList/' + this.comment.reply_num, {
      }).then(response => {
        this.reCommentList = response.data
      })
      if (this.comment.parent_num !== 0) {
        this.$emit('getReCommentList', this.comment.parent_num)
      }
    },
    changeRecommentStatus (status) {
      this.recommentStatus = status
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.follow-btn {
  text-align: right;
}
</style>
