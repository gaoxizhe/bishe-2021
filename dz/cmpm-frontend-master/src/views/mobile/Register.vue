<template>
  <div class="register-form">
    <van-notice-bar left-icon="volume-o" :text="noticeText" />
    <van-form @submit="onSubmit">
      <van-field
        v-model="form.username"
        name="用户名"
        label="用户名"
        placeholder="请输入用户名"
        :rules="[{ required: true, message: '不能为空' }]"
      />
      <van-field
        v-model="form.password"
        type="password"
        name="密码"
        label="密码"
        placeholder="请输入密码"
        :rules="[{ required: true, message: '不能为空' }]"
      />
      <van-field
        v-model="form.passagain"
        type="password"
        name="再次输入密码"
        label="再次输入密码"
        placeholder="请再次输入密码"
        :rules="[{ required: true, message: '不能为空' }]"
      />
      <van-field
        v-model="form.nick"
        name="姓名"
        label="姓名"
        placeholder="请输入您的姓名"
        :rules="[{ required: true, message: '不能为空' }]"
      />
      <van-field
        class="login-input"
        readonly
        clickable
        name="picker"
        :value="form.teamName"
        label="团队"
        placeholder="点击选择团队"
        @click="showPicker = true"
      />
      <van-popup v-model="showPicker" position="bottom">
        <van-picker
          show-toolbar
          :columns="teamColumns"
          @confirm="onPickerConfirm"
          @cancel="showPicker = false"
        />
      </van-popup>
      <div class="form-button">
        <van-button block type="info" native-type="submit">提交</van-button>
      </div>
    </van-form>
    <my-loading msg="提交中..." :show="loading" />
  </div>
</template>
<script>
import { getAllTeam, register, registerEnd } from "@/api/user";
import { Notify } from "vant";
import MyLoading from "@/components/MyLoading";
import { Dialog } from "vant";
import { dateFtt } from "@/utils/index";

export default {
  components: {
    MyLoading,
  },
  data() {
    return {
      form: {
        username: "",
        password: "",
        passagain: "",
        nick: "",
        teamName: "",
        team: "",
      },
      teamColumns: [],
      teams: [],
      showPicker: false,
      loading: false,
      registerEndTime: "",
      registerEndTimeStr: "",
    };
  },
  computed: {
    noticeText: function () {
      return "注册截止时间到：" + this.registerEndTimeStr;
    },
  },
  created() {
    this.getTeams();
    this.getRegsterTime();
  },
  methods: {
    getRegsterTime() {
      registerEnd().then((response) => {
        this.registerEndTime = response.data;
        this.registerEndTimeStr = dateFtt(
          new Date(this.registerEndTime),
          "yyyy-MM-dd"
        );
        this.checkRegisterTime()
      });
    },
    getTeams() {
      getAllTeam().then((response) => {
        const list = response.data;
        for (let i = 0; i < list.length; i++) {
          const team = list[i];
          const key = team.teamName;
          this.teamColumns.push(key);
          const data = {
            key: key,
            val: team,
          };
          this.teams.push(data);
        }
      });
    },
    onSubmit() {
      this.checkInput();
      const team = this.getTeamId(this.form.teamName);
      this.loading = true;
      this.form.team = team.val.id;
      register(this.form)
        .then((response) => {
          this.loading = false;
          Dialog.alert({
            message: "恭喜您注册成功！",
          }).then(() => {
            this.$router.push("/mobile/login");
          });
        })
        .catch((err) => {
          this.loading = false;
        });
    },
    checkRegisterTime() {
      //是否是可注册的时间段
      const now = Date.parse(new Date());
      //服务器拿到是0点的值，如2021-05-20 00：00：00，实际应该是2021-05-21 00：00：00
      const endTime = this.registerEndTime + 1000*60*60*24
      if (now > endTime) {
        this.$router.push({
          name: "Msg",
          params: {
            msg:'此时间段不允许注册'
          },
        });
      }
    },
    checkInput() {
      if (this.form.password != this.form.passagain) {
        Notify("两次密码输入不一致");
        return;
      }

      if (this.form.teamName === "") {
        Notify("请选择团队");
        return;
      }
    },
    onPickerConfirm(key) {
      this.form.teamName = key;
      this.showPicker = false;
    },
    getTeamId(key) {
      for (let i = 0; i < this.teams.length; i++) {
        const team = this.teams[i];
        if (team.key === key) {
          return team;
        }
      }
    },
  },
};
</script>
<style scoped>
.form-button {
  margin: 4rem 1rem 0 1rem;
}
</style>