<template>
<article class="feed">
  <header class="feed-header">
    <div class="user-pic">
      <a href="">
        <img src="https://scontent-hkg3-1.cdninstagram.com/vp/10f87370152bb3d796a1bb4088410993/5D4FEB78/t51.2885-19/s150x150/55734804_1068234573371318_7617162252218531840_n.jpg?_nc_ht=scontent-hkg3-1.cdninstagram.com"  alt="lalalalisa_m님의 프로필 사진"/>
      </a>
    </div>
    <div class="user-name">
      <a href="">lalalalisa_m</a>
    </div>
  </header>
  <div class="feed-pic">
    <img :src="this.feed.feed_pic" alt="feed" />
  </div>
  <div class="feed-content">
    <div class="feed-content-inner">
      <div class="feed-comment" v-if="page == 'FeedPage'">
        <ul>
            <li class="user-comment">
              <span class="user-name">jennierubyjane</span>
              <span class="user-text">나는 피드 제니!</span>
            </li>
        </ul>
      </div>
      <div class="feed-btn">
        <button class="icon-sprite ico-glyph heart"><span>heart</span></button>
        <router-link to= "/feed" class="icon-sprite ico-glyph chat"><span>chat</span></router-link>
      </div>
      <div class="heart-count">
        <span>좋아요 </span>
        <span>{{this.feed.heart}}</span>
        <span>개</span>
      </div>
      <div class="content-view">
        <ul>
          <li class="feed-comment" v-if="page == 'MainPage'">
            <router-link to= "" class="user-name">lalalalisa_m</router-link>
            <span class="feed-text">블라블라블라</span>
            <router-link to= "/feed">더보기</router-link>
          </li>
          <li class="more-comment" v-if="page == 'MainPage'">
            <router-link to= "/feed">
                <span>댓글</span>
                <span>1231231</span>
                <span>개 모두 보기</span>
            </router-link>
          </li>
          <li class="user-comment" v-if="page == 'MainPage'">
            <router-link to= "" class="user-name">jennierubyjane</router-link>
            <span class="user-text">나는 메인 제니!</span>
          </li>
        </ul>
      </div>
    </div>
    <div class="comment">
      <textarea placeholder="댓글 달기..."/>
      <button type="button">게시</button>
    </div>
  </div>
</article>
</template>

<script>
import axios from 'axios'
export default {
  props: ['page', 'feed_num'],
  data () {
    return {
      feed: {
        feed_pic: '',
        heart: '',
        regdate: ''
      }
    }
  },
  created () {
    axios.post('/api/feed/' + this.feed_num, {
    }).then(response => {
      this.feed.feed_pic = response.data[0].file_name
      this.feed.heart = response.data[0].heart
      this.feed.regdate = response.data[0].regdate
    }).catch(e => {
      console.log('error: ' + e)
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scope>
@import '../assets/css/source/feeds.scss';
</style>
