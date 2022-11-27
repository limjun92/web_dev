<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld msg="Welcome to Your Vue.js App"/>
  <AxiosTest/>
  <UserLogin/>
  <div>
    <router-link to="/">Home</router-link>
    <router-link to="/about">about</router-link>
  </div>
  <router-view/>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import AxiosTest from './components/AxiosTest.vue'
import UserLogin from './components/user/UserLogin.vue'

export default {
  name: 'App',
  components: {
    HelloWorld,
    AxiosTest,
    UserLogin
  }
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
