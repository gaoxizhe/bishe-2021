<template>
  <div class="panel">
    <van-form @submit="onSubmit">
      <van-field
        v-model="state.passwordOld"
        name="原密码"
        label="原密码"
        type="password"
        placeholder="请输入原密码"
        clearable
        :rules="[{ required: true, message: '请输入原密码' }]"
      />
      <van-field
        v-model="state.password"
        type="password"
        name="新密码"
        label="新密码"
        placeholder="请输入新密码"
        clearable
        :rules="[{ required: true, message: '请输入新密码' }]"
      />
      <van-field
        v-model="state.passwordAgain"
        type="password"
        name="新密码确认"
        label="新密码确认"
        placeholder="请再次输入新密码"
        clearable
        :rules="[{ required: true, message: '请再次输入新密码' }]"
      />
      <div class="submit">
        <van-button block type="info" native-type="submit"> 提交 </van-button>
      </div>
    </van-form>
  </div>
</template>
<script>
import { changePassword } from "@/api/user";
import { mapGetters } from "vuex";

import { Notify } from 'vant';

export default {
  computed: {
    ...mapGetters(["username"]),
  },
  data() {
    return {
      state: {
        passwordOld: "",
        password: "",
        passwordAgain: "",
      },
    };
  },
  methods: {
    onSubmit() {
      if (this.state.password != this.state.passwordAgain) {
        Notify("两次密码输入不一致")
        return;
      }

      let data = {
        username: this.username,
      };
      Object.assign(data, this.state);

      changePassword(data).then((response) => {
        this.$store
        .dispatch("user/logout")
        .then(() => {
          this.$router.push("/mobile");
        })
        .catch(() => {});
      });
    },
  },
};
</script>
<style scoped>
.submit {
  margin: 3rem 1rem 0 1rem;
}
.panel {
  /* background-color: #f5f6f7; */
  height: 100%;
}
</style>