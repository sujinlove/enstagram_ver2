<template>
  <section class="main-page">
    <app-header />
    <two-columns>
      <div class="feed-list" slot="main">
        <div class="feed-item" :key="feed" v-for="feed in this.feedList">
          <feed :feed_num="feed" :page="PageName" />
        </div>
      </div>
      <!-- <feed slot="main" :page="PageName" :feed_num="feed_num"></feed> -->
      <div slot="sidebar" class="profile">
        <div class="my-pic">
          <div class="my-pic-inner">
            <img :src="this.$store.state.user.profile" alt="ensta00_1님의 프로필 사진" />
          </div>
        </div>
        <div class="my-info">
          <p class="my-id">{{ this.$store.state.user.id }}</p>
          <p class="my-name">{{ this.$store.state.user.name }}</p>
        </div>
      </div>
      <div class="user-list" slot="sidebar">
        <ol>
          <li class="user" :key="following" v-for="following in this.$store.state.user.followList">
            <follower-list :following_num="following"></follower-list>
          </li>
        </ol>
      </div>
      <app-footer slot="sidebar"/>
    </two-columns>
  </section>
</template>

<script>
import Header from '../components/common/Header.vue'
import TwoColumns from '../components/common/TwoColumns'
import Feed from '../components/Feed'
import FollowerList from '../components/FollowerList'
import Footer from '../components/common/Footer'
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
    FollowerList
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

  .my {
    &-pic {
      img {
        width: 50px;
        height: 50px;
      }
    }

    &-info {
      p {
        display: block;
        margin-bottom: 0;

        &.my-name {
          font-size: 12px;
          font-weight: 400;
          color: #999;
        }
      }
    }
  }
</style>
