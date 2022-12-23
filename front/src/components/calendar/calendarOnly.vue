<template>
    <h2>calendar_??</h2>
    <div>
        <h2>{{ year }}</h2>
        <h2>{{ month }}</h2>
        <button v-on:click="getBeforeMonth()">이전</button><button v-on:click="getNextMonth()">이후</button><button v-on:click="getThisMonth()">초기화</button>
        <div>
        <table class = 'calendal'>
            <thead>
                <tr>
                    <th scope="col" v-for="day in days" :key="day">{{day}}</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="week in dates" :key="week">
                    <td scope="row" v-for="date in week" :key="date">{{date}}</td>
                </tr>
            </tbody>
        </table>
    </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            year:-1,
            month:-1,
            days:['일','월','화','수','목','금','토'],
            week:[],
            dates:[],
            date:null,
        }
    },
    created(){
        this.date = new Date();
        this.getWeekDate(this.date)
    },
    methods:{
        getWeekDate(dateinfo){
            this.year = dateinfo.getFullYear();
            this.month = dateinfo.getMonth()+1;
            this.getLastMonthLastWeek(dateinfo);
            this.getThisMonthWeeks(dateinfo);
            this.getNextMonthFirstWeeks(dateinfo);
        },
        //저번달 마지막 주 날짜
        getLastMonthLastWeek(dateinfo){
            let date = new Date(dateinfo.getFullYear(), dateinfo.getMonth(), 0);
            let lastDate = date.getDate();
            let lastDay = date.getDay();
            if(lastDay == 6)
                return;
            for(let i = lastDate-lastDay; i<=lastDate; i++){
                this.week.push(i);
            }
        },
        //이번달 총 날짜 
        getThisMonthWeeks(dateinfo){
            let date = new Date(dateinfo.getFullYear(), dateinfo.getMonth()+1, 0);
            let lastDate = date.getDate();
            for(let i = 1; i<=lastDate; i++){
                this.week.push(i);  
                if(this.week.length == 7){
                    this.dates.push(this.week);
                    this.week = [];
                }
            }
        },
        //다음달 첫째 주 날짜
        getNextMonthFirstWeeks(dateinfo){
            let date = new Date(dateinfo.getFullYear(), dateinfo.getMonth()+1, 0);
            let firstDay = date.getDay();
            for(let i = 1; i<7-firstDay; i++){
                this.week.push(i);
            }
            this.dates.push(this.week);
        },
        //다음달로 가기
        getNextMonth(){
            this.dates = [];
            this.week = [];
            this.date = new Date(this.date.getFullYear(), this.date.getMonth()+1, 1);
            this.getWeekDate(this.date)
        },
        //이전달로 가기
        getBeforeMonth(){
            this.dates = [];
            this.week = [];
            this.date = new Date(this.date.getFullYear(), this.date.getMonth()-1, 1);
            this.getWeekDate(this.date)
        },
        //초기화
        getThisMonth(){
            this.dates = [];
            this.week = [];
            this.date = new Date();
            this.getWeekDate(this.date)
        }

    }
}
</script>

<style>
th {
    border:1px solid;
}
th, td{
    text-align: right;
    vertical-align: top;
}
.calendal{
    width : 70%;
    height: 400px;
    margin-left: auto;
    margin-right: auto;
    border-collapse: collapse;
    border:1px solid;
}
</style>