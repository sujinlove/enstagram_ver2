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
  <li class="user user-comment" v-else>
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
    <div class="feed-others" v-if="page !== 'MyPage' && page !== 'MainPage'">
      <time :datetime="this.comment.regdate">{{this.commentTime}}</time>
      <button>답글 달기</button>
    </div>
  </li>
  <!-- Feed comment End -->
</template>

<script>
import axios from 'axios'

export default {
  props: ['page', 'user_num', 'list', 'comment'],
  data () {
    return {
      user: {},
      commentTime: ''
    }
  },
  created () {
    axios.get('/api/user/' + this.user_num).then((response) => {
      this.user = response.data
    })
    this.commentTime = this.getTime(this.comment.regdate)
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
      }
      return returnTime
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
