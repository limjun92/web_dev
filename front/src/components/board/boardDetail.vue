<template>
    <div>
    <h1>boardDetail</h1>
    <!-- {{ $route.params.id }} -->
    <!-- {{ boardDetail }} -->
    <button v-on:click="boardUpdate()">수정</button>
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
    data(){
        return{
            router:this.$router
        }
    },
    setup(){
        const store = useStore();
        const boardDetail = computed(() => store.getters['board/getBoardDetail']);
        console.log(boardDetail)
        return {boardDetail}
    },
    created(){
        console.log("created")
    },
    mouted(){
        console.log("mouted")
        //this.store.dispatch('board/boardDetail', this.route.params.id);
    },
    methods:{
        boardUpdate(){
            this.router.push({
                name:"BoardUpdate",
                query:{board_id: this.boardDetail.board_id}
            })
        }
    }  

}
</script>

<style scoped>
.container{
    display: flex;
    border-style: solid;
}
.containerBoardDetail {
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.title{
    text-align:left;
    padding: 40px;
}
.content{
    text-align: left;
    padding: 40px;
}
</style>