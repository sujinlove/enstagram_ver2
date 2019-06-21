// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import 'es6-promise/auto'
import axios from 'axios'
import VueAxios from 'vue-axios'
import store from './store'
import App from './App'
import router from './router'

Vue.use(VueAxios, axios)
Vue.config.productionTip = false
Vue.prototype.$EventBus = new Vue()

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  store,
  router,
  components: { App },
  template: '<App/>'
})
