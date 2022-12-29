<template>
    <h1>BoardList</h1>
    {{boardList}}
    <div>
        <table class = 'boardList'>
            <thead>
                <tr>
                    <th>타이틀</th>
                    <th>등록일</th>
                    <th>등록자</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(board, index) in boardList" :key="index">
                    <td>
                        <!-- <li><router-link to="/Board/1">{{board.title}}</router-link></li> -->
                        <li @click="$router.push('/Board/'+ board.board_id)">{{board.title}}</li>
                    </td>
                    <td>{{board.created_Dt}}
 
                    </td>
                    <td>{{board.writer}}
 
                    </td>
                </tr>
            </tbody>
        </table>
        <button :disabled="beforePageDisAble" v-on:click="beforePage()">이전</button><button v-on:click="NextPage()">이후</button>
        {{ pageNum }}
    </div>
    <router-view/>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    data(){
        return{
            pageNum:1,
            pageSize:5,
        }
    },
    setup(){
        const store = useStore();

        //1 처음에는 항상 1페이지 5개를 보여준다.
        const storeObj = {
            pageNum : 1,
            pageSize : 5,
        }

        store.dispatch('board/BoardList', storeObj);

        const boardList = computed(() => store.getters['board/getBoardList']);
        return {store, boardList}
    },
    computed:{
        //pageNum을 체크해서 1페이지일때 이전 페이지로 이동 못하도록 한다.
        beforePageDisAble(){
            if(this.pageNum == 1){
                return true
            }else{
                return false
            }
        }
    },
    methods:{
        //다음페이지로 가기
        NextPage(){
            this.pageNum += 1;
            const storeObj = {
                pageNum : this.pageNum,
                pageSize : this.pageSize
            }

            this.store.dispatch('board/BoardList', storeObj);

            const tmpboardList = computed(() => this.store.getters['board/getBoardList']);
            
            console.log(tmpboardList.value);
            console.log(tmpboardList.value.length);

            this.boardList = tmpboardList
            
            },
        //이전페이지로 가기
        beforePage(){
            this.pageNum -= 1;

            const storeObj = {
                pageNum : this.pageNum,
                pageSize : this.pageSize
            }

            this.store.dispatch('board/BoardList', storeObj);

            this.boardList = computed(() => this.store.getters['board/getBoardList']);
        },
    }
}
</script>

<style scoped>
.boardList{
    margin-left: auto;
    margin-right: auto;
    border-collapse: collapse;
    border:1px solid;
}
</style>