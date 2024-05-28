<template>
  <a-layout-header class="header">
    <div class="logo">
      <router-link to="/welcome">
        12306
      </router-link>
    </div>
    <div class="user-info">
      欢迎： {{member.mobile}}
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
      <a-menu-item key="1">nav 11</a-menu-item>
      <a-menu-item key="2">nav 2</a-menu-item>
      <a-menu-item key="3">nav 3</a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script>
import { defineComponent, ref } from 'vue'
import store from "@/store";

export default defineComponent({
  name: "the-header-view",
  setup() {
    let member = store.state.member
    const selectedKeys = ref([])

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