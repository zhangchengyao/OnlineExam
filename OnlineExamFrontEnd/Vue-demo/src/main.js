// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import App from './App'
import router from './router'
import store from './store'
import axios from 'axios'
import '../theme/index.css'

Vue.config.productionTip = false
axios.defaults.baseURL = '/api'
Vue.use(ElementUI)

/* eslint-disable no-new */
const app = new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})

export { app, router, store }
