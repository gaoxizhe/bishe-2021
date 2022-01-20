<template>
    <div class="top-header">
        <img class="logo" src="@/assets/images/official/TDUCK@2x.png">
        <div>
            <el-button v-if="isLogin" @click="$router.push({path:'/home'})">控 制 台</el-button>
            <el-button v-if="!isLogin" @click="$router.push({path:'/login'})">
                登 录
            </el-button>
        </div>
    </div>
</template>

<script>
import store from '@/store'

export default {
    name: 'Header',
    computed: {
        getStore() {
            return store
        },
        isLogin() {
            return this.getStore.getters['user/isLogin']
        }
    }
}
</script>

<style lang="scss" scoped>
.top-header {
    width: 100%;
    height: 72px;
    line-height: 72px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-content: center;
    align-items: center;
    background: linear-gradient(-67deg, rgb(32, 84, 240), rgba(51, 201, 253, 1), rgba(35, 86, 244, 1));
    * {
        z-index: 20;
    }
    .logo {
        width: 182px;
        height: 28px;
    }
    .menu-item {
        font-size: 17px;
        font-weight: 300;
        cursor: pointer;
        color: #fff;
        margin-right: 73px;
        line-height: 72px;
    }
    .add-contact-me {
        position: relative;
        div {
            margin-top: 20px;
            position: absolute;
            right: 0%;
            top: 100%;
            visibility: hidden;
            opacity: 0;
            transform: translateY(100px);
            transition: 0.3s;
        }
        img {
            width: 100px;
            height: 100px;
        }
    }
    .add-contact-me:hover {
        div {
            visibility: visible;
            opacity: 1;
            transform: translateY(0);
        }
    }
    .login-btn {
        width: 95px;
        height: 37px;
        background: #fff;
        border-radius: 11px;
    }
}

</style>

