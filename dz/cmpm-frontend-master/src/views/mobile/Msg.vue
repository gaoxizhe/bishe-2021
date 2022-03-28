<template>
  <div class="msg">
    <van-icon class="msg-icon" name="chat" />
    <p class="msg-text">{{msg}}</p>
  </div>
</template>
<script>
export default {
  data(){
    return {
      msg: ''
    }
  },
  created(){
     const { msg } = this.$route.params;
     this.msg = msg
  }

}
</script>
<style>
.msg {
  text-align: center;
  padding-top: 50%;
  font-size: 1.5rem;
}
.msg-icon {
  font-size: 3rem;
}
</style>