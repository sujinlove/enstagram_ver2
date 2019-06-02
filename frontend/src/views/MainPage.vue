<template>
  <section class="main-page">
    <app-header />
    <two-columns>
      <feed slot="main" :page="PageName"></feed>
      <div slot="sidebar" class="profile">
        <div class="my-pic">
          <div class="my-pic-inner">
            <img :src="this.$store.state.user.profile" alt="ensta00_1님의 프로필 사진" />
          </div>
        </div>
        <div class="my-info">
          <p class="my-id">{{ this.$store.state.user.id }}</p>
          <p class="my-name">{{ this.$store.state.user.name }}</p>
        </div>
      </div>
      <follower-list slot="sidebar"></follower-list>
      <app-footer slot="sidebar"/>
    </two-columns>
  </section>
</template>

<script>
import Header from '../components/common/Header.vue'
import TwoColumns from '../components/common/TwoColumns'
import Feed from '../components/Feed'
import FollowerList from '../components/FollowerList'
import Footer from '../components/common/Footer'
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
    'app-footer': Footer,
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
<style lang="scss" scoped>
  .profile {
    width: 100%;
    margin-bottom: 12px;
  }

  .user-list {
    width: 100%;
    margin-bottom: 14px;
    border: 1px solid #e6e6e6;
    border-radius: 3px;
    box-sizing: border-box;
    color: red;
  }

  footer {
    .container {
      padding: 0;
    }
  }

  .my {
    &-pic {
      img {
        width: 50px;
        height: 50px;
      }
    }

    &-info {
      p {
        display: block;
        margin-bottom: 0;

        &.my-name {
          font-size: 12px;
          font-weight: 400;
          color: #999;
        }
      }
    }
  }
</style>
