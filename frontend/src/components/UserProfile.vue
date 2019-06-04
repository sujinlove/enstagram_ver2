<template>
  <div class="profile">
    <!-- Desktop -->
    <div class="my-pic">
      <div class="my-pic-inner">
        <img :src="this.user.profile" alt="profile_alt"/>
      </div>
    </div>
    <div class="my-info">
      <div class="my-profile">
        <div class="my-id"><span>{{ this.user.id }}</span></div>
      </div>
      <button @click="addFollow" v-if="this.$store.state.user.followList.indexOf(this.user.accnt_num) === -1">팔로우</button>
      <button @click="cancelFollow" v-else>팔로잉</button>
      <div class="my-count">
        <ul>
          <li>
            <span class="title" @click="testm">게시물</span>
            <span class="count">{{ this.user.feedList.length }}</span>
          </li>
          <li>
            <span class="title">팔로워</span>
            <span class="count">1</span>
          </li>
          <li>
            <span class="title">팔로우</span>
            <span class="count">1</span>
          </li>
        </ul>
      </div>
      <div class="my-des">
        <p>{{ this.user.name }}</p>
        <p>{{ this.user.des }}</p>
      </div>
    </div>
    <!-- Desktop End -->
    <!-- Mobile -->
    <div class="my-des">
      <p>{{ this.user.name }}</p>
      <p>{{ this.user.des }}</p>
    </div>
    <div class="my-count" v-if="page === 'MyPage'">
      <ul>
        <li>
          <span class="title">게시물</span>
          <span class="count">{{ this.user.feedList.length }}</span>
        </li>
        <li>
          <span class="title">팔로워</span>
          <span class="count">1</span>
        </li>
        <li>
          <span class="title">팔로우</span>
          <span class="count">1</span>
        </li>
      </ul>
    </div>
    <!-- Mobile End -->
  </div>
</template>

<script>
import axios from 'axios'
export default {
  props: ['user', 'page'],
  data () {
    return {
    }
  },
  created () {
  },
  methods: {
    testm () {
      console.log(this.$store.state.user.followList)
      console.log(this.user.accnt_num)
    },
    addFollow () {
      axios.post('/api/user/follow', {
        accnt_num: this.$store.state.user.accnt_num,
        following_num: this.user.accnt_num
      }).then(response => {
        this.$store.commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    cancelFollow () {
      axios.post('/api/user/unfollow', {
        accnt_num: this.$store.state.user.accnt_num,
        following_num: this.user.accnt_num
      }).then(response => {
        this.$store.commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
    }
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
