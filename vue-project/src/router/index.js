import Vue from 'vue'
import Router from 'vue-router'
import Cookies from 'js-cookie';

import {routers} from './router';

Vue.use(Router)

// 路由配置
const RouterConfig = {
  // mode: 'history',
  routes: routers
};
const router =  new Router(RouterConfig);
router.beforeEach((to, from, next) => {
  if (Cookies.get('user') && (to.name === 'login'||to.name === 'register')) { // 判断是否已经登录且前往的是登录页
    next({
      name: 'Index'
    });
  }else if(!Cookies.get('user')){
    if(to.name === 'message'||to.name === 'space'||to.name==='found-add'||to.name==='lost-add'||to.name==='text-editor'){
      next({
        name: 'login'
      });
    }else{
      next()
    }
  }else{
    next()
  }
})
export default router;


