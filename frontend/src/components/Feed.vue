<template>
<article :id="'feed' + this.feed_num" class="feed">
  <!--When Mouse Over in My Page-->
  <div class="feed-hover" v-if="page == 'UserPage'">
    <div v-if="this.feed.heart > 0">
      <button class="icon-sprite ico-core heart"><span>heart</span></button>
      <span>{{this.feed.heart}}</span>
    </div>
    <div>
      <button class="icon-sprite ico-core chat"><span>chat</span></button>
      <span>{{this.feed.commentList.length}}</span>
    </div>
  </div>
  <!--When Mouse Over in My Page End-->
  <header class="feed-header">
    <div class="user-pic">
      <div class="user-pic-inner">
        <router-link :to="'/user/' + this.user.id">
          <img :src="this.user.profile" :alt="this.user.id + '님의 프로필 사진'"/>
        </router-link>
      </div>
    </div>
    <div class="user-id">
      <router-link :to="'/user/' + this.user.id">
        {{this.user.id}}
      </router-link>
      <div class="follow-btn" v-if="this.user.accnt_num !== this.$store.state.user.accnt_num && page == 'FeedPage'">
        <button @click="addFollow(user.accnt_num)" class="follow" v-if="this.$store.state.user.followingList.indexOf(this.user.accnt_num) === -1">팔로우</button>
        <button @click="cancelFollow(user.accnt_num)" class="unfollow" v-else>팔로잉</button>
      </div>
    </div>
    <button class="icon-sprite ico-glyph-2 more more2" type="button" @click="feedService"><span>more</span></button>
  </header>
  <div class="feed-pic">
    <div class="feed-pic-inner load">
      <div class="feed-loading loading"></div>
      <video autoplay controls :src="this.feed.file_name" type="video/mp4" v-if="page !== 'UserPage'" v-show="this.feedType === 'video'"/>
      <video :src="this.feed.file_name" type="video/mp4" v-if="page === 'UserPage'" v-show="this.feedType === 'video'"/>
      <img :src="this.feed.file_name" alt="feed" onerror="this.style.display='none'" v-show="this.feedType === 'image'"/>
    </div>
  </div>
  <!--When Not Edit Feed-->
  <div class="feed-content" v-if="this.$store.state.editFeed === false">
    <!--Feed Comment in Feed Page-->
    <feed-comments ref="feedComments" :user="user" :feedTime="feedTime" :feed="feed" :commentList="feed.commentList" :page="page" v-if="width > 767 && (page == 'FeedPage' || page == 'FeedCommentPage')" v-on:getFeedInfo="getFeedInfo" v-on:setParentComment="setParentComment"/>
    <!--Feed Comment in Feed Page End-->
    <!--Feed Content in All Page-->
    <div class="feed-content-inner">
      <div class="feed-btn">
        <button class="icon-sprite ico-glyph-3 heart" @click="addHeart(feed_num)"  v-if="this.$store.state.user.heartList.indexOf(Number(this.feed_num)) === -1"><span>heart</span></button>
        <button class="icon-sprite ico-glyph-3 noheart" @click="cancelHeart(feed_num)" v-else><span>heart</span></button>
        <router-link :to="'/feed/' + this.feed_num + '/comments'" class="icon-sprite ico-glyph chat" v-if="page === 'FeedPage'"><span>chat</span></router-link>
        <router-link :to="'/feed/' + this.feed_num" class="icon-sprite ico-glyph chat" v-else><span>chat</span></router-link>
      </div>
      <div class="heart-count" v-if="this.feed.heart > 0">
        <router-link :to="'/feed/' + this.feed_num + '/heart'">
          <span>좋아요</span>
          <span>{{this.feed.heart}}</span>
          <span>개</span>
        </router-link>
      </div>
      <div class="heart-count" v-else>
        가장 먼저 <span @click="addHeart(feed_num)">좋아요</span>를 눌러보세요
      </div>
      <router-link :to="'/feed/' + this.feed_num + '/comments'" v-if="width < 768 && this.feed.commentList.length > 0 && page === 'FeedPage'" class="more-comment">
          <span>댓글</span>
          <span>{{this.feed.commentList.length}}</span>
          <span>개 모두 보기</span>
      </router-link>
      <!--Feed Content in Main Page, My Page-->
      <div class="content-view" v-if="page !== 'FeedPage' || (page === 'FeedPage' && width < 768 )">
        <ul>
          <li class="feed-comment" v-if="this.feed.description !== ''">
            <router-link :to="'/user/' + this.user.id" class="user-id">
              {{this.user.id}}
            </router-link>
            <span class="feed-text" v-html="this.feed.description"></span>
          </li>
          <li class="more-comment" v-if="this.feed.commentList.length !== 0 && page !== 'FeedPage'">
            <router-link :to="'/feed/' + this.feed_num">
                <span>댓글</span>
                <span>{{this.feed.commentList.length}}</span>
                <span>개 모두 보기</span>
            </router-link>
          </li>
          <user-list v-if="showCommentList.length > 0 && page !== 'FeedPage'" :key="comment.reply_num" v-for="comment in this.showCommentList" :user_num="comment.accnt_num" :list="'comment'" :comment="comment" :page="page"/>
        </ul>
        <time :datetime="this.feed.regdate">{{this.feedTime}}</time>
      </div>
      <!--Feed Content in Main Page, My Page End-->
    </div>
    <!--Feed Content in All Page End-->
    <div class="comment">
      <form class="add-comment">
        <textarea @keyup.enter="addComment(feed_num, comment, parentComment)" v-model="comment" placeholder="댓글 달기..."/>
        <button type="button" class="comment-btn" disabled @click="addComment(feed_num, comment, parentComment)">게시</button>
      </form>
    </div>
  </div>
  <!--When Not Edit Feed End-->
  <!--When Edit Feed Comment-->
  <div class="feed-content edit-comment" v-else>
    <textarea placeholder="문구 입력..." v-model="feed.description"/>
  </div>
  <!--When Edit Feed Comment End-->
