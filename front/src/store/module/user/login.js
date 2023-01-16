import axios from 'axios';
//import { useCookies } from "vue3-cookies";
//const { cookies } = useCookies();

let api_url = 'http://localhost:8090/test'

console.log("??")

const state = {
    allUsers:{},
    user:{
        row_id : ""
    },
    nickname:"JunHyeong"
}

const getters = {
    getAllUser(state){
        return state.allUsers;
    },
    getNickName(state){
        return state.nickname;
    },
    getUserLogin(state){
        return state.user;
    }
}

const actions = {
    all_users(context){
        axios.get(`${api_url}/alluser`)
            .then(res => res.data)
            .then(item => (context.commit('setAllUser',item)))
            .catch(error => console.error(error));
    },
    userLogin(context, payload){
            axios.post(`${api_url}/login`, payload)
            .then(res => res.data)
            //springboot에서 null값을 넘겨주면 아무런 내용이 없음 item이 없다면 null값이 들어가도록 한다.
            //.then(item => (context.commit('setUserLogin',item), item?cookies.set("user",item):cookies.set("user",null)))
            .then(item => (context.commit('setUserLogin',item)))
            .catch(error => console.error(error));
    },
    logout(context){
        context.commit('setLogout')
    }
}

const mutations = {
    setAllUser(state, item){
        state.allUsers = item;
    },
    setUserLogin(state, item){
        console.log('login item', item)
        localStorage.setItem('user', JSON.stringify(item))
        if(item){
            //헤더에 추가하는 부분
            axios.defaults.headers.common['Authorization'] = `Bearer ${item.token}`
        }
        //console.log(localStorage.getItem('user'))
        state.user = item;
    },
    setLogout(state){
        localStorage.setItem('user', null)
        axios.defaults.headers.common['Authorization'] = null
        state.user = null;
    },
}

export default {
    //[vuex] unknown action type: login/all_users 과 같은 에러가 날때는 아래와 같이 namespaced: true를 지정한다.
    namespaced: true,
    state, 
    getters, 
    actions, 
    mutations
}