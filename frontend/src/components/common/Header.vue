<template>
  <header>
    <nav>
      <div>
        <ul class="container">
          <li class="logo">
            <router-link to ="/" class="icon-sprite ico-glyph instagram-logo_ver1"><span>logo</span></router-link>
            <div class="bar"><span>bar</span></div>
            <router-link to ="/" class="icon-sprite ico-glyph instagram-logo_ver2"><span>logo</span></router-link>
          </li>
          <li class="search">
            <input type="text" placeholder="검색"/>
            <div class="searchIcon ico-core icon-sprite"><span>search</span></div>
            <div class="searchClear ico-core icon-sprite"><span>search clear</span></div>
          </li>
          <li class="others" v-if="page !== 'FeedUploadPage'">
            <!-- <router-link to ="/feedUpload" class="icon-sprite ico-glyph upload"><span>upload</span></router-link> -->
            <button class="icon-sprite ico-glyph upload" @click="uploadBtn"><span>upload</span></button>
            <form :style="{display: 'none'}">
              <input type="file" name="file" ref="file" @change="handleFileUpload"/>
            </form>
            <router-link to ="/mypage" class="icon-sprite ico-glyph user"><span>user</span></router-link>
          </li>
          <li class="others" v-else>
            <button class="upload-btn" @click="fileUpload">
              공유하기
            </button>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  props: ['page'],
  data () {
    return {
    }
  },
  methods: {
    fileUpload () {
      this.$emit('upload')
    },
    uploadBtn () {
      this.$refs.file.click()
    },
    handleFileUpload () {
      this.file = this.$refs.file.files[0]
      this.$store.commit('uploadFile', this.file)
      this.$router.push('/feedUpload')
    }
  }
}
</script>
