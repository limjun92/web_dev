import {createStore} from 'vuex';
import login from "./module/user/login";
import modal from "./module/modal/modal";
import board from "./module/board/board";

export const store = createStore({
    modules : {login, modal, board},
})