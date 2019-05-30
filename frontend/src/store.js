import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      heartList: [],
      feedList: []
    },
    popupContent: '',
    selectFeed: '',
    // pageImages: [],
    uploadFile: ''
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
    }
  },
  actions: {
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
