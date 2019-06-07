<template>
  <section>
    <app-header v-on:changeFeedInfo="changeFeedInfo"/>
    <one-column>
      <div class="feed-view">
        <feed :page="PageName" :feed_num="feed_num" ref="feed"/>
      </div>
    </one-column>
    <popup>
      <button @click="cancelFollow" v-if="$store.state.popupContent == 'feedService'">팔로우 취소</button>
      <button @click="editFeed" v-if="$store.state.popupContent == 'feedService' && this.$store.state.user.feedList.indexOf(this.feed_num) !== -1">게시물 수정</button>
      <button @click="removeFeed" v-if="$store.state.popupContent == 'feedService' && this.$store.state.user.feedList.indexOf(this.feed_num) !== -1">게시물 삭제</button>
    </popup>
  </section>
</template>

<script>
import axios from 'axios'
import Header from '../components/common/Header.vue'
import OneColumn from '../components/common/OneColumn'
import Feed from '../components/Feed'
import Popup from '../components/common/Popup'

export default {
  props: ['feed_num'],
  data () {
    return {
      PageName: 'FeedPage'
    }
  },
  components: {
    'app-header': Header,
    OneColumn,
    Feed,
    Popup
  },
  methods: {
    changeFeedInfo () {
      this.$refs.feed.changeFeedInfo()
    },
    editFeed () {
      this.$store.commit('editFeed', true)
      this.$EventBus.$emit('showPopup')
    },
    removeFeed () {
      axios.post('/api/feed/remove', {
        accnt_num: this.$store.state.user.accnt_num,
        feed_num: this.feed_num
      }).then(response => {
        this.$router.push('/mypage')
      }).catch(e => {
        console.log('error: ' + e)
      })
      this.$EventBus.$emit('showPopup')
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
