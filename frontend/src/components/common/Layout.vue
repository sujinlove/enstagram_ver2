<template>
  <div id="app-layout">
    <navigation />
    <main></main>
    <router-view></router-view>
  </div>
</template>

<script>
import Navigation from './Navigation.vue'

export default {
  components: {
    'navigation': Navigation
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
