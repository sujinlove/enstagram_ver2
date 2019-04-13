<template>
  <section>
    <navigation />
    <one-column />
    <footer-layout></footer-layout>
  </section>
</template>

<script>
import Navigation from '../components/common/Navigation.vue'
import OneColumn from '../components/common/OneColumn'
import Footer from '../components/common/Footer'

export default {
  components: {
    Navigation,
    OneColumn,
    'footer-layout': Footer
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
