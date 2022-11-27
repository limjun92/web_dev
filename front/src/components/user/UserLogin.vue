<template>
    <h1>login</h1>
    <input type="text" v-model="id">
    <input type="text" v-model="password">
    <button v-on:click="login">로그인</button>
</template>

<script>
import {useStore} from "vuex";

export default {

    data() {
        return{
            id:"",
            password:"",
        }
    },
    setup(){
        const store = useStore();
        return {store}
    },
    methods:{
        
        login(){
            
            console.log(this.id);
            console.log(this.password);
            console.log(this.store)
            //console.log(setup)
            // const loginObj={
            //     id = this.id,
            //     password = this.password
            // }
            // this.store.dispatch('login/all_users', loginObj);
        }
    }
}
</script>

<style>

</style>