<template>
  <section>
    <app-header />
    <one-column />
  </section>
</template>

<script>
import Header from '../components/common/Header.vue'
import OneColumn from '../components/common/OneColumn'

export default {
  components: {
    'app-header': Header,
    OneColumn
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
