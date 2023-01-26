<template>
    <h1>login</h1>
    <div v-if="state.user">
        안녕하세요 {{ state.user }}님
        <button v-on:click="logout">로그아웃</button>
    </div>
    <div v-else>
        <input type="text" v-model="user_id">
        <input type="text" v-model="password">
        <button v-on:click="login">로그인</button>
    </div>
    <!--<h2>{{JSON.stringify(state.user)}}</h2>-->

</template>

<script>
import {useStore} from "vuex";
import {computed, reactive} from "vue";
//import { useCookies } from "vue3-cookies";
//const { cookies } = useCookies();

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
       // console.log("cookies", cookies.get("user"))

        //cookie값을 commmit 해준다
        //store.commit("login/setUserLogin", cookies.get("user"))
        console.log(localStorage.getItem('user'))
        // if(localStorage.getItem('user') == null){
        store.commit("login/setUserLogin", JSON.parse(localStorage.getItem('user')));
        // }
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
            //this.store.dispatch('login/logout');
            this.store.commit("login/setLogout")
            //cookies.set("user", null)
        }
    },
    watch:{
        
    }
}
</script>

<style>

</style>