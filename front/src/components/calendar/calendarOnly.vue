<template>
    <h2>calendar_??</h2>
</template>

<script>
export default {
    created(){
        var date = new Date();
        console.log(date)

        var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);

        var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);

        console.log(firstDay, lastDay)
    }
}
</script>

<style>

</style>