<template>
  <a-layout-header class="header">
    <div class="logo">
      <router-link to="/welcome">
        12306
      </router-link>
    </div>
    <div class="user-info">
      欢迎： {{member.mobile}} &nbsp;
      <router-link to="/login">
        退出登录
      </router-link>
    </div>
    <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/welcome">
        <router-link to="/welcome">
          <coffee-outlined /> &nbsp; 欢迎
        </router-link>
      </a-menu-item>
      <a-menu-item key="/passenger">
        <router-link to="/passenger">
          <user-outlined /> &nbsp; 乘车人管理
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script>
import {defineComponent, ref, watch} from 'vue'
import store from "@/store";
import router from "@/router";

export default defineComponent({
  name: "the-header-view",
  setup() {
    let member = store.state.member
    const selectedKeys = ref([])

    watch(() => router.currentRoute.value.path, (newValue) => {
      console.log('watch', newValue)
      selectedKeys.value = []
      selectedKeys.value.push(newValue)
    }, {immediate: true})

    return {
      member,
      selectedKeys
    }
  },
})
</script>

<style scoped>
.header {
  .logo {
    float: left;
    width: 150px;
    height: 30px;
    color: white;
    font-size: 20px;
  }
  .user-info {
    float: right;
    color: white;
  }
}

</style>