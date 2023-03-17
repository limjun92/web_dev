<template>
    <h2>coinDashBoard</h2>
    <button v-on:click = "watchAndUpdate()">버튼</button>
    <button v-on:click = "start()">시작</button>
    <button v-on:click = "end()">중지</button>
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
                <!-- <th>생성일</th> -->
                <!-- <th>수정일</th> -->
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
                <!-- <td>{{new Date(Date.parse(coinInfo.created_dt)).toLocaleString()}}</td> -->
                <!-- <td>{{new Date(Date.parse(coinInfo.updated_dt)).toLocaleString()}}</td> -->
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
//const sleep = delay => new Promise(resolve => setTimeout(resolve, delay));

export default {
    data(){
        return{
            coinInfoUpdateId:"",
            use_Yn:"",
            lock_Top:0,
            lock_Bottom:0,
            fix:0,
            coinAllInfo:{},
            markets:"",

            startFlag:false
        }
    },
    setup(){
        const store = useStore();
        const coinAllInfoTmp = computed(() => store.getters['coin/getCoinAllInfo']);
        const currentPrice = computed(() => store.getters['coin/getCurrentPrice']);
        //DB코인 정보 가져오기
        store.dispatch('coin/CoinAllInfo', 1);
        console.log(coinAllInfoTmp)
        return {store, coinAllInfoTmp, currentPrice}
    },
    watch:{
        coinAllInfoTmp: function(){
            //DB에서 가져온 정보를 수정해서 Dict형태로 변경한다.
            this.coinAllInfoTmp.forEach(element =>{
                this.coinAllInfo[element.coin_Nm] = element
            })
            console.log(this.coinAllInfo['KRW-BTC'].coin_Nm)
        }
    },
    methods:{
        //수정 버튼 클릭
        coinInfoUpdate(coin_Id, lock_Top, lock_Bottom, use_Yn, fix){
            this.coinInfoUpdateId = coin_Id;
            this.lock_Top = lock_Top;
            this.lock_Bottom = lock_Bottom;
            this.use_Yn = use_Yn;
            this.fix = fix;
        },

        //완료버튼 클릭
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

        //모니터링 
        watchAndUpdate(){
            this.coinAllInfoTmp.forEach(element => {
                console.log(element.user_Id, element.coin_Nm)
                const CoinObj={
                    userId : element.user_Id,
                    coinNm : element.coin_Nm,
                }
                this.store.dispatch('coin/getCoinOrder', CoinObj);
            });
        },
        
        //시작
        async start(){


            //DB에서 가져온 정보를 수정해서 Dict형태로 변경한다.
            this.markets = ""
            this.coinAllInfoTmp.forEach(element =>{
                this.coinAllInfo[element.coin_Nm] = element
                this.markets += element.coin_Nm + ", "
            })
            this.markets = this.markets.slice(0, -2)
            this.startFlag = true;

            while(this.startFlag){
                await this.store.dispatch('coin/getCurrentPrice', this.markets);
                // await sleep(100).then(async ()=>{
                //     await this.store.dispatch('coin/getCurrentPrice', this.markets);
                // });
                //await sleep(1000)
                console.log("start")
                this.currentPrice.forEach(element =>{
                    this.coinAllInfo[element.market].price = element.trade_price
                })
            }

            //this.store.dispatch('coin/getCurrentPrice', this.markets);
            //현재가를 가져오는 걸 호출한다

        },
        end(){  
            console.log("중지!!")
            this.startFlag = false;
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