<template>
  <section>
    <app-header />
    <one-column>
      <profile ref="profile" />
      <div class="modes">
        <div class="mode grid" @click="changeMode('grid-mode')">
          <button class="icon-sprite ico-glyph-3 grid-mode active"><span>grid</span></button>
        </div>
        <div class="mode list" @click="changeMode('list-mode')">
          <button class="icon-sprite ico-glyph-3 list-mode"><span>list</span></button>
        </div>
      </div>
      <div class="feed-list">
        <ul class="feed-mode grid-mode-view">
          <li class="feed-item" :key="feed" v-for="feed in this.$store.state.user.feedList">
            <router-link :to="{ name: 'FeedPage', params: { feed_num: feed } }" v-if="feedMode == 'grid-mode'">
              <feed :feed_num="feed" :page="PageName"/>
            </router-link>
            <feed :feed_num="feed" :page="PageName" v-else />
          </li>
        </ul>
      </div>
    </one-column>
    <footer-layout />
    <popup>
        <button @click="profileUpload" v-if="$store.state.popupContent == 'editUserProfile'">사진 업로드</button>
        <button @click="profileRemove" v-if="$store.state.popupContent == 'editUserProfile'">현재 사진 삭제</button>
        <button @click="editFeed($store.state.selectFeed)" v-if="$store.state.popupContent == 'feedService'">게시물 수정</button>
        <button @click="removeFeed($store.state.selectFeed)" v-if="$store.state.popupContent == 'feedService'">게시물 삭제</button>
        <a href="/logout" v-if="$store.state.popupContent == 'editUserInfo'">로그아웃</a>
    </popup>
  </section>
</template>

<script>
import axios from 'axios'
import Header from '../components/common/Header.vue'
import OneColumn from '../components/common/OneColumn'
import Profile from '../components/Profile'
import Feed from '../components/Feed'
import Footer from '../components/common/Footer'
import Popup from '../components/common/Popup'

export default {
  components: {
    'app-header': Header,
    OneColumn,
    Profile,
    Feed,
    'footer-layout': Footer,
    Popup
  },
  data () {
    return {
      PageName: 'MyPage',
      feedMode: 'grid-mode',
      feedList: [],
      editUserProfile: true,
      editUserInfo: false
    }
  },
  mounted () {
    this.$store.commit('setUser')
    this.feedList = this.$store.state.user.feedList
  },
  methods: {
    profileUpload () {
      this.$refs.profile.UploadBtn()
      this.$EventBus.$emit('showPopup')
    },
    profileRemove () {
      var params = new URLSearchParams()
      params.append('filePath', '/profile/default.jpg')
      axios.post('/api/profile/remove', params, {
      }).then(response => {
        this.$store.commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
      this.$EventBus.$emit('showPopup')
    },
    editFeed (feedNum) {
      this.$router.push('/feed/' + feedNum)
      this.$EventBus.$emit('showPopup')
      this.$store.commit('editFeed', true)
    },
    removeFeed (feedNum) {
      axios.post('/api/feed/remove', {
        accnt_num: this.$store.state.user.accnt_num,
        feed_num: this.$store.state.selectFeed
      }).then(response => {
        this.$store.commit('setUser')
        this.$store.commit('selectFeed', '')
      }).catch(e => {
        console.log('error: ' + e)
      })
      this.$EventBus.$emit('showPopup')
    },
    changeMode (mode) {
      this.feedMode = mode
      document.querySelector('.mode button:not(.' + mode + ')').classList.remove('active')
      document.querySelector('.' + mode).classList.add('active')
      document.querySelector('.feed-mode').classList.remove('grid-mode-view')
      document.querySelector('.feed-mode').classList.remove('list-mode-view')
      document.querySelector('.feed-mode').classList.add(mode + '-view')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.feed {
  border: 0;
}
@media only screen and (min-width: 768px) {
  footer {
    .container {
      display: flex;
    }
  }
}
@media only screen and (max-width: 767px) {
  main {
    padding-left: 0;
    padding-right: 0;
  }
}
</style>
