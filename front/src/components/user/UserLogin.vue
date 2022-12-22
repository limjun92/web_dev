<template>
    <h1>login</h1>
    <input type="text" v-model="id">
    <input type="text" v-model="password">
    <button v-on:click="login">로그인</button>
    <h2>{{user.id}}</h2>
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

        const user = computed(() => store.getters['login/getUser']);
        return {store, user}
    },
    methods:{
        
        login(){
            
            console.log(this.id);
            console.log(this.password);
            console.log(this.store)
            //console.log(setup)
            const loginObj={
                 id : this.id,
                 password : this.password
            }
            this.store.dispatch('login/login', loginObj);

            const user = computed(() => this.store.getters['login/getUser']);

            console.log("user", user)
        }
    }
}
</script>

<style>

</style>