import Cookies from 'js-cookie';

const user = {
  state: {
    user:Cookies.getJSON('admin'),
    token:Cookies.get('admin_token')
  },
  mutations: {
    login(state, payload) {
      state.user = payload.user
      state.token = payload.token
    },
    logout(state, vm) {
      state.user = null
      state.token = null
      Cookies.remove('admin');
      Cookies.remove('admin_token');
      localStorage.clear();
    }
  }
};

export default user;
