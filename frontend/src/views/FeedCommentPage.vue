<template>
  <section>
    <app-header :page="PageName"/>
    <div class="feed-comment-view">
      <feed-comments :feed="feed" :user="user" :page="PageName"/>
    </div>
  </section>
</template>

<script>
import axios from 'axios'
import Header from '../components/common/Header.vue'
import FeedComments from '../components/FeedComments'

export default {
  props: ['feed_num'],
  data () {
    return {
      PageName: 'FeedCommentPage',
      feed: {},
      user: {}
    }
  },
  components: {
    'app-header': Header,
    FeedComments
  },
  created () {
    this.getFeedInfo()
  },
  methods: {
    getFeedInfo () {
      axios.post('/api/feed/' + this.feed_num, {
      }).then(response => {
        this.feed = response.data
        this.getUserInfo()
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    getUserInfo () {
      axios.post('/api/user/' + this.feed.accnt_num, {
      }).then(response => {
        this.user = response.data
      }).catch(e => {
        console.log('error: ' + e)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
