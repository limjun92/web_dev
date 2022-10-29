<template>
  <h1>준형</h1>
  {{nickname}}
</template>

<script>

import {useStore} from "vuex";
import {computed} from "vue";

export default {
    name : "AxiosTest",

    setup(){
        const store = useStore();
        console.log("?", store)
        console.log("?", store.state.login.nickname)
        //const nickname = store.state.login.nickname;

        const nickname = computed(() => store.state.login.nickname);

        console.log("????", nickname)
        return {nickname};
    }
}
</script>

<style>

</style>