</article>
</template>

<script>
import axios from 'axios'
import FeedComments from '../components/FeedComments'
import UserList from '../components/UserList'
export default {
  props: ['page', 'feed_num'],
  components: {
    FeedComments,
    UserList
  },
  data () {
    return {
      feed: {
        commentList: []
      },
      user: {},
      parentComment: 0,
      comment: '',
      commentList: [],
      showCommentList: [],
      feedTime: '',
      feedType: '',
      width: '',
      ratio: '100%'
    }
  },
  watch: {
    comment: function (newValue) {
      var enterCheck = newValue.replace(/\n/g, '')
      if (newValue !== '' && enterCheck !== '') {
        document.querySelector('#feed' + this.feed_num + ' .comment-btn').disabled = false
      } else {
        document.querySelector('#feed' + this.feed_num + ' .comment-btn').disabled = true
      }
    }
  },
  created () {
    window.addEventListener('resize', this.windowResize)
    this.windowResize()
  },
  mounted () {
    this.$store.commit('setUser')
    this.getFeedInfo()
  },
  updated () {
    if (this.$store.state.editFeed === true) {
      document.querySelector('.edit-comment textarea').focus()
    }
  },
  methods: {
    windowResize () {
      this.width = screen.width
    },
    resizeFeedPic () {
      var feedNum = this.feed_num
      if (this.feed.file_name.split('.')[1] === 'mp4' || this.feed.file_name.split('.')[1] === 'mov') {
        this.feedType = 'video'
        var video = document.querySelector('#feed' + feedNum + ' video')
        video.onloadeddata = function () {
          document.querySelector('#feed' + feedNum + ' .feed-pic-inner').classList.remove('load')
          this.ratio = video.clientHeight / video.clientWidth * 100 + '%'
          document.querySelector('#feed' + feedNum + ' .feed-pic-inner').style.paddingBottom = this.ratio
        }
      } else {
        this.feedType = 'image'
        var img = new Image()
        img.src = this.feed.file_name
        img.onload = function () {
          document.querySelector('#feed' + feedNum + ' .feed-pic-inner').classList.remove('load')
          this.ratio = img.height / img.width * 100 + '%'
          document.querySelector('#feed' + feedNum + ' .feed-pic-inner').style.paddingBottom = this.ratio
        }
      }
    },
    getFeedInfo () {
      axios.post('/api/feed/' + this.feed_num, {
      }).then(response => {
        this.feed = response.data
        this.feedTime = this.getTime(this.feed.regdate)
        this.getUserInfo()
        this.resizeFeedPic()
        if (this.feed.commentList.length > 0) {
          this.showCommentList[0] = this.feed.commentList[0]
          if (this.feed.commentList.length > 2) {
            this.showCommentList[1] = this.feed.commentList[1]
          }
        }
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
    feedService () {
      this.$store.commit('setPopupContent', 'feedService')
      this.$EventBus.$emit('showPopup')
      if (this.feed.accnt_num === this.$store.state.user.accnt_num || this.page === 'MainPage') {
        this.$store.commit('selectFeed', this.feed)
      } else {
        this.$store.commit('selectFeed', '')
      }
    },
    addHeart (feedNum) {
      this.$store.dispatch('addHeart', {feedNum}).then(
        setTimeout(() => {
          this.getFeedInfo()
        }, 100)
      )
    },
    cancelHeart (feedNum) {
      this.$store.dispatch('cancelHeart', {feedNum}).then(
        setTimeout(() => {
          this.getFeedInfo()
        }, 100)
      )
    },
    addFollow (accntNum) {
      this.$store.dispatch('addFollow', {accntNum})
    },
    cancelFollow (accntNum) {
      this.$store.dispatch('cancelFollow', {accntNum})
    },
    getTime (uploadTime) {
      uploadTime = new Date(uploadTime)
      var now = new Date(Date.parse(new Date()) + 9000 * 60 * 60)
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
    changeFeedInfo () {
      this.feed.description = this.feed.description.replace(/\n/gi, '<br />')
      axios.post('/api/feed/edit', {
        feed_num: this.feed_num,
        description: this.feed.description
      }).then(response => {
        this.$store.commit('editFeed', false)
        this.getFeedInfo()
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    addComment (feedNum, comment, parentNum) {
      comment = comment.replace(/\n/g, '')
      if (comment !== '') {
        if (this.parentComment === 0) {
          this.$store.dispatch('addComment', {feedNum, comment}).then(
            this.getFeedInfo(),
            this.comment = ''
          )
        } else {
          this.$store.dispatch('addRecomment', {feedNum, comment, parentNum}).then(
            this.getFeedInfo(),
            this.comment = ''
          )
        }
      }
    },
    setParentComment (parentNum) {
      this.parentComment = parentNum
      document.querySelector('.add-comment textarea').focus()
    },
    getReCommentList (replyNum) {
      this.$refs.feedComments.getReCommentList(replyNum)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.follow-btn button {
  padding: 0;
  background: none;
  border: none;
  line-height: 1;
  color: #333;

  &.unfollow {
    color: #3897f0;
  }
  &:before {
    content: '•';
    margin: 0 4px;
    color: #333;
  }
}
</style>
