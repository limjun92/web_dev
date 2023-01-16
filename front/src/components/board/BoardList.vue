<template>
    <h1>BoardList</h1>
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
                <tr v-for="(board, index) in boardList.board" :key="index">
                    <td>
                        <!-- <li @click="$router.push('/Board/'+ board.board_id)">{{board.title}}</li> -->
                        <li v-on:click="BoardDetail(board.board_id)">{{board.title}}</li>
                    </td>
                    <td>{{board.created_Dt}}
 
                    </td>
                    <td>{{board.User_Id}}
 
                    </td>
                </tr>
            </tbody>
        </table>
        <button :disabled="beforePageAble" v-on:click="beforePage()">이전</button><button :disabled="afterPageAble" v-on:click="NextPage()">이후</button>
        <button v-on:click="boardWrite()">등록</button>
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
            router : this.$router
        }
    },
    setup(){
        const store = useStore();
        const user = computed(() => store.getters['login/getUserLogin']);
        
        //console.log("user ", user.value.row_id)
        //1 처음에는 항상 1페이지 5개를 보여준다.
        const storeObj = {
            pageNum : 1,
            pageSize : 5,
            //user_id : user.value.user_id,
            user_id : 'jh0508'
        }
        //게시글 리스트 가져오기
        store.dispatch('board/BoardList', storeObj);
        //테스트로 모든 유저 가져오기
        store.dispatch('login/all_users');

        const boardList = computed(() => store.getters['board/getBoardList']);
        
        return {store, boardList, user}
    },
    computed:{
        //pageNum을 체크해서 1페이지일때 이전 페이지로 이동 못하도록 한다.
        beforePageAble(){
            if(this.pageNum == 1){
                return true
            }else{
                return false
            }
        },
        afterPageAble(){
            console.log(this.boardList.count, this.pageSize, this.pageNum)
            if(this.boardList.board_count / this.pageSize > this.pageNum){
                return false
            }else{
                return true
            }
        }
    },
    methods:{
        //다음페이지로 가기
        NextPage(){
            this.pageNum += 1;
            console.log("next", this.user.user_id)
            const storeObj = {
                pageNum : this.pageNum,
                pageSize : this.pageSize,
                //user_id : this.user.user_id,
                user_id : 'jh0508'
            }

            this.store.dispatch('board/BoardList', storeObj);

            const tmpboardList = computed(() => this.store.getters['board/getBoardList']);

            this.boardList = tmpboardList
            
            },
        //이전페이지로 가기
        beforePage(){
            this.pageNum -= 1;

            const storeObj = {
                pageNum : this.pageNum,
                pageSize : this.pageSize,
                //user_id : this.user.user_id,
                user_id : 'jh0508'
            }

            this.store.dispatch('board/BoardList', storeObj);

            this.boardList = computed(() => this.store.getters['board/getBoardList']);
        },

        //BoardDetailPage확인
        BoardDetail(boardId){
            console.log(boardId)
            //var router = this.$router;
            this.router.push('/Board/'+ boardId)
            this.store.dispatch('board/boardDetail', boardId);
        }, 
        // 게시글 등록 
        boardWrite(){
            this.router.push('/Board/write')
        }
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