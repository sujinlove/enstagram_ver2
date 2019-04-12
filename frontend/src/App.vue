<template>
  <div id="app">
    <app-layout />
  </div>
</template>

<script>
import Layout from './components/common/Layout.vue'

export default {
  name: 'App',
  components: {
    'app-layout': Layout
  }
}
</script>

<style lang="scss">
@import 'src/assets/css/source/style.scss';
</style>
