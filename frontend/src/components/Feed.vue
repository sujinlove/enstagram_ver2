<template>
<article class="feed">
  <!--When Mouse Over in My Page-->
  <div class="feed-hover" v-if="page == 'MyPage'">
    <div v-if="this.feed.heart > 0">
      <button class="icon-sprite ico-core heart"><span>heart</span></button>
      <span>{{this.feed.heart}}</span>
    </div>
    <div>
      <button class="icon-sprite ico-core chat"><span>chat</span></button>
      <span>0</span>
    </div>
  </div>
  <!--When Mouse Over in My Page End-->
  <header class="feed-header">
    <div class="user-pic">
      <router-link :to= "{ name: 'UserPage', params: { user_id: this.user.id }}">
        <img :src="this.user.profile" :alt="this.user.id + '님의 프로필 사진'"/>
      </router-link>
    </div>
    <div class="user-id">
      <router-link :to= "{ name: 'UserPage', params: { user_id: this.user.id }}">{{this.user.id}}</router-link>
    </div>
    <button class="icon-sprite ico-glyph-2 more" type="button" @click="feedService"><span>more</span></button>
  </header>
  <div class="feed-pic">
    <div class="feed-pic-inner">
      <img :src="this.feed.file_name" alt="feed" onerror="this.style.display='none'"/>
    </div>
  </div>
  <!--When Not Edit Feed-->
  <div class="feed-content" v-if="this.$store.state.editFeed === false">
    <!--Feed Comment in Feed Page-->
    <feed-comments :user="user" :feed="feed" :page="page"/>
    <!--Feed Comment in Feed Page End-->
    <!--Feed Content in All Page-->
    <div class="feed-content-inner">
      <div class="feed-btn">
        <button class="icon-sprite ico-glyph-3 heart" @click="addHeart"  v-if="this.$store.state.user.heartList.indexOf(this.feed_num) === -1"><span>heart</span></button>
        <button class="icon-sprite ico-glyph-3 noheart" @click="cancelHeart" v-else><span>heart</span></button>
        <router-link :to= "{ name: 'FeedPage', params: { feed_num: this.feed_num }}" class="icon-sprite ico-glyph chat"><span>chat</span></router-link>
      </div>
      <div class="heart-count" v-if="this.feed.heart > 0">
        <router-link :to= "{ name: 'HeartPage', params: { feed_num: this.feed_num }}">
          <span>좋아요</span>
          <span>{{this.feed.heart}}</span>
          <span>개</span>
        </router-link>
      </div>
      <div class="heart-count" v-else>
        가장 먼저 <span @click="addHeart">좋아요</span>를 눌러보세요
      </div>
      <!--Feed Content in Main Page, My Page-->
      <div class="content-view" v-if="page !== 'FeedPage'">
        <ul>
          <li class="feed-comment" v-if="this.feed.description !== ''">
            <router-link :to= "{ name: 'UserPage', params: { user_id: this.user.id }}" class="user-id">{{this.user.id}}</router-link>
            <span class="feed-text">{{this.feed.description}}</span>
          </li>
          <li class="more-comment">
            <router-link to= "/feed">
                <span>댓글</span>
                <span>1231231</span>
                <span>개 모두 보기</span>
            </router-link>
          </li>
          <li class="user-comment">
            <router-link to= "" class="user-id">jennierubyjane</router-link>
            <span class="user-text">나는 메인 제니!</span>
          </li>
        </ul>
      </div>
      <!--Feed Content in Main Page, My Page End-->
    </div>
    <!--Feed Content in All Page End-->
    <div class="comment">
      <form>
        <textarea v-model="comment" placeholder="댓글 달기..."/>
        <button type="button" class="comment-btn" disabled>게시</button>
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
export default {
  props: ['page', 'feed_num'],
  components: {
    FeedComments
  },
  data () {
    return {
      feed: {},
      user: {},
      comment: ''
    }
  },
  watch: {
    comment: function (newValue) {
      if (newValue !== '') {
        document.querySelector('.comment-btn').disabled = false
      } else {
        document.querySelector('.comment-btn').disabled = true
      }
    }
  },
  created () {
    this.getFeedInfo()
  },
  updated () {
    if (this.$store.state.editFeed === true) {
      document.querySelector('.edit-comment textarea').focus()
    }
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
    },
    feedService () {
      this.$store.commit('setPopupContent', 'feedService')
      this.$EventBus.$emit('showPopup')
      if (this.page === 'MyPage') {
        this.$store.commit('selectFeed', this.feed_num)
      }
    },
    addHeart () {
      axios.post('/api/feed/like', {
        accnt_num: this.$store.state.user.accnt_num,
        feed_num: this.feed_num
      }).then(response => {
        this.getFeedInfo()
        this.$store.commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    cancelHeart () {
      axios.post('/api/feed/unlike', {
        accnt_num: this.$store.state.user.accnt_num,
        feed_num: this.feed_num
      }).then(response => {
        this.getFeedInfo()
        this.$store.commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    changeFeedInfo () {
      axios.post('/api/feed/edit', {
        feed_num: this.feed_num,
        description: this.feed.description
      }).then(response => {
        this.$store.state.editFeed = false
        this.getFeedInfo()
      }).catch(e => {
        console.log('error: ' + e)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scope>
@import '../assets/css/source/feeds.scss';
</style>
