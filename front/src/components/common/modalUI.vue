<template>
    <div class="black_bg"  v-if = modalFlag v-on:click="modalClose">
        <div class="white_bg" >
            <h2>modal {{modalFlag}}</h2>
            
            <slot name = "bodys"/>
        </div>
    </div>
</template>

<script>

import {useStore} from "vuex";
import {computed} from "vue";

export default {
    setup(){
        const store = useStore();
        const modalFlag = computed(() => store.getters['modal/getModalFlag']);
        return {store, modalFlag}
    },
    
    methods:{
        //모달창을 만들고 닫을때 검은 부분을 클릭할때만 닫을 수 있도록 하는 로직 필요
        //아래와 같이 event를 걸어서 클릭한 부분의 클래스 명을 확인해서 함수가 동작하도록 설정 가능
        modalClose(event){
            if(event.target.classList.contains('black_bg')){
                this.store.dispatch('modal/modalClose');
            }
        }
    },
}
</script>

<style>
.black_bg {
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.6);
    position: fixed;
}
.white_bg{
    width: 50%;
    height: 50%;
    margin: 80px auto;
    background: white;
}
</style>