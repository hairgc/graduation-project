export const appRouter = [
  {
    path: '/',
    name: 'HelloWorld',
    component: () => import('@/components/HelloWorld')
  }
]
export const routers = [
  ...appRouter
]
