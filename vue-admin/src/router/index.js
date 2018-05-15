import Vue from 'vue'
import Router from 'vue-router'
import iView from 'iview';
import Util from '../libs/util';
import {routers} from './router'

Vue.use(Router)

const RouterConfig = {
  // mode: 'history',
  routes: routers
}
const router = new Router(RouterConfig)
router.beforeEach((to, from, next) => {
  iView.LoadingBar.start();
  Util.title(to.meta.title);
  next();
});

router.afterEach((to) => {
  iView.LoadingBar.finish();
  window.scrollTo(0, 0);
});
export default router
