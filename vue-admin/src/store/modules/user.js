import Cookies from 'js-cookie';

const user = {
  state: {
    user:Cookies.getJSON('user'),
    token:Cookies.get('token')
  },
  mutations: {
    login(state, payload) {
      state.user = payload.user
      state.token = payload.token
    },
    logout(state, vm) {
      state.user = null
      state.token = null
      Cookies.remove('user');
      Cookies.remove('token');
      localStorage.clear();
    }
  }
};

export default user;
