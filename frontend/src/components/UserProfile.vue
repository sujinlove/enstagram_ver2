<template>
  <div class="profile">
    <div class="my-pic">
      <div class="my-pic-inner">
        <img :src="this.user.profile" alt="profile_alt"/>
      </div>
    </div>
    <div class="my-info">
      <div class="my-profile">
        <div class="my-name">
          <span>{{ this.user.id }}</span>
          <button type="button" @click="followStatus">{{ follow }}</button>
        </div>
      </div>
      <div class="my-count">
        <ul>
          <li>
            <span>게시물</span>
            <span class="feed-count">1</span>
          </li>
          <li>
            <span>팔로워</span>
            <span class="follower-count">1</span>
          </li>
          <li>
            <span>팔로우</span>
            <span class="follow-count">1</span>
          </li>
        </ul>
      </div>
      <div class="my-des">{{ this.user.name }}</div>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
export default {
  props: ['user_id'],
  data () {
    return {
    //   follow: '팔로우',
    //   status: '',
    //   count: '',
    //   user: {
    //     id: '',
    //     profile: '',
    //     name: ''
    //   }
    }
  },
  methods: {
    // followStatus () {
    //   this.count++
    //   if (this.count % 2 === 0) {
    //     this.follow = '팔로우'
    //     this.status = 0
    //   } else {
    //     this.follow = '팔로잉'
    //     this.status = 1
    //   }
    //   console.log(this.follow)
    //   console.log(this.status)
    //   var formData = new FormData()
    //   formData.append('accnt_id', this.user.id)
    //   formData.append('status', this.status)
    //   axios.post('/api/follow', formData, {
    //   }).then((response) => {
    //     console.log(this.follow + this.status)
    //   })
  // }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
@import '../assets/css/source/profile.scss';
</style>
