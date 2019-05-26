<template>
  <section>
    <app-header />
    <one-column>
      <div class="feed-view">
        <form class="form" name="form" id="feedForm">
          <fieldset>
            <input type="hidden" v-model="feed_num"/>
            <div>
              <label>첨부파일 : </label>
              <input type="file" name="file" ref="file" @change="handleFileUpload"/>
            </div>
            <div>
              <label>description : </label>
              <textarea type="textarea" name="description" v-model="description"/>
            </div>
            <div>
              <button type="button" @click="fileUpload">등록하기</button>
            </div>
          </fieldset>
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
      description: '',
      file: ''
    }
  },
  components: {
    'app-header': Header,
    OneColumn
  },
  methods: {
    fileUpload () {
      var formData = new FormData()
      formData.append('file', this.file)
      formData.append('description', this.description)
      formData.append('accnt_num', this.$store.state.user.accnt_num)
      axios.post('/api/feedUpload', formData, {
      }).then(response => {
        console.log('1: ' + this.file_name, this.description)
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    handleFileUpload () {
      this.file = this.$refs.file.files[0]
      console.log(this.file)
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
