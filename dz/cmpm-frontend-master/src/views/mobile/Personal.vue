<template>
  <div class="personal" >
    <van-cell-group>
      <van-cell>
        <template #title>
          <van-row>
            <van-col span="6">
              <van-image
                round
                width="5rem"
                height="5rem"
                src="assets/avatar.jpg"
            /></van-col>
            <van-col span=""
              ><span class="name">{{ name }}</span
              ><van-tag class="team-tag" type="warning">第{{ team }}队</van-tag></van-col
            >
          </van-row>
        </template>
      </van-cell>
      <van-cell title="修改密码" is-link @click="changePwd" />
       <van-cell title="当前关卡" :value="curLevel.title" />
      <van-cell title="个人排名">
        <template #right-icon>
          <van-tag  plain  type="warning">第{{ userRank }}名</van-tag>
        </template>
      </van-cell>
      <van-cell title="团队排名">
        <template #right-icon>
          <van-tag plain  type="warning">第{{ teamRank }}名</van-tag>
        </template>
      </van-cell>
    </van-cell-group>
    <div class="logout">
      <van-button type="info" block @click="onLogout">退出登录</van-button>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import { userRank } from '@/api/user'

export default {
  data() {
    return {
      userRank: "99+",
      teamRank: "99+",
    };
  },
  computed: {
    ...mapGetters(["name", "token","team","curLevel"]),
  },
  created(){
    this.initData()
  },
  methods: {
    initData(){
      const levelId = this.curLevel.id,userId = this.token
      userRank(levelId,userId).then( response => {
        const {userRank , teamRank } = response.data
        this.userRank = userRank == -1 ? '99+' : userRank
        this.teamRank = teamRank == -1 ? '99+' : teamRank
      })
    },
    changePwd() {
      this.$router.push({
        path: "/mobile/changePwd",
      });
    },
    onLogout() {
      this.$store
        .dispatch("user/logout")
        .then(() => {
          this.$router.push("/mobile");
        })
        .catch(() => {});
    },
  },
};
</script>
<style scoped>
.name {
  font-size: 2rem;
  line-height: 5rem;
}
.logout {
  margin: 4rem 1rem 0 1rem;
}
.personal{
  /* background-color: #fff; */
  height: 100%;
}
.team-tag {
  margin-left: 0.4rem;
}
</style>