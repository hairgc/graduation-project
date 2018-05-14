import Index from '@/components/Index'
import lost from '@/components/lost/lost';
import found from '@/components/found/found';
import login from '@/components/login'
import register from '@/components/register'
import foundDetail from '@/components/found/found_detail';
import lostDetail from '@/components/lost/lost_detail';

export const loginRouter = {
  path: '/login',
  name: 'login',
  meta: {
    title: 'Login - 登录'
  },
  component: login
};
export const registerRouter = {
  path: '/register',
  name: 'register',
  meta: {
    title: 'Register - 注册'
  },
  component: register
};
export const page404 = {
  path: '/*',
  name: 'error-404',
  meta: {
    title: '404-页面不存在'
  },
  component: () => import('@/components/error-page/404.vue')
};

export const page403 = {
  path: '/403',
  meta: {
    title: '403-权限不足'
  },
  name: 'error-403',
  component: () => import('@/components/error-page/403.vue')
};

export const page500 = {
  path: '/500',
  meta: {
    title: '500-服务端错误'
  },
  name: 'error-500',
  component: () => import('@/components/error-page/500.vue')
};
export const textEditor = {
  path: '/text-editor',
  meta: {
    title: '500-服务端错误'
  },
  name: 'text-editor',
  component: () => import('@/components/thanks-letter/text-editor.vue')
}

export const appRouter = [
  {
    path: '/',
    name: 'Index',
    component: Index,
    redirect: '/found/1/20',
    children: [
      {
        // 当 /user/:id/profile 匹配成功，
        // UserProfile 会被渲染在 User 的 <router-view> 中
        name: 'lost',
        path: 'lost/:page/:rows',
        component: lost
      },{
        path: '/lost/:id',
        name: 'lostDetail',
        component: lostDetail
      }, {
        name: 'found',
        path: 'found/:page/:rows',
        component: found
      }, {
        path: '/found/:id',
        name: 'foundDetail',
        component: foundDetail
      },
      {path: 'message', title: '消息中心', name: 'message', component: () => import('@/components/message/message.vue')},
      {path: 'space', title: '个人中心', name: 'space', component: () => import('@/components/my-space/my-space.vue')},
      {
        path: 'letter/:id',
        title: '感谢信',
        name: 'letter',
        component: () => import('@/components/thanks-letter/letter-detail.vue')
      },
      {
        path: '/post/found',
        name: 'found-add',
        component: () => import('@/components/found/found-add.vue')
      },
      {
        path: '/post/lost',
        name: 'lost-add',
        component: () => import('@/components/lost/lost-add.vue')
      }
    ]
  },
]

// 所有上面定义的路由都要写在下面的routers里
export const routers = [
  loginRouter,
  registerRouter,
  textEditor,
  ...appRouter,
  page500,
  page403,
  page404
];
