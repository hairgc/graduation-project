import Vue from 'vue'
import Vuex from 'vuex'
import Cookies from 'js-cookie';
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user:Cookies.getJSON('user'),
    token:Cookies.get('token')
  },
  mutations: {
    login(state, payload) {
      state.user = payload.user
      state.token = payload.token
    },
    logout(state) {
      state.user = null
      state.token = null
    }
  },
  actions: {

  }
})
