<template>
  <div>
    <div class="background">
      <img src="@/assets/login-background.jpg" width="100%" height="100%" alt="" />
    </div>
    <div class="login">
      <!-- <div class="logo">
        <img src="@/assets/logo.jpg" width="80" height="80" />
      </div> -->
      <h2 class="title">百科知识竞赛</h2>
      <van-form @submit="onSubmit">
        <van-field
          v-model="loginForm.username"
          name="用户名"
          label="用户名"
          placeholder="请输入用户名"
          clearable
          :rules="[{ required: true, message: '不能为空' }]"
        />
        <van-field
          v-model="loginForm.password"
          type="password"
          name="密码"
          label="密码"
          placeholder="请输入密码"
          clearable
          :rules="[{ required: true, message: '不能为空' }]"
        />
        <div class="login-button">
          <van-button block plain type="default" native-type="submit"
            >登录</van-button
          >
        </div>
        <van-divider>Or</van-divider>
         <div class="register-button">
          <van-button block plain type="default" @click="onRegister"
            >注册</van-button
          >
        </div>
      </van-form>
    </div>
     <my-loading msg="登录中..." :show="loading"/>
  </div>
</template>

<script>
import MyLoading from "@/components/MyLoading"

export default {
   components:{
    MyLoading
  },
  data() {
    return {
      loginForm: {
        username: "",
        password: "",
      },
      loading: false
    };
  },
  methods: {
    onRegister(){
      this.$router.push("/mobile/register")
    },
    onSubmit(values) {
      this.loading = true
      const form = {};
      this.$store
        .dispatch("user/login", this.loginForm)
        .then(() => {
          this.loading = false
          this.$router.push("/mobile");
        })
        .catch(() => {
           this.loading = false
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.background {
  width: 100%;
  height: 100%; /**宽高100%是为了图片铺满屏幕 */
  z-index: -1;
  position: absolute;
}
.login {
  width: 100%;
  height: 100%;
  margin-top: 20%;
  padding: 0 2rem 0 2rem;
  z-index: 1;
  position: absolute;
}

.logo {
  text-align: center;
  margin: 1rem;
}
.title {
  text-align: center;
  color: white;
}
.login-button {
  padding-top: 1.5rem;
}
</style>