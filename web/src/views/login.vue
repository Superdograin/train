<template>
  <a-row class="login">
    <a-col class="login-main" :span="8" :offset="8">
      <h1><rocket-two-tone />12306售票系统</h1>
      <a-form
          :model="loginForm"
          name="basic"
          autocomplete="off"
      >
        <a-form-item
            label=""
            name="mobile"
            :rules="[{ required: true, message: '请输入手机号' }]"
        >
          <a-input v-model:value="loginForm.mobile" placeholder="手机号" />
        </a-form-item>

        <a-form-item
            label=""
            name="code"
            :rules="[{ required: true, message: '请输入验证码' }]"
        >
          <a-input v-model:value="loginForm.code">
            <template #addonAfter>
              <a @click="sendCode">获取验证码</a>
            </template>
          </a-input>
        </a-form-item>

        <a-form-item>
          <a-button type="primary" block @click="login">登录</a-button>
        </a-form-item>
      </a-form>
    </a-col>
  </a-row>
</template>

<script>
import { defineComponent, reactive } from 'vue'
import axios from "axios";
import { notification } from "ant-design-vue";
import { useRouter } from "vue-router"
import store from "@/store";

export default defineComponent({
  name: "login-view",
  setup() {
    const router = useRouter()

    const loginForm = reactive({
      mobile: '13012345678',
      code: '',
    })

    const sendCode = () => {
      axios.post("/member/member/send-code", {
        mobile: loginForm.mobile
      }).then((response) => {
        let data = response.data;
        if (data.success) {
          notification.success({ description: '发送验证码成功！' })
          loginForm.code = "8888"
        } else {
          notification.error({ description: data.message })
        }
      })
    }

    const login = () => {
      axios.post("/member/member/login", loginForm).then((response) => {
        let data = response.data;
        if (data.success) {
          notification.success({ description: '登录成功！' })
          router.push("/welcome")
          store.commit("setMember", data.content)
        } else {
          notification.error({ description: data.message })
        }
      })
    }

    return {
      loginForm,
      sendCode,
      login,
    }
  },
})
</script>

<style scoped lang="less">
.login-main {
  margin-top: 100px;
  padding: 30px 30px 20px;
  border: 2px solid grey;
  border-radius: 10px;
  background-color: #fcfcfc;
  h1 {
    font-size: 25px;
    font-weight: bold;
    text-align: center;
  }
}
</style>