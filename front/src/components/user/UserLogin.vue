<template>
    <h1>login</h1>
    <div v-if="state.user.user_nm">
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

export default {
    data() {
        return{
            user_id:"",
            password:"",
        }
    },
    setup(){
        const state = reactive({
            user:{
                user_nm:""
            }
        })
        const store = useStore();
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
            this.state.user.user_nm = "";
        }
    }
}
</script>

<style>

</style>