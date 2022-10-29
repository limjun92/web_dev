import {createStore} from 'vuex';
import login from "./module/user/login";

export const store = createStore({
    modules : {login},
})