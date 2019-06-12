<template>
  <!-- <user-list :key="recomment.reply_num" v-for="recomment in this.recommentList" :user_num="recomment.accnt_num" :list="'comment'" :page="page" /> -->
  <div class="user-recomment">
    <div class="user-pic">
      <div class="user-pic-inner">
        <img :src="this.user.profile" :alt="this.user.id + '님의 프로필 사진'" />
      </div>
    </div>
    <div class="user-text">
      <router-link :to="'/user/' + this.user.id" class="user-id">{{this.user.id}}</router-link>
      <router-link to= "" class="user-id"></router-link>
      <span class="comment-text">{{this.comment.comment}}</span>
    </div>
    <div class="feed-others">
      <time :datetime="this.comment.regdate">{{this.getTime(this.comment.regdate)}}</time>
    </div>
    <button class="icon-sprite ico-glyph-2 comment-more" @click="commentService" v-if="this.$store.state.user.accnt_num == this.comment.accnt_num || this.$store.state.user.feedList.indexOf(String(this.comment.feed_num)) !== -1"><span>more</span></button>
  </div>
</template>

<script>
import axios from 'axios'
import UserList from '../components/UserList'
export default {
  props: ['user_num', 'comment'],
  components: {
    UserList
  },
  data () {
    return {
      user: {},
      recommentList: []
    }
  },
  created () {
    axios.get('/api/user/' + this.user_num).then((response) => {
      this.user = response.data
    })
  },
  methods: {
    commentService () {
      this.$store.commit('setPopupContent', 'commentService')
      this.$EventBus.$emit('showPopup')
      this.$store.commit('selectComment', this.comment)
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
    getReCommentList () {
      axios.post('/api/replyList/' + this.comment_num, {
      }).then(response => {
        this.recommentList = response.data
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
</style>
