<template>
    <h1>login</h1>
    <input type="text" v-model="user_id">
    <input type="text" v-model="password">
    <button v-on:click="login">로그인</button>
    <h2>{{user.user_nm}}</h2>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

export default {

    data() {
        return{
            id:"",
            password:"",
        }
    },
    setup(){
        const store = useStore();

        const user = computed(() => store.getters['login/getUserLogin']);
        return {store, user}
    },
    methods:{
        
        login(){
            
            console.log(this.user_id);
            console.log(this.password);
            console.log(this.store)
            //console.log(setup)
            const loginObj={
                 user_id : this.user_id,
                 password : this.password
            }
            this.store.dispatch('login/userLogin', loginObj);

            //const user = computed(() => this.store.getters['login/getUserLogin']);

            //console.log("user", user)
        }
    }
}
</script>

<style>

</style>