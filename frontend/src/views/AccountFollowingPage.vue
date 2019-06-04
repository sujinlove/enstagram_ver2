<template>
  <section>
    <app-header />
    <div class="user-list user-following-list">
      <ol>
        <li class="user" :key="Following" v-for="Following in FollowingList">
          <user-list :user_num="Following" :page="PageName"></user-list>
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
      PageName: 'AccountFollowingPage',
      FollowingList: []
    }
  },
  created () {
    this.getUserInfo()
  },
  methods: {
    getUserInfo () {
      axios.post('/api/user/id/' + this.user_id, {
      }).then(response => {
        this.FollowingList = response.data.followingList
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
