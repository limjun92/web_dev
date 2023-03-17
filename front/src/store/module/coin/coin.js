import axios from 'axios';

let api_url = 'http://localhost:8090/test'

const state = {
    CoinAccounts:{},
    CoinAllInfo:{},
    coinOrder:{},
    CurrentPrice:{},
}

const getters = {
    getCoinAccounts(state){
        return state.CoinAccounts;
    },
    getCoinAllInfo(state){
        return state.CoinAllInfo;
    },
    getCoinOrder(state){
        return state.coinOrder;
    },
    getCurrentPrice(state){
        return state.CurrentPrice;
    },
}

const actions = {
    //upbit API 내가 가진 코인 정보를 조회해온다.
    CoinAccounts(context, user_id){
        axios.get(`${api_url}/CoinDashBoard/${user_id}/`)
        .then(res => res.data)
        .then(item => (context.commit('setCoinAccounts',item)))
        .catch(error => console.error(error));
    },

    //DB코인 정보를 조회해온다.
    CoinAllInfo(context, user_id){
        axios.get(`${api_url}/CoinAllInfo/${user_id}/`)
        .then(res => res.data)
        .then(item => (context.commit('setCoinAllInfo',item)))
        .catch(error => console.error(error));
    },

    //upbit API 각 코인에 대한 정보를 조회해온다.
    getCoinOrder(context, storeObj){
        console.log(storeObj.userId, storeObj.coinNm)
        axios.get(`${api_url}/coinOrder/${storeObj.userId}/${storeObj.coinNm}/`)
        .then(res => res.data)
        .then(item => (context.commit('setCoinOrder',item)))
        .catch(error => console.error(error));
    },

    //upbit API 각 코인의 현재 시세를 조회한다.
    async getCurrentPrice(context, str){
        await axios.get(`${api_url}/getCurrentPrice/${str}/`)
        .then(res => res.data)
        .then(item => (context.commit('setCurrentPrice',item)))
        .catch(error => console.error(error));
    },

    //코인 정보를 DB에 업데이트 한다.
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
    setCoinOrder(state, item){
        console.log('itme', item)
        state.coinOrder = item;
    },
    setCurrentPrice(state, item){
        console.log('item', item)
        state.CurrentPrice = item;
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