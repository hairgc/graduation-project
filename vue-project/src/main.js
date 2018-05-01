import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './registerServiceWorker'
import iView from 'iview';
import locale from 'iview/dist/locale/zh-CN';
import 'iview/dist/styles/iview.css';
Vue.config.productionTip = false

Vue.use(iView,{ locale });

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
