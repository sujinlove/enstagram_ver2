<template>
  <section>
    <app-header />
    <one-column>
      <div class="feed-view">
        <feed :page="PageName"/>
      </div>
    </one-column>
  </section>
</template>

<script>
import Header from '../components/common/Header.vue'
import OneColumn from '../components/common/OneColumn'
import Feed from '../components/Feed'

export default {
  data () {
    return {
      PageName: 'FeedPage'
    }
  },
  components: {
    'app-header': Header,
    OneColumn,
    Feed
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
