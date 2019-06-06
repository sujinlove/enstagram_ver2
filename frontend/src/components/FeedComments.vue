<template>
  <div class="feed-comments feed-comment-list">
    <div class="comment" v-if="page == 'FeedCommentPage'">
      <div class="user-pic">
        <div class="user-pic-inner">
          <img :src="this.$store.state.user.profile" :alt="this.$store.state.user.id + '님의 프로필 사진'"/>
        </div>
      </div>
      <form>
        <textarea placeholder="댓글 달기..."/>
        <button type="button">게시</button>
      </form>
    </div>
    <ul>
      <li class="feed-comment" v-if="this.feed.description !== ''">
        <div class="user-pic">
          <div class="user-pic-inner">
            <router-link :to="'/user/' + this.user.id">
              <img :src="this.user.profile" :alt="this.user.id + '님의 프로필 사진'"/>
            </router-link>
          </div>
        </div>
        <div class="user-text">
          <router-link :to="'/user/' + this.user.id" class="user-id">{{this.user.id}}</router-link>
          <span class="feed-text">{{this.feed.description}}</span>
          <div class="feed-others">
            <time>1일</time>
          </div>
        </div>
      </li>
      <li class="user-comment">
        <div class="user-pic">
          <div class="user-pic-inner">
            <router-link :to="'/user/' + this.user.id" class="user-id">
              <img :src="this.user.profile" :alt="this.user.id + '님의 프로필 사진'"/>
            </router-link>
          </div>
        </div>
        <div class="user-text">
          <router-link to= "" class="user-id">jennierubyjane</router-link>
          <span class="user-text">나는 메인 제니!</span>
          <div class="feed-others">
            <time>1일</time>
            <button>답글 달기</button>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  props: ['page', 'feed', 'user'],
  data () {
    return {
    }
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
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
</style>
