<template>
  <!-- <div class="user-list">
    <ol>
      <li class="user" :key="follower" v-for="follower in followers">
        <div class="user-pic">
          <img src="https://scontent-hkg3-1.cdninstagram.com/vp/bd578d8e5cd331f6cd2c1efcbaa8b76d/5D322299/t51.2885-19/s320x320/46469858_263407874356482_8592698910261116928_n.jpg?_nc_ht=scontent-hkg3-1.cdninstagram.com" alt="vousmevoyez님의 프로필 사진" />
        </div>
        <div class="user-info">
          <router-link to= "" class="user-id">{{follower.userName}}</router-link>
          <div class="user-name">{{follower.contents}}</div>
        </div>
      </li>
    </ol>
  </div> -->
  <div class="user-wrapper">
    <div class="user-pic">
      <img :src="this.user.profile" :alt="this.user.id + '님의 프로필 사진'" />
    </div>
    <div class="user-info">
      <router-link to= "" class="user-id">{{this.user.id}}</router-link>
      <div class="user-name">{{this.user.name}}</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  props: ['following_num'],
  data () {
    return {
      user: {}
    }
  },
  created () {
    axios.get('/api/user/' + this.following_num).then((response) => {
      this.user = response.data
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scope>
</style>
