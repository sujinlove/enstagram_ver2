<template>
  <section class="main-page">
    <app-header />
    <two-columns>
      <div class="feed-list" slot="main">
        <div class="feed-item" :key="feed" v-for="feed in this.feedList">
          <feed :feed_num="feed" :page="PageName"/>
        </div>
      </div>
      <!-- <feed slot="main" :page="PageName" :feed_num="feed_num"></feed> -->
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
      <div class="user-list" slot="sidebar">
        <ol>
          <li class="user" :key="following" v-for="following in this.$store.state.user.followList">
            <user-list :user_num="following"></user-list>
          </li>
        </ol>
      </div>
      <app-footer slot="sidebar"/>
    </two-columns>
    <popup>
      <button @click="cancelFollow($store.state.selectFeed.accnt_num)" v-if="$store.state.popupContent == 'feedService'">팔로우 취소</button>
      <!-- <button @click="removeFeed($store.state.selectFeed)" v-if="$store.state.popupContent == 'feedService'">게시물 삭제</button> -->
    </popup>
  </section>
</template>

<script>
import Header from '../components/common/Header.vue'
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
      feedList: []
    }
  },
  components: {
    'app-header': Header,
    'app-footer': Footer,
    TwoColumns,
    Feed,
    UserList,
    Popup
  },
  created () {
    this.getFollowFeed()
  },
  methods: {
    getFollowFeed () {
      axios.get('/api/feed/follow', {
      }).then(response => {
        this.feedList = response.data
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    cancelFollow (followingNum) {
      axios.post('/api/user/unfollow', {
        accnt_num: this.$store.state.user.accnt_num,
        following_num: followingNum
      }).then(response => {
        this.$store.commit('setUser')
        this.$store.commit('selectFeed', '')
        this.getFollowFeed()
        this.$EventBus.$emit('showPopup')
      }).catch(e => {
        console.log('error: ' + e)
      })
    }
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  .profile {
    width: 100%;
    margin-bottom: 12px;
  }

  .user-list {
    width: 100%;
    margin-bottom: 14px;
    border: 1px solid #e6e6e6;
    border-radius: 3px;
    box-sizing: border-box;
    color: red;
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
</style>
