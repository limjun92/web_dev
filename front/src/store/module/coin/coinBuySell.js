import axios from 'axios';

let api_url = 'http://localhost:8090/test'

const state = {
    TradeInfo:{},
    buyFlag:0,
}

const getters = {
    getTradeInfo(state){
        return state.TradeInfo;
    },
    getBuyFlag(state){
        return state.buyFlag;
    }
}

const actions = {
    //코인 trade정보 조회
    TradeInfo(context){
        axios.get(`${api_url}/selectTradeInfo`)
        .then(res => res.data)
        .then(item => (context.commit('setTradeInfo',item)))
        .catch(error => console.error(error));
    },
    buy(context, storeObj){
        axios.post(`${api_url}/buy`, storeObj)
        .then(res => res.data)
        .then(item => (context.commit('setBuy',item)))
        .catch(error => console.error(error));
    },
    async buyTest(context, storeObj){
        await axios.post(`${api_url}/buyTest`, storeObj)
        .then(res => res.data)
        .then(item => (context.commit('setBuy',item)))
        .catch(error => console.error(error));
    }
}

const mutations = {
    setTradeInfo(state, item){
        console.log('item Trade', item);
        state.TradeInfo = item;
    },
    setBuy(state, item){
        console.log('item Buy', item);
        state.buyFlag = item;
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