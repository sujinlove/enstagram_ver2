<template>
  <section>
    <one-column>
      <div>
        <img src="https://www.instagram.com/static/images/homepage/home-phones.png/43cc71bb1b43.png" alt="" />
      </div>
      <account></account>
    </one-column>
  </section>
</template>

<script>
import OneColumn from '../components/common/OneColumn'
import Feed from '../components/Feed'
import Account from '../components/Account'

export default {
  components: {
    OneColumn,
    Feed,
    Account
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">

</style>
