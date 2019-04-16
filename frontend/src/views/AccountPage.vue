<template>
  <section>
    <two-columns>
      <div slot="main">
        <img src="https://www.instagram.com/static/images/homepage/home-phones.png/43cc71bb1b43.png" alt="" />
      </div>
      <account slot="sidebar"></account>
    </two-columns>
  </section>
</template>

<script>
import TwoColumns from '../components/common/TwoColumns'
import Feed from '../components/Feed'
import Account from '../components/Account'

export default {
  components: {
    TwoColumns,
    Feed,
    Account
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">

</style>
