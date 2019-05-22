<template>
  <section>
    <app-header />
    <one-column>
      <profile ref="editProfile" />
      <div class="feed-list">
        <ul>
          <li class="feed-item"><img src="https://scontent-icn1-1.cdninstagram.com/vp/8cdc5dfb2d8655371613a490edf22cf1/5D775FB7/t51.2885-15/sh0.08/e35/s640x640/56219791_130014521491636_8631154733078667631_n.jpg?_nc_ht=scontent-icn1-1.cdninstagram.com" alt="ensta00_1님의 사진" /></li>
          <li class="feed-item"><img src="https://scontent-icn1-1.cdninstagram.com/vp/8cdc5dfb2d8655371613a490edf22cf1/5D775FB7/t51.2885-15/sh0.08/e35/s640x640/56219791_130014521491636_8631154733078667631_n.jpg?_nc_ht=scontent-icn1-1.cdninstagram.com" alt="ensta00_1님의 사진" /></li>
          <li class="feed-item"><img src="https://scontent-icn1-1.cdninstagram.com/vp/8cdc5dfb2d8655371613a490edf22cf1/5D775FB7/t51.2885-15/sh0.08/e35/s640x640/56219791_130014521491636_8631154733078667631_n.jpg?_nc_ht=scontent-icn1-1.cdninstagram.com" alt="ensta00_1님의 사진" /></li>
          <li class="feed-item"><img src="https://scontent-icn1-1.cdninstagram.com/vp/8cdc5dfb2d8655371613a490edf22cf1/5D775FB7/t51.2885-15/sh0.08/e35/s640x640/56219791_130014521491636_8631154733078667631_n.jpg?_nc_ht=scontent-icn1-1.cdninstagram.com" alt="ensta00_1님의 사진" /></li>
        </ul>
      </div>
    </one-column>
    <footer-layout />
    <popup>
        <button @click="fileUpload" v-if="$store.state.popupContent == 'editUserProfile'">사진 업로드</button>
        <a href="/logout" v-if="$store.state.popupContent == 'editUserInfo'">로그아웃</a>
    </popup>
  </section>
</template>

<script>
import Header from '../components/common/Header.vue'
import OneColumn from '../components/common/OneColumn'
import Profile from '../components/Profile'
import Footer from '../components/common/Footer'
import Popup from '../components/common/Popup'

export default {
  components: {
    'app-header': Header,
    OneColumn,
    Profile,
    'footer-layout': Footer,
    Popup
  },
  data () {
    return {
      editUserProfile: true,
      editUserInfo: false
    }
  },
  methods: {
    fileUpload () {
      this.$refs.editProfile.UploadBtn()
      this.$EventBus.$emit('showPopup')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scope>
@import '../assets/css/source/feeds.scss';
@media only screen and (max-width: ($screen__m - 1)) {
  main {
    padding-left: 0;
    padding-right: 0;
  }
}
</style>
