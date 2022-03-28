<template>
  <div class="level-container">
    <div class="level-header-img">
      <img
        src="@/assets/main-background.jpg"
        alt=""
        srcset=""
        width="100%"
        height="auto"
      />
    </div>
    <div class="rules" @click="onShowRule">规则说明</div>
    <div class="time-desc">活动时间：2021-06-01 至 2021-06-30</div>
    <div class="level">
      <div
        class="level-card"
        @click="onChallenge(item)"
        v-for="item in levelData"
        v-bind:key="item.id"
        v-bind:class="{
          gray:
            item.status == 0 || item.status == 2 || item.userPermission == 2,
        }"
      >
        <div class="level-card-tag">
          {{ item.tag }}
        </div>
        <div class="level-card-title">{{ item.title }}</div>
        <img
          class="level-card-img"
          :src="item.cover"
          width="90%"
          height="100"
        />
        <div class="level-card-score">
          <van-rate
            size="30"
            color="#ffd21e"
            allow-half
            v-model="item.score"
            readonly
          />
        </div>
      </div>
    </div>
    <van-action-sheet v-model="showRule" title="规则说明">
      <div class="rule-content">
        <div class="rule-title">答题规则:</div>
        <ul class="rule-details">
          <li>
            1.每关25道题，每题4分，不定项选择题，限时6分钟，超时自动提交。
          </li>
          <li>2.单向答题，跳转至下一题后不能返回上一题。</li>
          <li>3.每关淘汰倒数三名的团队,被淘汰后，团队成员失去答题资格。</li>
        </ul>
        <van-divider />
        <div class="rule-title">闯关时间</div>
        <ul class="rule-details">
          <li v-for="item in levelData" v-bind:key="item.id">
            <span class="rule-level-title">{{ item.title }}</span>
            <div>
              <span class="rule-level-start">{{ item.startTime }}</span>
              至
              <span class="rule-level-end">{{ item.endTime }}</span>
            </div>
          </li>
        </ul>
      </div>
    </van-action-sheet>
  </div>
</template>

<script>
import { getAll } from "@/api/level";
import { mapGetters } from "vuex";

import { Notify } from "vant";

export default {
  data() {
    return {
      levelData: [],
      showRule: false,
    };
  },
  computed: {
    ...mapGetters(["token"]),
  },
  created() {
    this.initData();
  },
  methods: {
    initData() {
      getAll().then((response) => {
        let { data } = response;
        for (let i = 0; i < data.length; i++) {
          const level = data[i];
          level.score = level.score / 20;
          const tag = "可挑战";

          if (level.status == 2) {
            tag = "已结束";
          }

          if (level.userPermission == 1) {
            tag = "已挑战";
          } else if (level.userPermission == 2) {
            tag = "已淘汰";
          }

          if (level.status == 0) {
            tag = "未开放";
          }

          level.tag = tag;
          this.levelData.push(level);
        }
      });
    },
    onShowRule() {
      this.showRule = true;
    },
    onChallenge(item) {
      if (item.status == 0) {
        Notify("该关卡还未开放");
        return;
      }

      if (item.status == 2) {
        Notify("该关卡已结束");
        return;
      }

      if (item.userPermission == 1) {
        Notify("您已完成挑战");
        return;
      }

      if (item.userPermission == 2) {
        Notify("您已被淘汰");
        return;
      }

      this.$router.push({
        name: "Challenge",
        params: {
          level: item,
          token: this.token,
        },
      });
    },
  },
};
</script>

<style scoped>
.level-container {
  margin-top: -1rem;
}
.level {
  margin-top: -1rem;
  padding: 1rem 1rem 1rem 1rem;
  background-color: #fffdfd;
}
.level-card {
  text-align: center;
  position: relative;
  overflow: hidden;
  background-color: white;
  margin-top: 1rem;
  border-radius: 0.3rem;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

.level-card-tag {
  color: #fff;
  height: 1.5rem;
  width: 5.5rem;
  position: absolute;
  right: -2.5rem;
  top: -1rem;
  padding: 0.2rem;
  background-color: #ff5933;
  text-align: center;
  -moz-transform: rotate(45deg);
  -webkit-transform: rotate(45deg);
  -o-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  -webkit-transform-origin: left top;
  transform-origin: left top;
  transform: rotate(45deg);
}

.level-card-title {
  margin: 0.5rem;
  margin-top: 1rem;
  font-size: 1rem;
}

.level-card-score {
  margin-top: 1rem;
  margin-bottom: 1rem;
}

.gray {
  -webkit-filter: grayscale(100%);
  -moz-filter: grayscale(100%);
  -ms-filter: grayscale(100%);
  -o-filter: grayscale(100%);
  filter: grayscale(100%);
  filter: gray;
}
.rules {
  position: absolute;
  right: 0;
  top: 3rem;
  color: #fff;
  margin: 1rem;
  
}

.time-desc {
  position: absolute;
  text-align: center;
  top: 7rem;
  font-size: 0.9rem;
  color: #fff;
  margin: 1rem;
  background-color: #9697994f;
  padding: 0.3rem;
}

.rule-content {
  padding: 1rem 1rem 60% 1rem;
  font-size: 0.9rem;
}

.rule-title {
  font-weight: bold;
  margin-bottom: 1rem;
}

.rule-details li {
  margin-top: 0.2rem;
}
</style>