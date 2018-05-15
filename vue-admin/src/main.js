// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import {appRouter} from './router/router';
import store from './store'
import Axios from './axios'
import VueAxios from 'vue-axios'
import i18n from '@/locale';
import iView from 'iview';
Vue.config.productionTip = false

Vue.use(VueAxios, Axios)
Vue.use(iView)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  i18n,
  components: { App },
  template: '<App/>',
  mounted () {

  },
  created () {
    let tagsList = [];
    appRouter.map((item) => {
      if (item.children.length <= 1) {
        tagsList.push(item.children[0]);
      } else {
        tagsList.push(...item.children);
      }
    });
    this.$store.commit('setTagsList', tagsList);
  }
})
