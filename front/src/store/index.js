import {createStore} from 'vuex';
import login from "./module/user/login";
import modal from "./module/modal/modal";
import board from "./module/board/board";
import coin from "./module/coin/coin";

export const store = createStore({
    modules : {login, modal, board, coin},
})