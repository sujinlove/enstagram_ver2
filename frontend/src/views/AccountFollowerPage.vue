<template>
  <section>
    <app-header />
    <div class="user-list user-follower-list">
      <ol>
        <li class="user" :key="Follower" v-for="Follower in FollowerList">
          <user-list :user_num="Follower" :list="'follower'"></user-list>
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
  props: ['user_id'],
  data () {
    return {
      PageName: 'AccountFollowerPage',
      FollowerList: []
    }
  },
  created () {
    this.getUserInfo()
  },
  methods: {
    getUserInfo () {
      axios.post('/api/user/id/' + this.user_id, {
      }).then(response => {
        this.FollowerList = response.data.followerList
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
