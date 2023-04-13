import {createStore} from 'vuex';
import login from "./module/user/login";
import modal from "./module/modal/modal";
import board from "./module/board/board";
import coin from "./module/coin/coin";
import coinBuySell from "./module/coin/coinBuySell"

export const store = createStore({
    modules : {login, modal, board, coin, coinBuySell},
})