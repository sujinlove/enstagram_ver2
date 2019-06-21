<template>
  <section>
    <one-column>
      <account></account>
    </one-column>
    <footer-layout />
  </section>
</template>

<script>
import OneColumn from '../components/common/OneColumn'
import Account from '../components/Account'
import Footer from '../components/common/Footer'

export default {
  components: {
    OneColumn,
    Account,
    'footer-layout': Footer
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
