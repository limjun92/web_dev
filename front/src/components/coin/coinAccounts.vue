<template>
    <h2>coinDashBoard</h2>
    <table class = 'coinAllInfo'>
        <thead>
            <tr>
                <!-- <th>코인ID</th> -->
                <!-- <th>User_Id</th> -->
                <th>이름</th>
                <th>타입</th>
                <th>구매횟수</th>
                <th>판매횟수</th>
                <th>사용</th>
                <th>현재가</th>
                <th>예약가</th>
                <th>제한상단</th>
                <th>제한하단</th>
                <th>사용금액</th>
                <th>회수금액</th>
                <th>순이익</th>
                <th>생성일</th>
                <th>수정일</th>
                <th>수정</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(coinInfo, index) in coinAllInfo" :key="index">
                <!-- <td>{{coinInfo.coin_Id}}</td> -->
                <!-- <td>{{coinInfo.user_Id}}</td> -->
                <td>{{coinInfo.coin_Nm}}</td>
                <td>{{coinInfo.type}}</td>
                
                <td>{{coinInfo.buy_Cnt}}</td>
                <td>{{coinInfo.sell_Cnt}}</td>
                <td v-if = "coinInfoUpdateId == coinInfo.coin_Id">
                    <select v-model="use_Yn">
                        <option>Y</option>
                        <option>N</option>
                    </select>
                </td >
                <td v-else>{{coinInfo.use_Yn}}</td>
                <td>{{coinInfo.price}}</td>
                <td v-if = "coinInfoUpdateId == coinInfo.coin_Id"><input type="text" v-model="fix"/></td>
                <td v-else>{{coinInfo.fix}}</td>
                <td v-if = "coinInfoUpdateId == coinInfo.coin_Id"><input type="text" v-model="lock_Top"/></td>
                <td v-else>{{coinInfo.lock_Top}}</td>
                <td v-if = "coinInfoUpdateId == coinInfo.coin_Id"><input type="text" v-model="lock_Bottom"/></td>
                <td v-else>{{coinInfo.lock_Bottom}}</td>
                <td>{{coinInfo.use_Krw}}</td>
                <td>{{coinInfo.back_Krw}}</td>
                <td>{{coinInfo.re_Get}}</td>
                <td>{{new Date(Date.parse(coinInfo.created_dt)).toLocaleString()}}</td>
                <td>{{new Date(Date.parse(coinInfo.updated_dt)).toLocaleString()}}</td>
                <td v-if = "coinInfoUpdateId == coinInfo.coin_Id"><button v-on:click="coinInfoUpdateComplete(coinInfo.user_Id, coinInfo.coin_Id)">완료</button></td>
                <td v-else><button v-on:click="coinInfoUpdate(coinInfo.coin_Id, coinInfo.lock_Top, coinInfo.lock_Bottom, coinInfo.use_Yn, fix)">수정</button></td>
            </tr>
        </tbody>
    </table>
    {{ coinInfoUpdateId }}
    {{ use_Yn }}
    {{ lock_Top }}
    {{ lock_Bottom }}

</template>

<script>

import {useStore} from "vuex";
import {computed} from "vue";

export default {
    data(){
        return{
            coinInfoUpdateId:"",
            use_Yn:"",
            lock_Top:0,
            lock_Bottom:0,
            fix:0,
        }
    },
    setup(){
        const store = useStore();
        const coinAllInfo = computed(() => store.getters['coin/getCoinAllInfo']);
        
        //게시글 리스트 가져오기
        store.dispatch('coin/CoinAllInfo', 1);

        return {store, coinAllInfo}
    },
    methods:{
        coinInfoUpdate(coin_Id, lock_Top, lock_Bottom, use_Yn, fix){
            this.coinInfoUpdateId = coin_Id;
            this.lock_Top = lock_Top;
            this.lock_Bottom = lock_Bottom;
            this.use_Yn = use_Yn;
            this.fix = fix;
        },
        coinInfoUpdateComplete(user_Id, coin_Id){
            this.coinInfoUpdateId = "";
            console.log(coin_Id)
            
            const loginObj={
                user_Id : user_Id,
                coin_Id : coin_Id,
                lock_Top : this.lock_Top,
                lock_Bottom : this.lock_Bottom,
                use_Yn : this.use_Yn,
                fix : this.fix,
            }

            this.store.dispatch('coin/coinInfoUpdate', loginObj);

        },
    }
}
</script>

<style scoped>

.coinAllInfo{
    border:1px solid;
}
th, td {
    border: 1px solid #444444;
  }
</style>