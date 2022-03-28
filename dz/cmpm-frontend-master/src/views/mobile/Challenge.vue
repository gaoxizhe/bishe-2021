<template>
  <div class="panel">
    <div class="question">
    <div class="question-title">{{ level.title }}</div>
    <div class="clock" v-show="loading != true">
      <van-icon name="clock-o" class="row-item" />
      <van-count-down
        :time="retainTime"
        class="row-item"
        @finish="coutDownFinish"
      >
       
      </van-count-down>
      <span class="question-catalog"
        >{{ question.num }}/{{ questionList.length }}</span
      >
    </div>
    <div class="question-card">
      <div class="content">{{ question.content }}</div>
      <div class="answers" v-if="question.type == 1">
        <van-radio-group v-model="question.userAnswer">
          <van-radio
            class="answer"
            checked-color="#ee0a24"
            v-for="q in question.options"
            v-bind:key="q.key"
            :name="q.key"
            >{{ q.value }}</van-radio
          >
        </van-radio-group>
      </div>
      <div class="answers" v-else-if="question.type == 2">
        <van-checkbox-group
          v-model="question.userAnswer"
          :max="question.options.length"
        >
          <van-checkbox
            class="answer"
            checked-color="#ee0a24"
            v-for="q in question.options"
            v-bind:key="q.key"
            :name="q.key"
            >{{ q.value }}</van-checkbox
          >
        </van-checkbox-group>
      </div>
      <div class="submit" v-if="questionIndex == questionList.length - 1">
        <van-button type="info" block @click="onSubmit(0)">提交</van-button>
      </div>
      <div class="submit" v-else-if="questionIndex < questionList.length - 1">
        <van-button type="info" block @click="onNext">下一题</van-button>
      </div>
    </div>

    <van-dialog v-model="showResult" :before-close="onBeforeClose">
      <div class="result">
        <div class="result-header">
          <img src="@/assets/result-logo.png" width="100" height="100" />
        </div>
        <div class="result-desc">
          <div class="result-desc-item">
            <van-row justify="center" gutter="6">
              <van-col class="result-label" span="12"
                >正确率:{{ (result.correctRate * 100).toFixed(2) }}%</van-col
              >
              <van-col span="12" class="result-label"
                >用时：{{ result.totalUsedTime }}</van-col
              >
            </van-row>
          </div>
          <div class="result-desc-item">
            <van-row justify="center" gutter="6">
              <van-col class="result-label" span="12"
                >得分:{{ result.score }}</van-col
              >
              <van-col span="12" class="result-label"
                >当前排名:{{ result.rank }}</van-col
              >
            </van-row>
          </div>
        </div>
        <div class="score">
          <van-rate
            size="30"
            v-model="result.scoreStar"
            allow-half
            readonly
            color="#ffd21e"
          />
        </div>
      </div>
    </van-dialog>

    <my-loading msg="加载中..." :show="loading"/>
    <my-loading msg="提交中..." :show="submiting"/>

  </div>
  </div>
</template>
<script>
import { drawQuestions } from "@/api/question";
import { challengeStart, submit, cacheSubmit } from "@/api/record";
import { mapGetters } from "vuex";
import { formatSeconds } from "@/utils/index";
import { Notify } from "vant";

