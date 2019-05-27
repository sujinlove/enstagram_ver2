<template>
  <section>
    <app-header />
    <div class="user-list">
      <ol>
        <li class="user" :key="heartAccount" v-for="heartAccount in heartAccountList">
          <div class="user-pic">
            <img :src="heartAccount.profile" :alt="heartAccount.id + '님의 프로필 사진'" />
          </div>
          <div class="user-info">
            <router-link :to= "{ name: 'UserPage', params: { user_id: heartAccount.id }}" class="user-id">{{heartAccount.id}}</router-link>
            <div class="user-name">{{heartAccount.name}}</div>
          </div>
        </li>
      </ol>
    </div>
    <app-footer />
  </section>
</template>

<script>
import axios from 'axios'
import Header from '../components/common/Header.vue'
import Footer from '../components/common/Footer'
export default {
  components: {
    'app-header': Header,
    'app-footer': Footer
  },
  props: ['feed_num'],
  data () {
    return {
      heartAccountList: []
    }
  },
  created () {
    this.fileUpload()
  },
  methods: {
    fileUpload () {
      console.log(this.feed_num)
      axios.get('/api/feed/' + this.feed_num + '/heart', {
      }).then(response => {
        this.heartAccountList = response.data
      }).catch(e => {
        console.log('error: ' + e)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
</style>
