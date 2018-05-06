import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import lost from '@/components/lost/lost';
import found from '@/components/found/found';

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
      redirect: '/found/1/20',
      children: [
        {
          // 当 /user/:id/profile 匹配成功，
          // UserProfile 会被渲染在 User 的 <router-view> 中
          name:'lost',
          path: 'lost/:page/:rows',
          component: lost
        },{
          name:'found',
          path: 'found/:page/:rows',
          component: found
        }
      ]
    }
  ]
})
