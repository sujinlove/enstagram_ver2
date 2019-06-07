<template>
  <section v-if="countId !== 0">
    <app-header />
    <one-column>
      <profile :user="user" :page="PageName"/>
      <div class="feed-list">
        <ul class="feed-mode grid-mode-view">
          <li class="feed-item" :key="feed" v-for="feed in this.user.feedList">
            <router-link :to="'/feed/' + feed" v-if="feedMode == 'grid-mode'">
              <feed :feed_num="feed" :page="PageName"/>
            </router-link>
            <feed :feed_num="feed" :page="PageName" v-else />
          </li>
        </ul>
      </div>
    </one-column>
    <footer-layout />
  </section>
  <not-found v-else />
</template>

<script>
import axios from 'axios'
import Header from '../components/common/Header.vue'
import OneColumn from '../components/common/OneColumn'
import Profile from '../components/Profile'
import Feed from '../components/Feed'
import Footer from '../components/common/Footer'
import Popup from '../components/common/Popup'
import NotFound from './NotFound.vue'

export default {
  components: {
    'app-header': Header,
    OneColumn,
    Profile,
    Feed,
    'footer-layout': Footer,
    Popup,
    NotFound
  },
  props: ['user_id'],
  data () {
    return {
      user: {},
      PageName: 'UserPage',
      feedMode: 'grid-mode',
      countId: ''
    }
  },
  created () {
    this.checkId()
  },
  methods: {
    checkId () {
      axios.post('/api/check/id', {
        id: this.user_id
      }).then(response => {
        this.countId = response.data
        if (this.countId !== 0) {
          this.getUserInfo()
        }
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    getUserInfo () {
      axios.post('/api/user/id/' + this.user_id, {
      }).then(response => {
        this.user = response.data
      }).catch(e => {
        console.log('error: ' + e)
      })
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
</style>
