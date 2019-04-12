<template>
<nav>
  <ul>
    <li class="logo">
      <a href="" class="icon-sprite ico-glyph instagram-logo_ver1"><span>logo</span></a>
      <div class="bar"><span>bar</span></div>
      <a href="" class="icon-sprite ico-glyph instagram-logo_ver2"><span>logo</span></a>
    </li>
    <li class="search">
      <input type="text" placeholder="검색"/>
      <div class="searchIcon ico-core icon-sprite"><span>search</span></div>
      <div class="searchClear ico-core icon-sprite"><span>search clear</span></div>
    </li>
    <li class="others">
      <div class="icon-sprite ico-glyph upload"><span>upload</span></div>
      <a href="" class="icon-sprite ico-glyph user"><span>user</span></a>
    </li>
  </ul>
</nav>
</template>

<script>
export default {
  name: 'Navigation',
  data () {
    return {
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
@import 'src/assets/css/source/navigation.scss';
</style>
