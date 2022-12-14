import axios from 'axios';

let api_url = 'http://localhost:8090/test'
//import {createStore} from "vuex";
console.log("??")

const state = {
    BoardList:{},
}

const getters = {
    getBoardList(state){
        return state.BoardList;
    },
}

const actions = {
    //게시글 리스트 가져오기
    BoardList(context, storeObj){
            axios.get(`${api_url}/BoardList/${storeObj.pageNum}/${storeObj.pageSize}/${storeObj.user_id}`)
            .then(res => res.data)
            .then(item => (context.commit('setBoardList',item)))
            .catch(error => console.error(error));
    }
}

const mutations = {
    setBoardList(state, item){
        console.log('item', item)
        state.BoardList = item
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