<template>
  <div class="user-list">
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
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      followers: []
    }
  },
  created () {
    axios.get('/api/board').then((response) => {
      if (response.status === 200) {
        this.followers = response.data
      }
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scope>
</style>
