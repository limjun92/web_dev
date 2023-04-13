<template>
    <div>
        <h2>coinTradeInfo</h2>
        <table class = 'TradeInfo'>
            <thead>
                <tr>
                    <th>이름</th>
                    <th>ID</th>
                    <th>type</th>
                    <th>volume</th>
                    <th>판매가격</th>
                    <th>구매당시가치</th>
                    <th>판매플래그</th>
                    <th>구매가격</th>
                    <th>구매날짜</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(Info, index) in TradeInfo" :key="index">
                    <td>{{Info.market}}</td>
                    <td>{{Info.tradeId}}</td>
                    <td>{{Info.type}}</td>
                    <td>{{Info.volume}}</td>
                    <td>{{Info.sellPrice}}</td>
                    <td>{{Info.buyPrice}}</td>
                    <td>{{Info.sellYn}}</td>
                    <td>{{Info.pay}}</td>
                    <td>{{Info.createdDt}}</td>
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

        const TradeInfo = computed(() => store.getters['coinBuySell/getTradeInfo']);
        
        store.dispatch('coinBuySell/TradeInfo');

        return {TradeInfo}
    },
}
</script>

<style>
.TradeInfo{
    border:1px solid;
    margin:20px;
}
th, td {
    border: 1px solid #444444;
  }
</style>