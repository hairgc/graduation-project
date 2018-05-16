import Main from '@/views/Main.vue';

// 不作为Main组件的子页面展示的页面单独写，如下
export const loginRouter = {
  path: '/login',
  name: 'login',
  meta: {
    title: 'Login - 登录'
  },
  component: resolve => { require(['@/views/login.vue'], resolve); }
};

export const page404 = {
  path: '/*',
  name: 'error-404',
  meta: {
    title: '404-页面不存在'
  },
  component: resolve => { require(['@/views/error-page/404.vue'], resolve); }
};

export const page403 = {
  path: '/403',
  meta: {
    title: '403-权限不足'
  },
  name: 'error-403',
  component: resolve => { require(['@//views/error-page/403.vue'], resolve); }
};

export const page500 = {
  path: '/500',
  meta: {
    title: '500-服务端错误'
  },
  name: 'error-500',
  component: resolve => { require(['@/views/error-page/500.vue'], resolve); }
};

// 作为Main组件的子页面展示但是不在左侧菜单显示的路由写在otherRouter里
export const otherRouter = {
  path: '/',
  name: 'otherRouter',
  component: Main,
  redirect: '/home',
  children: [
    { path: 'home', title: {i18n: 'home'}, name: 'home_index', component: ()=> import ('@/views/home/home.vue') }
  ]
};

// 作为Main组件的子页面展示并且在左侧菜单显示的路由写在appRouter里
export const appRouter = [
  {
    path: '/base',
    icon: 'ios-people',
    name: 'base',
    title: {i18n: 'base'},
    component: Main,
    children: [
      {
        path: 'user',
        icon: 'ios-person',
        name: 'user',
        title: {i18n: 'userManagement'},
        component: resolve => { require(['@/views/page/base/user-management.vue'], resolve); }
      },
      {
        path: 'admin',
        icon: 'ios-person-outline',
        name: 'admin',
        title:  {i18n: 'adminManagement'},
        component: resolve => { require(['@/views/page/base/admin-management.vue'], resolve); }
      }
    ]
  },
  {
    path: '/found',
    icon: 'ios-heart',
    component: Main,
    name:'found',
    redirect: '/found/management',
    children: [
      { path: 'management', title:{i18n: 'foundManagement'}, name: 'foundManagement', component: resolve => { require(['@/views/page/found-management/found-management.vue'], resolve); } }
    ]
  },
  {
    path: '/lost',
    icon: 'heart-broken',
    component: Main,
    name:'lost',
    redirect: '/lost/management',
    children: [
      { path: 'management', title:{i18n: 'lostManagement'}, name: 'lostManagement', component: resolve => { require(['@/views/page/lost-management/lost-management.vue'], resolve); } }
    ]
  },
  {
    path: '/letter',
    icon: 'ios-email',
    component: Main,
    name:'letter',
    redirect: '/letter/management',
    children: [
      { path: 'management', title:{i18n: 'letterManagement'}, name: 'letterManagement', component: resolve => { require(['@/views/page/thanks-letter/thanks-letter.vue'], resolve); } }
    ]
  }
];

// 所有上面定义的路由都要写在下面的routers里
export const routers = [
  loginRouter,
  otherRouter,
  ...appRouter,
  page500,
  page403,
  page404
];

