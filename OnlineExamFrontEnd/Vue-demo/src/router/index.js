import Vue from 'vue'
import Router from 'vue-router'
import Maps from './map'

Vue.use(Router)
// 实际路由设置
const router = new Router(Maps)
// 验证登录
router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // this route requires auth, check if logged in
    // TODO
    console.log('requires auth')
    // 向后端获取是否登录的信息来设置 loggedIn 这里先默认为 true
    let loggedIn = true
    // if not, redirect to login page.
    if (!loggedIn) {
      next({
        path: '/teacher/login'
        // query: { redirect: to.fullPath }
      })
    } else {
      next()
    }
  } else {
    next() // 确保一定要调用 next()
  }
})

export default router
