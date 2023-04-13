<template>
    <h1>coinDashBoard</h1>
    <div class="coinMain">
        <coinAccounts/>
        <coinTradeInfo/>
    </div>
</template>

<script>
import coinAccounts from '@/components/coin/coinAccounts.vue'
import coinTradeInfo from '@/components/coin/coinTradeInfo.vue'

export default {
    name: 'MainBoard',
    components: {
        coinAccounts,
        coinTradeInfo,
    }
}
</script>

<style>
.coinMain{
  display: flex;
  justify-content: space-evenly;
}
</style>