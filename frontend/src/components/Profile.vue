<template>
  <div class="profile">
    <!-- Desktop -->
    <!-- in My Page -->
    <div class="my-pic" v-if="this.user.accnt_num === this.$store.state.user.accnt_num">
      <div class="my-pic-inner" v-if="this.$store.state.user.profile =='/profile/default.jpg'" >
        <img :src="this.$store.state.user.profile" :alt="this.$store.state.user.id + '님의 프로필 사진'" @click="UploadBtn"/>
      </div>
      <div class="my-pic-inner" v-else>
        <img :src="this.$store.state.user.profile" :alt="this.$store.state.user.id + '님의 프로필 사진'" @click="editUserProfile"/>
      </div>
      <form :style="{display: 'none'}">
        <input type="file" name="file" ref="file" accept="image/jpeg,image/png" @change="fileUpload"/>
      </form>
    </div>
    <!-- in User Page -->
    <div class="my-pic" v-else>
      <div class="my-pic-inner">
        <img :src="this.user.profile" alt="profile_alt"/>
      </div>
    </div>
    <div class="my-info">
      <!-- in My Page -->
      <div class="my-profile" v-if="this.user.accnt_num === this.$store.state.user.accnt_num">
        <div class="my-id"><span>{{ this.$store.state.user.id }}</span></div>
        <router-link to="/account/edit" class="my-edit-btn">프로필 편집</router-link>
        <div class="my-btn">
          <div class="icon-sprite ico-glyph setting" @click="editUserInfo"><span>setting</span></div>
        </div>
      </div>
      <!-- in User Page -->
      <div class="my-profile" v-else>
        <div class="my-id"><span>{{ this.user.id }}</span></div>
        <button @click="addFollow" v-if="this.$store.state.user.followList.indexOf(this.user.accnt_num) === -1">팔로우</button>
        <button @click="cancelFollow" v-else>팔로잉</button>
      </div>
      <div class="my-count">
        <ul>
          <li>
            <span class="title" @click="testm">게시물</span>
            <span class="count">{{ this.$store.state.user.feedList.length }}</span>
          </li>
          <li>
            <span class="title">팔로워</span>
            <span class="count">{{ this.$store.state.user.followerList.length }}</span>
          </li>
          <li>
            <span class="title">팔로우</span>
            <span class="count">{{ this.$store.state.user.followList.length }}</span>
          </li>
        </ul>
      </div>
      <div class="my-des">
        <p>{{ this.$store.state.user.name }}</p>
        <p>{{ this.$store.state.user.des }}</p>
      </div>
    </div>
    <!-- Desktop End -->
    <!-- Mobile -->
    <div class="my-des">
      <p>{{ this.$store.state.user.name }}</p>
      <p>{{ this.$store.state.user.des }}</p>
    </div>
    <div class="my-count" v-if="page === 'MyPage'">
      <ul>
        <li>
          <span class="title">게시물</span>
          <span class="count">{{ this.$store.state.user.feedList.length }}</span>
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
  props: ['page', 'user'],
  data () {
    return {
      file: ''
    }
  },
  methods: {
    testm () {
      console.log(this.user)
      console.log(this.$store.state.user)
    },
    fileUpload () {
      this.file = this.$refs.file.files[0]
      var formData = new FormData()
      formData.append('file', this.file)
      axios.post('/api/profile', formData, {
      }).then(response => {
        this.$store.commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    UploadBtn () {
      this.$refs.file.click()
    },
    editUserProfile () {
      this.$store.commit('setPopupContent', 'editUserProfile')
      this.$EventBus.$emit('showPopup')
    },
    editUserInfo () {
      this.$store.commit('setPopupContent', 'editUserInfo')
      this.$EventBus.$emit('showPopup')
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
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
@import '../assets/css/source/profile.scss';
</style>
