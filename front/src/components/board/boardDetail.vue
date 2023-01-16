<template>
    <div>
    <h1>boardDetail</h1>
    {{ $route.params.id }}
    {{ boardDetail }}
    <h2> {{boardDetail.title}}</h2>
    <Markdown :source="boardDetail.content" />
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

</style>