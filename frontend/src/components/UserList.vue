<template>
  <li class="user">
    <div class="user-wrapper">
      <div class="user-pic">
        <div class="user-pic-inner">
          <img :src="this.user.profile" :alt="this.user.id + '님의 프로필 사진'" />
        </div>
      </div>
      <div class="user-info">
        <router-link :to="'/user/' + this.user.id" class="user-id">{{this.user.id}}</router-link>
        <div class="user-name">{{this.user.name}}</div>
      </div>
      <div class="follow-btn" v-if="list === 'recommend' || list === 'following' || list === 'follower' || list === 'heartAccount'">
        <button @click="addFollow(user.accnt_num)" class="follow" v-if="this.$store.state.user.followingList.indexOf(this.user.accnt_num) === -1">팔로우</button>
        <button @click="cancelFollow(user.accnt_num)" class="unfollow" v-else>팔로잉</button>
      </div>
    </div>
  </li>
</template>

<script>
import axios from 'axios'

export default {
  props: ['page', 'user_num', 'list'],
  data () {
    return {
      user: {}
    }
  },
  created () {
    axios.get('/api/user/' + this.user_num).then((response) => {
      this.user = response.data
    })
  },
  methods: {
    addFollow (accntNum) {
      this.$store.dispatch('addFollow', {accntNum})
    },
    cancelFollow (accntNum) {
      this.$store.dispatch('cancelFollow', {accntNum})
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.follow-btn {
  text-align: right;
}
</style>
