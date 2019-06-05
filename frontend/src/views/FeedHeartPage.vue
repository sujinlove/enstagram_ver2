<template>
  <section>
    <app-header />
    <div class="user-list user-heart-list">
      <ol>
        <li class="user" :key="heartAccount" v-for="heartAccount in heartAccountList">
          <user-list :user_num="heartAccount" :list="'heartAccount'"></user-list>
        </li>
      </ol>
    </div>
    <app-footer />
  </section>
</template>

<script>
import axios from 'axios'
import Header from '../components/common/Header.vue'
import UserList from '../components/UserList'
import Footer from '../components/common/Footer'
export default {
  components: {
    'app-header': Header,
    'app-footer': Footer,
    UserList
  },
  props: ['feed_num'],
  data () {
    return {
      PageName: 'FeedHeartPage',
      heartAccountList: []
    }
  },
  created () {
    this.fileUpload()
  },
  methods: {
    fileUpload () {
      console.log(this.feed_num)
      axios.get('/api/feed/' + this.feed_num + '/heart', {
      }).then(response => {
        this.heartAccountList = response.data
      }).catch(e => {
        console.log('error: ' + e)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
</style>
