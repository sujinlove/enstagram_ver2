<template>
  <div class="profile">
    <!-- Desktop -->
    <!-- in My Page -->
    <div class="user-pic" v-if="this.user.accnt_num === this.$store.state.user.accnt_num">
      <div class="user-pic-inner" v-if="this.$store.state.user.profile =='/profile/default.jpg'" >
        <img :src="this.$store.state.user.profile" :alt="this.$store.state.user.id + '님의 프로필 사진'" @click="UploadBtn"/>
      </div>
      <div class="user-pic-inner" v-else>
        <img :src="this.$store.state.user.profile" :alt="this.$store.state.user.id + '님의 프로필 사진'" @click="editUserProfile"/>
      </div>
      <form :style="{display: 'none'}">
        <input type="file" name="file" ref="file" accept="image/jpeg,image/png" @change="fileUpload"/>
      </form>
    </div>
    <!-- in My Page End -->
    <!-- in User Page -->
    <div class="user-pic" v-else>
      <div class="user-pic-inner">
        <img :src="this.user.profile" alt="profile_alt"/>
      </div>
    </div>
    <!-- in User Page End -->
    <div class="user-info">
      <!-- in My Page -->
      <div class="user-profile" v-if="this.user.accnt_num === this.$store.state.user.accnt_num">
        <div class="user-id"><span>{{ this.$store.state.user.id }}</span></div>
        <router-link to="/account/edit" class="user-edit-btn">프로필 편집</router-link>
        <div class="user-btn">
          <div class="icon-sprite ico-glyph setting" @click="editUserInfo"><span>setting</span></div>
        </div>
      </div>
      <!-- in My Page End -->
      <!-- in User Page -->
      <div class="user-profile" v-else>
        <div class="user-id"><span>{{ this.user.id }}</span></div>
        <div class="follow-btn">
          <button @click="addFollow(user.accnt_num)" class="follow" v-if="this.$store.state.user.followingList.indexOf(this.user.accnt_num) === -1">팔로우</button>
          <button @click="cancelFollow(user.accnt_num)" class="unfollow" v-else>팔로우</button>
        </div>
      </div>
      <!-- in User Page End -->
      <div class="user-count">
        <ul>
          <li>
            <span class="title">게시물</span>
            <span class="count" v-if="this.user.feedList">{{ this.user.feedList.length }}</span>
          </li>
          <li>
            <router-link :to="'/user/' + this.user.id + '/followers'">
              <span class="title">팔로워</span>
              <span class="count" v-if="this.user.followerList">{{ this.user.followerList.length }}</span>
            </router-link>
          </li>
          <li>
            <router-link :to="'/user/' + this.user.id + '/following'">
              <span class="title">팔로우</span>
              <span class="count" v-if="this.user.followingList">{{ this.user.followingList.length }}</span>
            </router-link>
          </li>
        </ul>
      </div>
      <div class="user-des">
        <p>{{ this.user.name }}</p>
        <p>{{ this.user.des }}</p>
      </div>
    </div>
    <!-- Desktop End -->
    <!-- Mobile -->
    <div class="user-des">
      <p>{{ this.user.name }}</p>
      <p>{{ this.user.des }}</p>
    </div>
    <div class="user-count" v-if="page === 'MyPage' || page === 'UserPage'">
      <ul>
        <li>
          <span class="title">게시물</span>
          <span class="count" v-if="this.user.feedList">{{ this.user.feedList.length }}</span>
        </li>
        <li>
          <router-link :to="'/user/' + this.user.id + '/followers'">
            <span class="title">팔로워</span>
            <span class="count" v-if="this.user.followerList">{{ this.user.followerList.length }}</span>
          </router-link>
        </li>
        <li>
          <router-link :to="'/user/' + this.user.id + '/following'">
            <span class="title">팔로우</span>
            <span class="count" v-if="this.user.followingList">{{ this.user.followingList.length }}</span>
          </router-link>
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
    addFollow (accntNum) {
      this.$store.dispatch('addFollow', {accntNum})
      this.$emit('get-user')
    },
    cancelFollow (accntNum) {
      this.$store.dispatch('cancelFollow', {accntNum})
      this.$emit('get-user')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
@import '../assets/css/source/profile.scss';
</style>
