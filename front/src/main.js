import { createApp } from 'vue'
import App from './App.vue'
import { store } from './store'
import router from './router'
import { useCookies } from 'vue3-cookies'
import Markdown from 'vue3-markdown-it';

createApp(App)
.use(store)
.use(router)
.use(Markdown)
//쿠키 기간 설정하기
.use(useCookies,{
    expireTimes: "30d",
    secure: true,
})
.mount('#app')