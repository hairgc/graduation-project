// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './vuex'
import Axios from './axios'
import VueAxios from 'vue-axios'
import iView from 'iview'
import locale from 'iview/dist/locale/zh-CN'
import 'iview/dist/styles/iview.css'
Vue.config.productionTip = false
Vue.use(VueAxios, Axios)
Vue.use(iView, {locale})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
