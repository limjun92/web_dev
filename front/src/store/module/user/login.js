import axios from 'axios';

let api_url = 'http://localhost:8090/test'
//import {createStore} from "vuex";
console.log("??")

const state = {
    allUsers:{},
    user:{},
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
            axios.post(`${api_url}/userLogin`, payload)
            .then(res => res.data)
            .then(item => (context.commit('setUserLogin',item)))
            .catch(error => console.error(error));
    }
}

const mutations = {
    setAllUser(state, item){
        state.allUsers = item;
    },
    setUserLogin(state, item){
        console.log('item', item)
        state.user = item
    }
}

export default {
    //[vuex] unknown action type: login/all_users 과 같은 에러가 날때는 아래와 같이 namespaced: true를 지정한다.
    namespaced: true,
    state, 
    getters, 
    actions, 
    mutations
}