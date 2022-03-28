<template>
  <div class="main">
    <van-nav-bar
      :title="title"
      :left-text="back ? '返回' : ''"
      :left-arrow="back"
      @click-left="onClickLeft"
      @click-right="onClickRight"
    >
      <template #right>
        <van-icon class="icon" name="user-o" size="18" v-show="showPersonal" />
      </template>
    </van-nav-bar>
    <transition name="fade-transform" mode="out-in">
      <router-view :key="key" />
    </transition>
  </div>
</template>

<script>
export default {
  name: "MobileLayout",
  components: {},
  data() {
    return {
      title: "",
      back: false,
      showPersonal: false,
    };
  },
  created() {
    this.getTitle();
  },
  watch: {
    $route() {
      this.getTitle();
    },
  },
  computed: {
    key() {
      return this.$route.path;
    },
  },
  methods: {
    getTitle() {
      let matched = this.$route.matched.filter(
        (item) => item.meta && item.meta.title
      );
      this.title = matched[0].meta.title;
      this.showPersonal = this.title == "知识大闯关" ? true : false;
      this.back = matched[0].meta.back;
    },
    onClickLeft() {
      this.$router.go(-1);
    },
    onClickRight() {
      this.$router.push({
        path: "/mobile/personal",
      });
    },
  },
};
</script>

<style lang="scss">
.main {
  // background-color: #f2ead7;
  height: 100%;
}
.icon {
  color: #323233;
}
.van-nav-bar__text {
  color: #323233;
}
.van-nav-bar .van-icon {
  color: #323233;
}
</style>
