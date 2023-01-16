<template>
  <h1>준형</h1>
  {{nickname}}
  {{allUser}}
</template>

<script>

import {useStore} from "vuex";
import {computed} from "vue";

export default {
    name : "AxiosTest",

    setup(){
        const store = useStore();
        const nickname = computed(() => store.state.login.nickname);
        console.log(store)

        const allUser = computed(() => store.getters['login/getAllUser']);
        console.log(allUser);
        //중괄호를 꼭 해야지 보임
        return {nickname, allUser};
    }
}
</script>

<style>

</style>