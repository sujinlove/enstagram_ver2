<template>
  <section>
    <app-header />
    <one-column>
      <profile ref="editProfile" />
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
            <feed :feed_num="feed"/>
          </li>
        </ul>
      </div>
    </one-column>
    <footer-layout />
    <popup>
        <button @click="profileUpload" v-if="$store.state.popupContent == 'editUserProfile'">사진 업로드</button>
        <button @click="profileRemove" v-if="$store.state.popupContent == 'editUserProfile'">현재 사진 삭제</button>
        <button @click="feedRemove" v-if="$store.state.popupContent == 'feedService'">게시물 삭제</button>
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
      this.$refs.editProfile.UploadBtn()
      this.$EventBus.$emit('showPopup')
    },
    profileRemove () {
      var params = new URLSearchParams()
      params.append('filePath', 'profile/default.jpg')
      axios.post('/api/profile/remove', params, {
      }).then(response => {
        this.$store.commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
      this.$EventBus.$emit('showPopup')
    },
    feedRemove () {
      var params = new URLSearchParams()
      params.append('feed_num', this.feed_num)
      axios.post('/api/feed/remove', params, {
      }).then(response => {
        this.$router.push('/mypage')
      }).catch(e => {
        console.log('error: ' + e)
      })
      this.$EventBus.$emit('showPopup')
    },
    changeMode (mode) {
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
<style lang="scss" scope>
@import '../assets/css/source/feeds.scss';
@media only screen and (max-width: ($screen__m - 1)) {
  main {
    padding-left: 0;
    padding-right: 0;
  }
}
</style>
