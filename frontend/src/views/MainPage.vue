<template>
  <section>
    <navigation />
    <one-column />
  </section>
</template>

<script>
import Navigation from '../components/common/Navigation.vue'
import OneColumn from '../components/common/OneColumn'

export default {
  components: {
    Navigation,
    OneColumn
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
