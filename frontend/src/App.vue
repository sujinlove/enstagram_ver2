<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'App',
  components: {
  },
  data: function () {
    return {
    }
  },
  created () {
    axios.get('/api/user').then((response) => {
      this.$store.commit('setUser')
    }).catch(e => {
      console.log('error: ' + e)
    })
  }
}
</script>

<style lang="scss">
@import 'src/assets/css/source/style.scss';
</style>
