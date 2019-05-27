<template>
  <div class="profile">
    <div class="my-pic">
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
    <div class="my-info">
      <div class="my-profile">
        <div class="my-name"><span>{{ this.$store.state.user.id }}</span></div>
        <div class="my-btn">
          <div class="icon-sprite ico-glyph setting" @click="editUserInfo"><span>setting</span></div>
        </div>
      </div>
      <div class="my-count">
        <ul>
          <li>
            <span>게시물</span>
            <span class="feed-count">{{ this.$store.state.user.feedList.length }}</span>
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
      <div class="my-des">{{ this.$store.state.user.name }}</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
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
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
@import '../assets/css/source/profile.scss';
</style>
