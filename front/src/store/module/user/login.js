import axios from 'axios';

let api_url = 'http://localhost:8090/test'
//import {createStore} from "vuex";
console.log("??")

const state = {
    allUsers:{},
    nickname:"JunHyeong"
}

const getters = {
    getAllUser(state){
        return state.allUsers;
    },
    getNickName(state){
        return state.nickname;
    }
}

const actions = {
    all_users(context){
        axios.post(`${api_url}/user`)
            .then(res => res.data)
            .then(item => (context.commit('setAllUser',item)))
            .catch(error => console.error(error));
    },
}

const mutations = {
    setAllUser(state, item){
        state.allUsers = item;
    },
}

export default {
    state, getters, actions, mutations
}



// export default createStore({
//     state: {
//         allUsers:{},
//         name:"JunHyeong"
//     },
    
//     getters: {
//         getAllUser(state){
//             return state.allUsers;
//         },
//     },
    
//     actions: {
//         all_users(context){
//             axios.post(`${api_url}/user`)
//                 .then(res => res.data)
//                 .then(item => (context.commit('setAllUser',item)))
//                 .catch(error => console.error(error));
//         },
//     },
    
//     mutations: {
//         setAllUser(state, item){
//             state.allUsers = item;
//         },
//     }
// });