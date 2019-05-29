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
        <div class="my-name"><span>{{ this.$store.state.user.id }}</span></div>
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
<style lang="scss">
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
  }

  footer {
    .container {
      padding: 0;

      nav li {
        &:not(:last-child) {
          &:after {
            content: '\00B7';
            margin: 0 2px;
          }
        }
      }
    }
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
