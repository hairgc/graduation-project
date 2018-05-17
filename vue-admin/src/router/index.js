import Vue from 'vue'
import Router from 'vue-router'
import iView from 'iview';
import Util from '../libs/util';
import Cookies from 'js-cookie';
import {routers} from './router'

Vue.use(Router)

const RouterConfig = {
  // mode: 'history',
  routes: routers
}
const router = new Router(RouterConfig)
router.beforeEach((to, from, next) => {
  iView.LoadingBar.start();
  if (!Cookies.get('admin') && to.name !== 'login') { // 判断是否已经登录且前往的页面不是登录页
    Util.title(to.meta.title);
    next({
      name: 'login'
    });
  }else if (Cookies.get('admin') && to.name === 'login') { // 判断是否已经登录且前往的是登录页
    Util.title('Login-登录');
    next({
      name: 'home_index'
    });
  }else{
    Util.title(to.meta.title);
    next();
  }
});

router.afterEach((to) => {
  iView.LoadingBar.finish();
  window.scrollTo(0, 0);
});
export default router
