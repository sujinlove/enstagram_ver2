<template>
<article>
  <header>
    <div class="user-pic">
      <a href=""></a>
    </div>
    <div class="user-name">
      <a href=""></a>
    </div>
  </header>
  <div class="feed-pic">

  </div>
  <div class="feed-content">
    <div class="feed-btn">
      <dlv class="icon-sprite ico-glyph heart"><span>heart</span></dlv>
      <dlv class="icon-sprite ico-glyph chat"><span>chat</span></dlv>
    </div>
    <div class="heart-count">
      <span>좋아요 </span>
      <span>2313</span>
    </div>
    <div class="content-view">
      <ul>
        <li class="user-comment">
          <span class="user-name"></span>
          <span class="user-text"></span>
        </li>
        <li class="more-comment">
          <button>
              <span>댓글</span>
              <span>1231231</span>
              <span>개 모두 보기</span>
          </button>
        </li>
        <li class="other-comment">

        </li>
      </ul>
    </div>
    <div class="comment">
      <input type="text" placeholder="댓글 달기..."/>
      <button type="button">게시</button>
    </div>
  </div>
</article>
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
<style lang="scss">
</style>
