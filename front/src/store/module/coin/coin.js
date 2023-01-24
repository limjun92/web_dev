import axios from 'axios';

let api_url = 'http://localhost:8090/test'

const state = {
    CoinAccounts:{},
    CoinAllInfo:{},
}

const getters = {
    getCoinAccounts(state){
        return state.CoinAccounts;
    },
    getCoinAllInfo(state){
        return state.CoinAllInfo;
    },
}

const actions = {
    CoinAccounts(context, user_id){
        axios.get(`${api_url}/CoinDashBoard/${user_id}/`)
        .then(res => res.data)
        .then(item => (context.commit('setCoinAccounts',item)))
        .catch(error => console.error(error));
    },
    CoinAllInfo(context, user_id){
        axios.get(`${api_url}/CoinAllInfo/${user_id}/`)
        .then(res => res.data)
        .then(item => (context.commit('setCoinAllInfo',item)))
        .catch(error => console.error(error));
    },
    coinInfoUpdate(context, storeObj){
        axios.post(`${api_url}/coinInfoUpdate`, storeObj)
        .then(res => res.data)
        .then(item => (context.commit('setCoinAllInfo',item)))
        .catch(error => console.error(error));
    }
}

const mutations = {
    setCoinAccounts(state, item){
        console.log('item', item)
        state.CoinAccounts = item
    },
    setCoinAllInfo(state, item){
        console.log('item', item)
        state.CoinAllInfo = item
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