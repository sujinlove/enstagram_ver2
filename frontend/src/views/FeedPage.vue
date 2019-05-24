<template>
  <section>
    <app-header />
    <one-column>
      <div class="feed-view">
        <feed :page="PageName" :feed_num="feed_num"/>
      </div>
    </one-column>
    <popup>
      <button @click="feedRemove" v-if="$store.state.popupContent == 'feedService'">게시물 삭제</button>
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
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
