<template>
    <h1>login</h1>
    <div v-if="state.user.row_id">
        안녕하세요 {{ state.user.user_nm }}님
        <button v-on:click="logout">로그아웃</button>
    </div>
    <div v-else>
        <input type="text" v-model="user_id">
        <input type="text" v-model="password">
        <button v-on:click="login">로그인</button>
    </div>
    <h2>{{state.user}}</h2>
</template>

<script>
import {useStore} from "vuex";
import {computed, reactive} from "vue";
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();

export default {
    data() {
        return{
            user_id:"",
            password:"",
        }
    },
    setup(){
        const state = reactive({
            user: null
        })
        const store = useStore();
        console.log("cookies", cookies.get("user"))

        //cookie값을 commmit 해준다
        store.commit("login/setUserLogin", cookies.get("user"))
        state.user = computed(() => store.getters['login/getUserLogin']);
        return {store, state}
    },
    methods:{
        login(){
            const loginObj={
                user_id : this.user_id,
                password : this.password
            }
            this.store.dispatch('login/userLogin', loginObj);
        },
        logout(){
            //함수 내에서 router이동 
            this.$router.push("/")
            this.state.user.row_id = null;
            cookies.set("user", null)
        }
    }
}
</script>

<style>

</style>