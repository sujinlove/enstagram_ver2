<template>
  <section>
    <app-header ref="header"/>
    <one-column>
      <profile ref="profile" :page="PageName" :user="this.$store.state.user" />
      <div class="modes">
        <div class="mode grid" @click="changeMode('grid-mode')">
          <button class="icon-sprite ico-glyph-3 grid-mode active"><span>grid</span></button>
        </div>
        <div class="mode list" @click="changeMode('list-mode')">
          <button class="icon-sprite ico-glyph-3 list-mode"><span>list</span></button>
        </div>
      </div>
      <div class="feed-list" v-if="this.$store.state.user.feedList.length > 0">
        <ul class="feed-mode grid-mode-view">
          <li class="feed-item" :key="feed" v-for="feed in this.$store.state.user.feedList">
            <router-link :to="'/feed/' + feed" v-if="feedMode == 'grid-mode'">
              <feed :feed_num="feed" :page="PageName"/>
            </router-link>
            <feed :feed_num="feed" :page="PageName" v-else />
          </li>
        </ul>
      </div>
      <div class="service-start" v-else>
        <strong class="content-title">시작하기</strong>
        <div class="container">
          <div class="icon-circle">
            <div class="icon-sprite ico-glyph-3 image"><span>image</span></div>
          </div>
          <div class="content-title">사진 공유</div>
          <p>사진을 공유하면 회원님의 프로필에 표시됩니다.</p>
          <button class="action primary" @click="$refs.header.uploadBtn()">첫 사진을 공유해보세요</button>
        </div>
      </div>
    </one-column>
    <footer-layout />
    <popup>
        <button @click="profileUpload" v-if="$store.state.popupContent == 'editUserProfile'">사진 업로드</button>
        <button @click="profileRemove" v-if="$store.state.popupContent == 'editUserProfile'">현재 사진 삭제</button>
        <button @click="editFeed($store.state.selectFeed.feed_num)" v-if="$store.state.popupContent == 'feedService'">게시물 수정</button>
        <button @click="removeFeed($store.state.selectFeed.feed_num)" v-if="$store.state.popupContent == 'feedService'">게시물 삭제</button>
        <router-link to="/account/edit" v-if="$store.state.popupContent == 'editUserInfo'">비밀번호 변경</router-link>
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
      this.$store.dispatch('removeFeed', {feedNum}).then(
        this.$store.commit('selectFeed', ''),
        this.$EventBus.$emit('showPopup')
      )
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
.service-start {
  padding: 15px;

  .container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 14px;
    padding: 12px;
    border: 1px solid #e6e6e6;
    border-radius: 4px;
    background: #fff;
  }

  .content-title {
    margin-top: 16px;
  }

  p {
    color: #999;
  }

  button {
    width: auto;
  }
}

.feed {
  border: 0;
}

@media only screen and (max-width: 767px) {
  main {
    padding-left: 0;
    padding-right: 0;
  }
}
</style>
