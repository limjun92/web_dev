import axios from 'axios';

let api_url = 'http://localhost:8090/test'
//import {createStore} from "vuex";
console.log("??")

const state = {
    BoardList:{},
    BoardDetail:{},
}

const getters = {
    getBoardList(state){
        return state.BoardList;
    },
    getBoardDetail(state){
        return state.BoardDetail;
    },
}

const actions = {
    //게시글 리스트 가져오기
    BoardList(context, storeObj){
        axios.get(`${api_url}/BoardList/${storeObj.pageNum}/${storeObj.pageSize}/${storeObj.user_id}`)
        .then(res => res.data)
        .then(item => (context.commit('setBoardList',item)))
        .catch(error => console.error(error));
    },
    boardDetail(context, boardId){
        axios.get(`${api_url}/BoardDetail/${boardId}`)
        .then(res => res.data)
        .then(item => (context.commit('setBoardDetail',item),console.log(item)))
        .catch(error => console.error(error));
    },
    boardWrite(context, storeObj){
        axios.post(`${api_url}/BoardWrite`, storeObj)
        .then(res => res.data)
        .then(item => (context.commit('setBoardDetail',item)))
        .catch(error => console.error(error));
    }
}

const mutations = {
    setBoardList(state, item){
        console.log('item', item)
        state.BoardList = item
    },
    setBoardDetail(state, item){
        state.BoardDetail = item
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