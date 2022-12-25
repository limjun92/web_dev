const state = {
    ModalFlag:false,
}

const getters = {
    getModalFlag(state){
        return state.ModalFlag;
    },
}

const actions = {
    modalOpen(context){
        context.commit(`setModalOpen`);
    },
    modalClose(context){
        context.commit(`setModalClose`);
    }
}

const mutations = {
    setModalOpen(state){
        state.ModalFlag = true;
    },
    setModalClose(state){
        state.ModalFlag = false;
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