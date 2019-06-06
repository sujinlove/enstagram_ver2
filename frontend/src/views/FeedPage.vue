<template>
  <section v-if="countFeed !== 0">
    <app-header v-on:changeFeedInfo="changeFeedInfo"/>
    <one-column>
      <div class="feed-view">
        <feed :page="PageName" :feed_num="feed_num" ref="feed"/>
      </div>
    </one-column>
    <popup>
      <button @click="cancelFollow" v-if="$store.state.popupContent == 'feedService'">팔로우 취소</button>
      <button @click="editFeed" v-if="$store.state.popupContent == 'feedService' && this.$store.state.user.feedList.indexOf(this.feed_num) !== -1">게시물 수정</button>
      <button @click="removeFeed(feed_num)" v-if="$store.state.popupContent == 'feedService' && this.$store.state.user.feedList.indexOf(this.feed_num) !== -1">게시물 삭제</button>
    </popup>
  </section>
  <not-found v-else />
</template>

<script>
import axios from 'axios'
import Header from '../components/common/Header.vue'
import OneColumn from '../components/common/OneColumn'
import Feed from '../components/Feed'
import Popup from '../components/common/Popup'
import NotFound from './NotFound.vue'

export default {
  props: ['feed_num'],
  data () {
    return {
      PageName: 'FeedPage',
      countFeed: ''
    }
  },
  components: {
    'app-header': Header,
    OneColumn,
    Feed,
    Popup,
    NotFound
  },
  created () {
    this.checkFeed()
  },
  methods: {
    checkFeed () {
      axios.post('/api/check/feedNum', {
        feed_num: this.feed_num
      }).then(response => {
        this.countFeed = response.data
        console.log(response.data)
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    changeFeedInfo () {
      this.$refs.feed.changeFeedInfo()
    },
    editFeed () {
      this.$store.commit('editFeed', true)
      this.$EventBus.$emit('showPopup')
    },
    removeFeed (feedNum) {
      this.$store.dispatch('removeFeed', {feedNum}).then(
        this.$router.push('/mypage'),
        this.$EventBus.$emit('showPopup')
      )
    },
    cancelFollow () {
      this.$refs.feed.cancelFollow()
      this.$EventBus.$emit('showPopup')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
