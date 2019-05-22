import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {},
    popupContent: ''
  },
  mutations: {
    setUser (state) {
      axios.get('/api/user').then((response) => {
        state.user = response.data[0]
      }).catch(e => {
        console.log('error: ' + e)
      })
    },
    setPopupContent (state, data) {
      state.popupContent = data
    }
  },
  actions: {}
})
