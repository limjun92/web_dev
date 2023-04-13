<template>
    <div>
        <h2>coinDashBoard</h2>
        <button v-on:click = "watchAndUpdate()">버튼</button>
        <button v-on:click = "start()">시작</button>
        <button v-on:click = "end()">중지</button>
        <input type="text" v-model="buyRatio"/>
        <input type="text" v-model="upRatio"/>
        <input type="text" v-model="pay"/>
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
                    <td v-else><button v-on:click="coinInfoUpdate(coinInfo.coin_Id, coinInfo.lock_Top, coinInfo.lock_Bottom, coinInfo.use_Yn, coinInfo.fix)">수정</button></td>
                </tr>
            </tbody>
        </table>
        {{ coinInfoUpdateId }}
        {{ use_Yn }}
        {{ lock_Top }}
        {{ lock_Bottom }}
    </div>
</template>

<script>

import {useStore} from "vuex";
import {computed} from "vue";
const sleep = delay => new Promise(resolve => setTimeout(resolve, delay));

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

            buyPrice:5500,

            startFlag:false,
            //
            buyRatio:0.99,
            upRatio:1.03,
            pay:5500,

            buyCount:0,
        }
    },
    setup(){
        const store = useStore();
        //1. CoinDB정보를 가장 먼저 가져온다.
        const coinAllInfoTmp = computed(() => store.getters['coin/getCoinAllInfo']);
        //Coin현재가를 가져온다
        const currentPrice = computed(() => store.getters['coin/getCurrentPrice']);
        const buyFlag = computed(() => store.getters['coin/getBuyFlag']);
        //DB코인 정보 가져오기
        
        
        console.log(coinAllInfoTmp)
        return {store, coinAllInfoTmp, currentPrice, buyFlag}
    },
    async created(){
        await this.store.dispatch('coin/CoinAllInfo', 1);
        var markets = ""
        this.coinAllInfoTmp.forEach(element =>{
            this.coinAllInfo[element.coin_Nm] = element
            markets += element.coin_Nm + ", "
        })
        markets = markets.slice(0, -2)
        await this.store.dispatch('coin/getCurrentPrice', markets);
        this.currentPrice.forEach(async element =>{
            this.coinAllInfo[element.market].price = element.trade_price
        })
    },
    watch:{
        //시작하면 DB정보를 한번 읽어온다.
         coinAllInfoTmp: async function(){
            //console.log("======watch")
            //await sleep(1000)
            //DB에서 가져온 정보를 수정해서 Dict형태로 변경한다.
            //var markets = ""
            // this.coinAllInfoTmp.forEach(element =>{
            //     this.coinAllInfo[element.coin_Nm] = element
            //     //markets += element.coin_Nm + ", "
            // })
            //console.log("??")
            // markets = markets.slice(0, -2)
            // //2. coin의 현재가격을 가져온다.
            // await this.store.dispatch('coin/getCurrentPrice', markets);
            // this.currentPrice.forEach(async element =>{
            //         this.coinAllInfo[element.market].price = element.trade_price
            // })
            //console.log("=======watchend")
        },
        buyFlag: function(){
            console.log("buyFlag")
        }
        //구매하면 변경이 되기 때문에 한번 호출을 해준다.
        //buyFlag: function(){
        //    this.store.dispatch('coin/CoinAllInfo', 1);
        //    this.store.dispatch('coinBuySell/TradeInfo');
        //}
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
            console.log("this.coinAllInfoTmp 1", this.coinAllInfoTmp)
            //DB에서 가져온 정보를 수정해서 Dict형태로 변경한다.
            this.markets = ""
            this.coinAllInfoTmp.forEach(element =>{
                //use_Yn == Y인값만
                if(element.use_Yn == 'Y'){
                    this.coinAllInfo[element.coin_Nm] = element
                    this.markets += element.coin_Nm + ", "
                }
            })
            this.markets = this.markets.slice(0, -2)
            this.startFlag = true;
            while(this.startFlag){
                await sleep(1000)
                console.log("=======================================")
                //현재가 조회
                
                await this.store.dispatch('coin/getCurrentPrice', this.markets);
                //console.log("start")
                this.currentPrice.forEach(async element =>{
                    //현재가격을 업데이트 해준다.
                    //console.log("fix start ", this.coinAllInfo[element.market].fix)
                    this.coinAllInfo[element.market].price = element.trade_price
                    //사기
                    //현재 가격이 예약 가격보다 작으면 산다.
                    if(this.coinAllInfo[element.market].price < this.coinAllInfo[element.market].fix){
                        //사기위해 넘겨주는 값
                        const CoinObj={
                            userId : this.coinAllInfo[element.market].user_Id,  // user_Id
                            pay : this.pay,                                     // 지불하는돈 default 5500
                            market : element.market,                            // 코인명
                            type : "test",                                      
                            sellPrice : this.coinAllInfo[element.market].fix * this.upRatio * (1/this.buyRatio),     // 코인판매가치 설정 현재가격 * default(1.03)
                            nowPrice : element.trade_price,                     // 코인현재가치
                            buyRatio : this.buyRatio                            // 다음 구매 가격을 정하기 위해 넘김 defalt(0.99)
                        }
                        //======================
                        //await this.store.dispatch('coinBuySell/buy', CoinObj);
                        await this.store.dispatch('coinBuySell/buyTest', CoinObj);
                        //console.log("buy -> monitering");
                        await this.store.dispatch('coin/CoinAllInfo', 1);

                        //console.log("this.coinAllInfoTmp 2", this.coinAllInfoTmp)

                        this.coinAllInfoTmp.forEach(element =>{
                            this.coinAllInfo[element.coin_Nm] = element
                        })

                        this.store.dispatch('coinBuySell/TradeInfo');
                        //console.log("fix!! ", this.coinAllInfo[element.market].fix)
                    }
                    //한개를 산다.
                    //fix가격을 조정한다. 현재산가격 *0.98*
                    //이력용 테이블에 한줄을 추가한다.
                    //이력용 테이블을 호출해서 보여준다.
                })
                //팔기
                //DB에는 3%정도 오르면 팔리도록 한다.
            }
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