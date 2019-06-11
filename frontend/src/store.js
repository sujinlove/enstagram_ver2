import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      heartList: [],
      feedList: [],
      followingList: [],
      followerList: []
    },
    popupContent: '',
    selectFeed: {},
    selectComment: {},
    // pageImages: [],
    uploadFile: '',
    editFeed: false
  },
  mutations: {
    setUser (state) {
      axios.get('/api/user').then((response) => {
        state.user = response.data
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    setPopupContent (state, data) {
      state.popupContent = data
    },
    selectFeed (state, data) {
      state.selectFeed = data
    },
    selectComment (state, data) {
      state.selectComment = data
    },
    // setPreImages (state, data) {
    //   const image = new Image()
    //   image.src = data
    //   state.pageImages.push(image)
    //   console.log(state.pageImages)
    // },
    // resetImages (state) {
    //   state.pageImages = []
    // },
    uploadFile (state, data) {
      state.uploadFile = data
    },
    editFeed (state, data) {
      state.editFeed = data
    }
  },
  actions: {
    removeFeed ({commit, state}, {feedNum}) {
      axios.post('/api/feed/remove', {
        accnt_num: state.user.accnt_num,
        feed_num: feedNum
      }).then(response => {
        commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    addComment ({commit, state}, {feedNum, comment}) {
      axios.post('/api/reply', {
        accnt_num: state.user.accnt_num,
        feed_num: feedNum,
        comment: comment
      }).then(response => {
      })
    },
    addRecomment ({state}, {feedNum, comment, parentNum}) {
      axios.post('/api/reply', {
        accnt_num: state.user.accnt_num,
        feed_num: feedNum,
        parent_num: parentNum,
        comment: comment
      }).then(response => {
      })
    },
    removeComment ({commit, state}, {replyNum}) {
      axios.post('/api/reply/remove', {
        reply_num: replyNum
      }).then(response => {
        console.log('hh')
      })
    },
    addHeart ({commit, state}, {feedNum}) {
      axios.post('/api/feed/like', {
        accnt_num: state.user.accnt_num,
        feed_num: feedNum
      }).then(response => {
        commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    cancelHeart ({commit, state}, {feedNum}) {
      axios.post('/api/feed/unlike', {
        accnt_num: state.user.accnt_num,
        feed_num: feedNum
      }).then(response => {
        commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    addFollow ({commit, state}, {accntNum}) {
      axios.post('/api/user/follow', {
        accnt_num: state.user.accnt_num,
        following_num: accntNum
      }).then(response => {
        commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    cancelFollow ({commit, state}, {accntNum}) {
      axios.post('/api/user/unfollow', {
        accnt_num: state.user.accnt_num,
        following_num: accntNum
      }).then(response => {
        commit('setUser')
      }).catch(e => {
        console.log('error: ' + e)
      })
    }
    // getFeedInfo ({commit, state}, {feedNum}) {
    //   axios.post('/api/feed/' + feedNum, {
    //   }).then(response => {
    //     this.feed = response.data
    //     this.getUserInfo()
    //   }).catch(e => {
    //     console.log('error: ' + e)
    //   })
    // },
    // addHeart ({commit, state}, feed_num) {
    //   axios.post('/api/feed/like', {
    //     accnt_num: this.$store.state.user.accnt_num,
    //     feed_num: this.feed_num
    //   }).then(response => {
    //     this.getFeedInfo()
    //     this.$store.commit('setUser')
    //     console.log(this.$store.state.user)
    //   }).catch(e => {
    //     console.log('error: ' + e)
    //   })
    // },
    // preload () {
    //   this.state.pageImages.every(function (item) {
    //     item.onload = function () {
    //       document.querySelector('.app-wrapper').classList.add('active')
    //       document.querySelector('#load').classList.remove('active')
    //       this.commit('resetImages')
    //       console.log('load')
    //     }
    //   })
    // }
  }
})
