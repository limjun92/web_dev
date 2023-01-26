<template>
    <div>
    <h1>boardDetail</h1>
    {{ $route.params.id }}
    {{ boardDetail }}
    <div class="container">
        <div class="containerBoardDetail">
            <div class="title"><h2> {{boardDetail.title}}</h2></div>
            <div class="content"><Markdown :source="boardDetail.content" /></div>
        </div>
    </div>
    </div>
</template>

<script>

import {useStore} from "vuex";
import {computed} from "vue";

import Markdown from 'vue3-markdown-it';

export default {
    components: {
        Markdown
    },
    setup(){
        const store = useStore();
        const boardDetail = computed(() => store.getters['board/getBoardDetail']);
        console.log(boardDetail)
        return {boardDetail}
    },
    mouted(){
        console.log("mouted")
        //this.store.dispatch('board/boardDetail', this.route.params.id);
    },  
    created(){
        console.log("created")
    }
}
</script>

<style>
.container{
    display: flex;
    justify-content: center;
    
}
.containerBoardDetail {
    display: flex;
    flex-direction: column;
    justify-content: center;
    border-style: solid;
    width:60%;
}
.title{
    background-color: #BBDEFB;
    text-align:left;
    padding: 40px;
}
.content{
    background-color: #fbe2bb;
    text-align: left;
    padding: 40px;
}
</style>