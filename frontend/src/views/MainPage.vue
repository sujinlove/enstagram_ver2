<template>
  <section>
    <app-header />
    <two-columns>
      <feed slot="main" :page="PageName"></feed>
      <div slot="sidebar" class="profile">
        <div class="my-pic">
          <div class="my-pic-inner">
            <img :src="this.$store.state.user.profile" alt="ensta00_1님의 프로필 사진" />
          </div>
        </div>
        <div class="my-name"><span>{{ this.$store.state.user.id }}</span></div>
          </div>
      <follower-list slot="sidebar"></follower-list>
    </two-columns>
  </section>
</template>

<script>
import Header from '../components/common/Header.vue'
import TwoColumns from '../components/common/TwoColumns'
import Feed from '../components/Feed'
import FollowerList from '../components/FollowerList'
import axios from 'axios'
export default {
  data () {
    return {
      PageName: 'MainPage',
      num: '',
      userName: '',
      contents: '',
      values: []
    }
  },
  components: {
    'app-header': Header,
    TwoColumns,
    Feed,
    FollowerList
    // num: num,
    // userName: userName,
    // contents: contents
  },
  created () {
    axios.get('/api/board').then((response) => {
      if (response.status === 200) {
        this.values = response.data
      }
    })
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
.profile {
  margin-bottom: 12px;
}

.my {
  &-pic {
    img {
      width: 50px;
      height: 50px;
    }
  }
}
</style>
