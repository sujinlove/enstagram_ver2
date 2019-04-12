<template>
  <div id="app">
    <one-column />
  </div>
</template>

<script>
import OneColumn from './components/common/OneColumn'

export default {
  name: 'App',
  components: {
    OneColumn
  }
}
</script>

<style lang="scss">
@import 'src/assets/css/source/style.scss';
</style>
