<template>
  <header>
    <nav>
      <div>
        <ul class="container">
          <li class="logo">
            <router-link to ="/" class="icon-sprite ico-glyph instagram-logo_ver1"><span>logo</span></router-link>
            <div class="bar"><span>bar</span></div>
            <router-link to ="/" class="icon-sprite ico-glyph instagram-logo_ver2"><span>logo</span></router-link>
          </li>
          <li class="search">
            <input type="text" placeholder="검색"/>
            <div class="searchIcon ico-core icon-sprite"><span>search</span></div>
            <div class="searchClear ico-core icon-sprite"><span>search clear</span></div>
          </li>
          <li class="others">
            <router-link to ="" class="icon-sprite ico-glyph upload"><span>upload</span></router-link>
            <router-link to ="/mypage" class="icon-sprite ico-glyph user"><span>user</span></router-link>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  data () {
    return {
    }
  }
}
</script>
