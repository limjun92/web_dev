<template>
    <div>
    <h1>boardList</h1>
        <!-- <boardList/> -->
        <BoardList/>
    </div>
</template>

<script>

import BoardList from '@/components/board/BoardList.vue'

export default {
    name: 'boardList',
    components: {
        BoardList,
    }
}

</script>

<style>

</style>