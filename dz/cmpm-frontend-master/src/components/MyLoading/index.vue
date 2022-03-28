<template>
  <div class="dialog">
    <van-dialog v-model="show" :showConfirmButton="false">
      <div class="msg">
        <img src="@/assets/loading.gif" alt="" srcset="" width="200" />
        <p class="text">{{msg}}</p>
      </div>
    </van-dialog>
  </div>
</template>
<script>
export default {
    name:'MyLoading',
    props:['msg','show'],
    data(){
        return {
            loading:true
        }
    }
}
</script>
<style scoped>
.dialog {
    text-align: center;
}
.text {
    font-size: 1rem;
    margin-top: -3rem;
}
</style>