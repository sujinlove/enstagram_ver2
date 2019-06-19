<template>
  <header>
    <nav>
      <div>
        <!--Feed Upload Page-->
        <ul class="container" v-if="page === 'FeedUploadPage'">
          <li class="back">
            <button class="icon-sprite ico-glyph-3 back" @click="goToBack"><span>back</span></button>
          </li>
          <li class="page-title"><h1>새 게시물</h1></li>
          <li class="complete">
            <div class="upload-loading loading" v-if="loading === true"></div>
            <button class="upload-btn" @click="fileUpload">
              공유하기
            </button>
          </li>
        </ul>
        <!--Feed Comment Page-->
        <ul class="container" v-if="page === 'FeedCommentPage'">
          <li class="back">
            <button class="icon-sprite ico-glyph-3 back" @click="goToBack"><span>back</span></button>
          </li>
          <li class="page-title"><h1>댓글</h1></li>
        </ul>
        <!--Feed Edit-->
        <ul class="container" v-if="this.$store.state.editFeed === true">
          <li class="back">
            <button class="icon-sprite ico-glyph-3 back" @click="goToBack">
              <span>back</span>
            </button>
          </li>
          <li class="page-title"><h1>정보 수정</h1></li>
          <li class="complete">
            <button class="upload-btn" @click="changeFeedInfo">
              완료
            </button>
          </li>
        </ul>
        <!--Common Page-->
        <ul class="container" v-if="page !== 'FeedUploadPage' && page !== 'FeedCommentPage' && this.$store.state.editFeed !== true">
          <li class="logo">
            <router-link to ="/" class="icon-sprite ico-glyph instagram-logo_ver1"><span>logo</span></router-link>
            <div class="bar"><span>bar</span></div>
            <router-link to ="/" class="icon-sprite ico-glyph instagram-logo_ver2"><span>logo</span></router-link>
          </li>
          <li class="search">
            <input type="text" placeholder="검색" v-model="searchText"/>
            <div class="searchIcon ico-core icon-sprite"><span>search</span></div>
            <div class="searchClear ico-core icon-sprite" @click="searchText = ''"><span>search clear</span></div>
            <div class="search-user user-list" v-if="searchText !== ''">
              <div class="search-box">
                <ol v-if="this.searchList.length > 0">
                  <user-list class="user" :key="search" v-for="search in this.searchList" :user_num="search" :list="'search'"/>
                </ol>
                <p v-else>검색 결과가 없습니다.</p>
              </div>
            </div>
          </li>
          <li class="others">
            <!-- <router-link to ="/feedUpload" class="icon-sprite ico-glyph upload"><span>upload</span></router-link> -->
            <button class="icon-sprite ico-glyph upload" @click="uploadBtn"><span>upload</span></button>
            <form :style="{display: 'none'}">
              <input type="file" name="file" ref="file" accept="image/jpeg,image/png,video/mp4,video/mov" @change="handleFileUpload"/>
            </form>
            <a :href="'/user/' + this.$store.state.user.id" class="icon-sprite ico-glyph mypage"><span>my page</span></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</template>

<script>
import axios from 'axios'
import UserList from '../UserList'

export default {
  props: ['page'],
  components: {
    UserList
  },
  data () {
    return {
      searchText: '',
      searchList: [],
      loading: false
    }
  },
  watch: {
    searchText: function (newValue) {
      if (newValue === '') {
        this.searchList = []
      } else {
        this.searchUser(newValue)
      }
    }
  },
  methods: {
    goToBack () {
      this.$router.go(-1)
      this.$store.commit('editFeed', false)
    },
    changeFeedInfo () {
      this.$emit('changeFeedInfo')
    },
    fileUpload () {
      this.loading = true
      this.$emit('upload')
    },
    uploadBtn () {
      this.$refs.file.click()
    },
    handleFileUpload () {
      this.file = this.$refs.file.files[0]
      this.$store.commit('uploadFile', this.file)
      this.$router.push('/feedUpload')
    },
    searchUser (userId) {
      axios.post('/api/searchId', {
        id: userId
      }).then(response => {
        this.searchList = response.data
      }).catch(e => {
        console.log('error: ' + e)
      })
    }
  }
}
</script>
