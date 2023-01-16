<template>
  <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
  <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
  <AxiosTest/>
  <UserLogin/>
  <div>
    <router-link to="/">Home</router-link>
    <div v-if = "state.user">
      <router-link to="/about">about</router-link>
      <router-link to="/MainCalendar">calendar</router-link>
      <router-link to="/Board">Board</router-link>
    </div>
  </div>
  <router-view/>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import AxiosTest from './components/AxiosTest.vue'
import UserLogin from './components/user/UserLogin.vue'
import {useStore} from "vuex";
import {computed, reactive} from "vue";

export default {
  name: 'App',
  components: {
    // HelloWorld,
    AxiosTest,
    UserLogin,
  },
  setup(){
      const state = reactive({
            user: null
      })
      const store = useStore();
      state.user = computed(() => store.getters['login/getUserLogin']);
      return {store, state}
    },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
