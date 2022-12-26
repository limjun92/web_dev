<template>
    <div>
        <h1>boardMain</h1>
        <boardList/>
    </div>
</template>

<script>

import boardList from '@/components/board/boardList.vue'

export default {
    name: 'MainCalendar',
    components: {
        boardList,
    }
}

</script>

<style>

</style>