<template>
  <section>
    <app-header />
    <one-column />
    <footer-layout></footer-layout>
  </section>
</template>

<script>
import Header from '../components/common/Header.vue'
import OneColumn from '../components/common/OneColumn'
import Footer from '../components/common/Footer'

export default {
  components: {
    'app-header': Header,
    OneColumn,
    'footer-layout': Footer
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
