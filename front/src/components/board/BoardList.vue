<template>
    <h1>BoardList</h1>
    {{boardList}}
    <div>
        <table class = 'boardList'>
            <thead>
                <tr>
                    <th>타이틀</th>
                    <th>등록일</th>
                    <th>등록자</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(board, index) in boardList" :key="index">
                    <td>{{board.title}}
 
                    </td>
                    <td>{{board.created_Dt}}
 
                    </td>
                    <td>{{board.writer}}
 
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    setup(){
        const store = useStore();

        store.dispatch('board/BoardList');

        const boardList = computed(() => store.getters['board/getBoardList']);
        return {store, boardList}
    },
}
</script>

<style scoped>
.boardList{
    margin-left: auto;
    margin-right: auto;
    border-collapse: collapse;
    border:1px solid;
}
</style>