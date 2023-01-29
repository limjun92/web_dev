<template>
  <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
  <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
  <!-- <AxiosTest/> -->
  <div class="container">
    <header class="header">
      <div class="header_top">header</div>
      <div class="title_left">A</div>
      <div class="title">title</div>
      <div class="title-right">B</div>
      <div class="menu_item">
        <span class = "menu">
          <router-link class="menu-item" to="/">Home</router-link>
          <!-- <span v-if = "state.user"> -->
          <router-link class="menu-item" v-if = "state.user" to="/about">about</router-link>
          <router-link class="menu-item" v-if = "state.user" to="/MainCalendar">calendar</router-link>
          <router-link class="menu-item" v-if = "state.user" to="/Board">Board</router-link>
          <router-link class="menu-item" v-if = "state.user" to="/coin">coin</router-link>
          <!-- </span> -->
        </span>
      </div>
    </header>
    <div class="empty">Empty</div>
    <div class="main_left">C</div>
    <div class="main">main
      <UserLogin/>
      <router-view/>
    </div>
    <div class="main_right">D</div>
    <div class="empty2">Empty2</div>
    <footer class="footer">footer</footer>
  </div>

  
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
//import AxiosTest from './components/AxiosTest.vue'
import UserLogin from './components/user/UserLogin.vue'
import {useStore} from "vuex";
import {computed, reactive} from "vue";

export default {
  name: 'App',
  components: {
    // HelloWorld,
    //AxiosTest,
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
  height: 100%;
  margin-left: 5px;
  margin-right: 5px;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.container{
  height: 100%;
  display: grid;
  grid-template-columns: 17fr minmax(0, 66fr) 17fr;
  grid-template-rows: 7rem 1rem 1fr minmax(1rem, auto) minmax(3rem, auto);
  align-content: stretch;
  max-width: 96%;
  margin: 0 auto;
}
/* 1 */
.header:nth-child(1){
  grid-column: 1 / 4;
	grid-row: 1 / 2;
}


.header{
	display: grid;
  grid-template-columns: 17fr minmax(0, 66fr) 17fr;
  grid-template-rows: 2rem 3rem 2rem
}
/* 1-1 */
.header_top:nth-child(1){
  grid-column: 1 / 4;
	grid-row: 1 / 2;
}

/* 1-2 */
.title_left:nth-child(2){
  border: 1px solid #2c3e50;
}

.title:nth-child(3){
  border-top: 1px solid #2c3e50;
  border-bottom: 1px solid #2c3e50;
}

.title-right:nth-child(4){
  border: 1px solid #2c3e50;
}
/* 1-3 */
.menu_item:nth-child(5) {
	grid-column: 1 / 4;
	grid-row: 3 / 4;
  border-bottom: 1px solid rgb(50,50,40);
}

.menu{
  height: 2rem;
  display: flex;
  justify-content: space-evenly;
}

.menu_item a{
  display: flex;
  align-items: center;
}

/* 2 */
.empty:nth-child(2) {
	grid-column: 1 / 4;
	grid-row: 2 / 3;
}

/* 3 */
.main_left:nth-child(3){
  padding-right: 1rem;
  border-right: 1px solid rgb(50,50,40);
}

.main:nth-child(4){
  padding: 0 2rem;
}

.main_right:nth-child(5){
  padding-left: 1rem;
  border-left: 1px solid rgb(50,50,40);
}

/* 4 */
.empty2:nth-child(6) {
	grid-column: 1 / 4;
	grid-row: 4 / 5;
}

/* 5 */
.footer:nth-child(7) {
	grid-column: 1 / 4;
	grid-row: 5 / 6;
  border-top: 1px solid rgb(50,50,40);
}



</style>
