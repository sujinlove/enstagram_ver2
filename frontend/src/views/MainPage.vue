<template>
  <section>
    <app-header />
    <two-columns>
      <feed slot="main"></feed>
      <follower-list slot="sidebar"></follower-list>
    </two-columns>
  </section>
</template>

<script>
import Header from '../components/common/Header.vue'
import TwoColumns from '../components/common/TwoColumns'
import Feed from '../components/Feed'
import FollowerList from '../components/FollowerList'

export default {
  components: {
    'app-header': Header,
    TwoColumns,
    Feed,
    FollowerList
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
