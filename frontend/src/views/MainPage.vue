<template>
  <section>
    <app-header />
    <two-columns>
      <feed slot="main"></feed>
      <div slot="sidebar" class="profile">
        <div class="my-pic">
          <div class="my-pic-inner">
            <img src="https://instagram.fbed1-2.fna.fbcdn.net/vp/0ad6ead8ba1fc621ef2107c6d137d264/5D2CF3F1/t51.2885-19/44884218_345707102882519_2446069589734326272_n.jpg?_nc_ht=instagram.fbed1-2.fna.fbcdn.net" alt="ensta00_1님의 프로필 사진" />
          </div>
        </div>
        <div class="my-name"><span>ensta00_1</span></div>
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

export default {
  components: {
    'app-header': Header,
    TwoColumns,
    Feed,
    FollowerList
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
