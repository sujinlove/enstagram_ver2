<template>
  <div class="follow-list">
    <ol>
      <li class="follower">
        <div class="follower-pic">
          <img src="https://scontent-hkg3-1.cdninstagram.com/vp/bd578d8e5cd331f6cd2c1efcbaa8b76d/5D322299/t51.2885-19/s320x320/46469858_263407874356482_8592698910261116928_n.jpg?_nc_ht=scontent-hkg3-1.cdninstagram.com" alt="vousmevoyez님의 프로필 사진" />
        </div>
        <div class="follower-info">
          <router-link to= "" class="follower-name">vousmevoyez</router-link>
          <div class="follower-des">KRYSTAL</div>
        </div>
      </li>
    </ol>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scope>
@import '../assets/css/source/follow.scss';
</style>
