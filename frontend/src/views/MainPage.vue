<template>
  <section class="main-page">
    <app-header />
    <two-columns v-if="this.feedList.length > 0">
      <div class="feed-list" slot="main">
        <div class="feed-item" :key="feed" v-for="feed in this.showList">
          <feed :feed_num="feed" :page="PageName"/>
        </div>
      </div>
      <div slot="sidebar" class="profile">
        <div class="user-pic">
          <div class="user-pic-inner">
            <img :src="this.$store.state.user.profile" :alt="this.$store.state.user.id + '님의 프로필 사진'" />
          </div>
        </div>
        <div class="user-info">
          <p class="user-id">{{ this.$store.state.user.id }}</p>
          <p class="user-name">{{ this.$store.state.user.name }}</p>
        </div>
      </div>
      <div class="user-list following-user" slot="sidebar" v-if="this.$store.state.user.followingList.length > 0">
        <ol>
          <li class="user" :key="following" v-for="following in this.$store.state.user.followingList">
            <user-list :user_num="following" />
          </li>
        </ol>
      </div>
      <div class="user-list recommend-user" slot="sidebar" v-if="this.recommendList.length > 0">
        <strong class="content-title">회원님을 위한 추천</strong>
        <ol>
          <li class="user" :key="recommend" v-for="recommend in this.recommendList">
            <user-list :user_num="recommend" :list="'recommend'" />
          </li>
        </ol>
      </div>
      <app-footer slot="sidebar"/>
    </two-columns>
    <!-- When no follow user-->
    <one-column v-else>
      <div class="container">
        <div class="content-title">회원님을 위한 추천</div>
        <div class="user-list recommend-user">
          <ol>
            <li class="user" :key="recommend" v-for="recommend in this.recommendList">
              <user-list :user_num="recommend" :list="'recommend'"/>
            </li>
          </ol>
        </div>
      </div>
    </one-column>
    <popup>
      <button @click="cancelFollow($store.state.selectFeed.accnt_num)" v-if="$store.state.popupContent == 'feedService' && $store.state.selectFeed.accnt_num !== $store.state.user.accnt_num">팔로우 취소</button>
      <button @click="removeFeed($store.state.selectFeed.feed_num)" v-if="$store.state.popupContent == 'feedService' && $store.state.user.feedList.indexOf(String($store.state.selectFeed.feed_num)) !== -1">게시물 삭제</button>
    </popup>
  </section>
</template>

<script>
import Header from '../components/common/Header.vue'
import OneColumn from '../components/common/OneColumn'
import TwoColumns from '../components/common/TwoColumns'
import Feed from '../components/Feed'
import UserList from '../components/UserList'
import Footer from '../components/common/Footer'
import Popup from '../components/common/Popup'
import axios from 'axios'
export default {
  data () {
    return {
      PageName: 'MainPage',
      feed_num: '',
      values: [],
      feedList: [],
      recommendList: [],
      showList: [],
      bottom: false,
      count: 0
    }
  },
  components: {
    'app-header': Header,
    'app-footer': Footer,
    OneColumn,
    TwoColumns,
    Feed,
    UserList,
    Popup
  },
  created () {
    window.addEventListener('scroll', () => {
      this.bottom = this.bottomVisible()
    })
    this.getFollowFeed()
    this.getRecommendList()
  },
  watch: {
    bottom (bottom) {
      if (bottom) {
        this.addFeed()
      }
    }
  },
  methods: {
    bottomVisible () {
      const scrollY = window.scrollY
      const visible = document.documentElement.clientHeight
      const pageHeight = document.documentElement.scrollHeight
      const bottomOfPage = visible + scrollY >= pageHeight
      if (scrollY > 0) {
        return bottomOfPage || pageHeight < visible
      }
    },
    addFeed () {
      if (this.feedList[this.count]) {
        this.showList.push(this.feedList[this.count])
        this.count++
      }
    },
    removeFeed (feedNum) {
      this.$store.dispatch('removeFeed', {feedNum}).then(
        this.$store.commit('selectFeed', ''),
        this.$EventBus.$emit('showPopup'),
        this.showList.splice(this.feedList.indexOf(feedNum), 1),
        this.getFollowFeed()
      )
    },
    getFollowFeed () {
      axios.get('/api/feed/follow', {
      }).then(response => {
        this.feedList = response.data
        this.addFeed()
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    cancelFollow (accntNum) {
      this.$store.dispatch('cancelFollow', {accntNum}).then(
        this.$EventBus.$emit('showPopup'),
        this.$store.commit('selectFeed', ''),
        this.getFollowFeed()
      )
    },
    getRecommendList () {
      axios.get('/api/recommendUser', {
      }).then(response => {
        this.recommendList = response.data.accnt_num
      }).catch(e => {
        console.log('error: ' + e)
      })
    }
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  .container {
    width: 100%;
  }

  .user-list {
    width: 100%;
    margin-top: 14px;
    border: 1px solid #e6e6e6;
    border-radius: 3px;
    box-sizing: border-box;

    &.recommend-user {
      margin-bottom: 14px;
    }
  }

  footer {
    .container {
      padding: 0;
    }
  }

  .user {
    &-pic {
      width: 50px;
      height: 50px;
    }

    &-info {
      p {
        display: block;
        margin-bottom: 0;

        &.user-name {
          font-size: 12px;
          font-weight: 400;
          color: #999;
        }
      }
    }
  }
  @media only screen and (max-width: 767px) {
    .container {
      padding-top: 40px;
      padding-bottom: 40px;
    }
  }
</style>
