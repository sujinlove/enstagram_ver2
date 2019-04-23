<template>
  <div class="profile">
    <div class="my-pic">
      <div class="my-pic-inner">
        <img src="https://instagram.fbed1-2.fna.fbcdn.net/vp/0ad6ead8ba1fc621ef2107c6d137d264/5D2CF3F1/t51.2885-19/44884218_345707102882519_2446069589734326272_n.jpg?_nc_ht=instagram.fbed1-2.fna.fbcdn.net" alt="ensta00_1님의 프로필 사진" />
      </div>
    </div>
    <div class="my-info">
      <div class="my-profile">
        <div class="my-name"><span>ensta00_1</span></div>
        <div class="my-btn">
          <div class="icon-sprite ico-glyph setting"><span>setting</span></div>
        </div>
      </div>
      <div class="my-count">
        <ul>
          <li>
            <span>게시물</span>
            <span class="feed-count">1</span>
          </li>
          <li>
            <span>팔로워</span>
            <span class="follower-count">1</span>
          </li>
          <li>
            <span>팔로우</span>
            <span class="follow-count">1</span>
          </li>
        </ul>
      </div>
      <div class="my-des">vue~~~~~enstagram~~</div>
    </div>
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
<style lang="scss" scoped>
@import '../assets/css/source/profile.scss';
</style>
