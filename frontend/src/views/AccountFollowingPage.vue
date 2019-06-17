<template>
  <section>
    <app-header />
    <div class="user-list user-following-list" v-if="this.FollowingList.length > 0">
      <ol>
        <li class="user" :key="Following" v-for="Following in FollowingList">
          <user-list :user_num="Following" :list="'following'"></user-list>
        </li>
      </ol>
    </div>
    <div class="service follow" v-else>
      <div class="container">
        <div class="icon-sprite ico-core-2 follow"><span>follow</span></div>
        <div class="content-title">회원님이 팔로우하는 사람</div>
        <p>회원님을 팔로우하는 모든 사람이 여기에 표시됩니다.</p>
      </div>
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
