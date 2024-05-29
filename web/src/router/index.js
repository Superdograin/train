import { createRouter, createWebHistory } from 'vue-router'
import { notification } from "ant-design-vue";
import store from "@/store";

const routes = [
  {
    path: '/login',
    component: () => import('../views/login.vue')
  },
  {
    path: '/',
    component: () => import('../views/main.vue'),
    meta: {
      loginRequired : true
    },
    children: [
      {
        path: 'welcome',
        component: () => import('../views/main/welcome.vue')
      },
      {
        path: 'passenger',
        component: () => import('../views/main/passenger.vue')
      }
    ]
  },
  {
    path: '',
    redirect: '/welcome'
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 路由登录拦截
router.beforeEach((to, from, next) => {
  if (to.matched.some(function (item) {
    console.log(item, "是否需要登录校验: ", item.meta.loginRequired || false)
    return item.meta.loginRequired
  })) {
    const member = store.state.member
    console.log("页面登录校验开始： ", member)
    if (!member.token) {
      console.log("用户未登录或登录超时！")
      notification.error({ description: "未登录或登录超时" })
      next('login')
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router
