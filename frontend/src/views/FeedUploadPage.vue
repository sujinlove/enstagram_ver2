<template>
  <section>
    <app-header :page="PageName" v-on:upload="fileUpload"/>
    <one-column>
      <div class="feed-view">
        <form class="form" name="form" id="feedForm">
          <div class="upload-feed">
            <div class="user-pic" :style="{width: '30px', height: '30px'}">
              <div class="user-pic-inner">
                <img :src="this.$store.state.user.profile" :alt="this.$store.state.id + '님의 프로필 사진'"/>
              </div>
            </div>
            <textarea type="textarea" name="description" placeholder="문구 입력..." :style="{height: '48px'}" v-model="description"/>
            <img id="feed-file" :style="{width: '48px', height: '48px'}"/>
          </div>
        </form>
      </div>
    </one-column>
  </section>
</template>

<script>
import Header from '../components/common/Header.vue'
import OneColumn from '../components/common/OneColumn'
import axios from 'axios'

export default {
  data () {
    return {
      PageName: 'FeedUploadPage',
      description: '',
      file: ''
    }
  },
  components: {
    'app-header': Header,
    OneColumn
  },
  created () {
    if (this.$store.state.uploadFile !== '') {
      var reader = new FileReader()
      reader.onload = function (e) {
        document.getElementById('feed-file').src = e.target.result
      }
      reader.readAsDataURL(this.$store.state.uploadFile)
    } else {
      this.$router.push('/')
    }
  },
  methods: {
    fileUpload () {
      var formData = new FormData()
      formData.append('file', this.$store.state.uploadFile)
      formData.append('description', this.description)
      formData.append('accnt_num', this.$store.state.user.accnt_num)
      axios.post('/api/feedUpload', formData, {
      }).then(response => {
        this.$store.commit('uploadFile', '')
        this.$router.push('/feed/' + response.data)
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    handleFileUpload () {
      this.file = this.$refs.file.files[0]
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.user-pic {
  margin-right: 6px;
}
</style>
