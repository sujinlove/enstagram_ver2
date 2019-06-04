<template>
  <section>
    <app-header />
    <one-column>
      <profile :user="user" :page="PageName"/>
      <div class="feed-list">
        <ul class="feed-mode grid-mode-view">
          <li class="feed-item" :key="feed" v-for="feed in this.user.feedList">
            <router-link :to="{ name: 'FeedPage', params: { feed_num: feed } }" v-if="feedMode == 'grid-mode'">
              <feed :feed_num="feed" :page="PageName"/>
            </router-link>
            <feed :feed_num="feed" :page="PageName" v-else />
          </li>
        </ul>
      </div>
    </one-column>
    <footer-layout />
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
  props: ['user_id'],
  data () {
    return {
      user: {},
      PageName: 'UserPage'
    }
  },
  created () {
    this.getUserInfo()
  },
  methods: {
    getUserInfo () {
      axios.post('/api/user/id/' + this.user_id, {
      }).then(response => {
        this.user = response.data
        console.log(this.user)
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
