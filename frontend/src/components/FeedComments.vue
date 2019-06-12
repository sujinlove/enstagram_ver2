<template>
  <div class="feed-comments feed-comment-list">
    <div class="comment" v-if="page == 'FeedCommentPage'">
      <div class="user-pic">
        <div class="user-pic-inner">
          <img :src="this.$store.state.user.profile" :alt="this.$store.state.user.id + '님의 프로필 사진'"/>
        </div>
      </div>
      <form class="add-comment">
        <textarea v-model="comment" placeholder="댓글 달기..."/>
        <button type="button" @click="addComment(feed.feed_num, comment, parentComment)">게시</button>
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
        </div>
        <div class="feed-others">
          <time :datetime="this.feed.regdate">{{this.feedTime}}</time>
        </div>
      </li>
      <user-list :ref="'userList'" :key="comment.reply_num" v-for="comment in feed.commentList" :user_num="comment.accnt_num" :list="'comment'" :page="page" :comment="comment" v-on:getReCommentList="getReCommentList" v-on:setParentComment="setParentComment(comment.reply_num)"/>
    </ul>
  </div>
</template>

<script>
import UserList from '../components/UserList'
export default {
  props: ['page', 'feedTime', 'feed', 'user'],
  components: {
    UserList
  },
  data () {
    return {
      comment: '',
      parentComment: 0
    }
  },
  methods: {
    addComment (feedNum, comment, parentNum) {
      if (this.parentComment === 0) {
        this.$store.dispatch('addComment', {feedNum, comment}).then(
          console.log('addComment'),
          this.$emit('getFeedInfo'),
          this.comment = ''
        )
      } else {
        this.$store.dispatch('addRecomment', {feedNum, comment, parentNum}).then(
          console.log('addReComment'),
          this.$emit('getFeedInfo'),
          this.comment = ''
        )
      }
    },
    setParentComment (parentNum) {
      this.parentComment = parentNum
      if (this.page === 'FeedPage') {
        this.$emit('setParentComment', parentNum)
      }
      document.querySelector('.add-comment textarea').focus()
    },
    getReCommentList (replyNum) {
      var replyIndex = this.feed.commentList.findIndex(i => i.reply_num === replyNum)
      this.$refs.userList[replyIndex].getReCommentList()
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
</style>
