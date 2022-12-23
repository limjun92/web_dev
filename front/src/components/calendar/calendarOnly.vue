<template>
    <h2>calendar_??</h2>
    <!-- <modal> -->
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
                    <tr v-for="(week, index) in dates" :key="index">
                        <td scope="row" v-for="date in week" :key="date">{{date}}
                            <button v-on:click="trace(index, date)">이후</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        
    </div>
    
</template>

<script>

// import modal from '@/components/common/modalUI.vue'

export default {
    components: {
        // modal,
    },
    data(){
        return{
            year:-1,
            month:-1,
            days:['일','월','화','수','목','금','토'],
            week:[],
            dates:[],
            date:null,
            openToDo:false,
        }
    },
    created(){
        this.date = new Date();
        this.getWeekDate(this.date)
    },
    methods:{
        //캘린더 생성
        getWeekDate(dateinfo){
            this.year = dateinfo.getFullYear();
            this.month = dateinfo.getMonth()+1;
            this.getLastMonthLastWeek(dateinfo);
            this.getThisMonthWeeks(dateinfo);
            this.getNextMonthFirstWeeks();
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
        getNextMonthFirstWeeks(){
            //let date = new Date(dateinfo.getFullYear(), dateinfo.getMonth()+1, 0);
            //let firstDay = date.getDay();
            let i = 1
            while(this.dates.length!=6){
                this.week.push(i);
                i++;
                if(this.week.length == 7){
                    this.dates.push(this.week);
                    this.week = [];
                }
            }
            // for(let i = 1; i<7-firstDay; i++){
            //     this.week.push(i);
            // }
            // this.dates.push(this.week);
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
        },
        //trace
        trace(index, date){
            let year = this.year;
            let month = this.month;
            if(index == 0 && date > 15){
                month = this.month-1;
                if(month == 0){
                    month = 12;
                    year--;
                }
            }
            if(index >= 4 && date < 15){
                month = this.month+1;
                if(month == 13){
                    month = 1;
                    year++;
                }
            }
            console.log(year, month, date)

            this.openToDo = !this.openToDo;
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
    height: 600px;
    margin-left: auto;
    margin-right: auto;
    border-collapse: collapse;
    border:1px solid;
}
</style>