import MyLoading from "@/components/MyLoading"
export default {
  components:{
    MyLoading
  },
  data() {
    return {
      level: null,
      loading: true,
      submiting: false,
      question: {
        id: "",
        num: 0,
        content: "",
        type: "",
        options: [],
        userAnswer: [],
      },
      userAnswerList: [],
      questionIndex: 0,
      questionList: [],
      showResult: false,
      retainTime: 60 * 60 * 1000,
      result: {
        correctRate: "",
        score: 0,
        rank: "",
        totalUsedTime: "",
      },
    };
  },
  computed: {
    ...mapGetters(["token"]),
  },
  created() {
    this.initChallenge();
  },
  watch: {
    questionIndex: function () {
      this.question = this.questionList[this.questionIndex];
    },
    retainTime(val) {
      if (val <= 0) {
        //时间到，强制提交
        this.coutDownFinish();
      }
    },
  },
  methods: {
    coutDownFinish() {
      Notify("时间已到，将自动提交");
      let self = this;
      window.setTimeout(function () {
        self.onSubmit(3);
      }, 2000);
    },
    initChallenge() {
      const { level } = this.$route.params;
      this.level = level;
      this.loading = true;
      drawQuestions(this.token, level.id).then((response) => {
        const questionData = response.data;
        challengeStart(this.token, level.id).then((response) => {
          this.initQuestions(questionData);
          const { data } = response;
          this.retainTime = data.retainTime * 1000;
          this.questionIndex = data.curQuestion - 1;
          this.questionList[this.questionIndex];
          if (data.details) {
            this.userAnswerList = JSON.parse(data.details);
          }
          this.loading = false;
        });
      });
    },
    initQuestions(data) {
      this.questionList = data;
      let list = new Array();
      for (let i = 0; i < this.questionList.length; i++) {
        const question = this.questionList[i];
        question.options = JSON.parse(question.options);
        //TODO 这次比赛都设成多选
        question.type = 2;
        question.num = i + 1;
        list.push(question);
      }
      this.questionList = list;
      this.questionIndex = 0;
      this.question = this.questionList[this.questionIndex];
    },
    onNext() {
      const data = this.setUserAnswer(1);
      cacheSubmit(data).then((response) => {});
      this.questionIndex++;
    },
    setUserAnswer(status) {
      let q = this.question;
      if (q.userAnswer && q.userAnswer != "") {
        for (let i = 0; i < q.userAnswer.length; i++) {
          const ans = q.userAnswer[i];
          q.answer += ans;
        }
        //按字母排序
        q.answer = Array.from(q.answer).sort().join("");
      } else {
        q.answer = "";
      }
      this.userAnswerList.push(q);
      const data = {
        userId: this.token,
        levelId: this.level.id,
        curQuestion: this.questionIndex + 1,
        answers: this.userAnswerList,
        status: status,
      };

      return data;
    },
    onSubmit(status) {
      const data = this.setUserAnswer(status);
      this.submiting = true
      //TODO 需要防抖动，不然最后会追加重复答案
      submit(data).then((response) => {
        let result = response.data;
        this.result.correctRate = result.correctRate;
        this.result.rank = result.rank;
        this.result.totalUsedTime = formatSeconds(response.data.totalUsedTime);
        //满分100，每颗星 20分
        this.result.score = result.score
        this.result.scoreStar = result.score / 20;

        this.submiting = false
        this.showResult = true;
      });
    },
    onBeforeClose() {
      this.$router.go(-1);
    },
  },
};
</script>
<style scoped>
.clock {
  margin-top: 0.5rem;
}
.question-catalog {
  float: right;
  margin-right: 0.3rem;
}

.row-item {
  float: left;
  margin-left: 0.3rem;
}
.question {
  /* margin: 1rem; */
  height: 100%;
  padding: 1rem 1rem 0 1rem;
}
.question-title {
  text-align: center;
  font-size: 2rem;
}
.question-card {
  background-color: white;
  margin-top: 2rem;
  padding: 1rem;
}
.content {
  line-height: 1.5rem;
}
.answer {
  margin-top: 0.5rem;
}
.submit {
  margin-top: 1rem;
  margin-bottom: 1rem;
}
.result {
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
  text-align: center;
}
.result-desc-item {
  padding: 0 2rem 0 2rem;
}
.result-label {
  color: #606266;
  text-align: left;
  /* margin-right: 1rem; */
}
.result-text {
  text-align: left;
}

.score {
  margin-top: 1rem;
}

.loading-overlay {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}
.panel{
   background-color: #f7f8fa;
   height: 100%;
}

</style>