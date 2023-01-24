<template>
    <h1>coinDashBoard</h1>
    <coinAccounts/>
</template>

<script>
import coinAccounts from '@/components/coin/coinAccounts.vue'

export default {
    name: 'MainBoard',
    components: {
        coinAccounts,
    }
}
</script>

<style>

</style>