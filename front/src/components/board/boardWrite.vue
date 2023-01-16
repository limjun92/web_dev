<template>
    <h1>게시글 등록</h1>
        <div class="AddWrap">
            <table class="tbAdd">
                <colgroup>
                    <col width="10%" />
                    <col width="45%" />
                    <col width="45%" />
                </colgroup>
                <tr>
                    <th>제목</th>
                    <td><input type="text" v-model="title"/></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea v-model="content"></textarea></td>
                    <td><Markdown class="markdown" :source="content" /></td>
                </tr>
            </table>
		</div>
        <div class="btnWrap">
			<button v-on:click="boardWrite()">등록</button>
        </div>	
</template>

<script>
import {useStore} from "vuex";
import Markdown from 'vue3-markdown-it';
import {computed} from "vue";

export default {
    components: {
        Markdown,
    },
    data() {
        return {
            title: "",
            content: "",
            router : this.$router
        }
    },
    setup(){
        const store = useStore();
        const boardDetail = computed(() => store.getters['board/getBoardDetail'])
        return {store, boardDetail}
    },
    watch: {
        boardDetail: function () {
            console.log("watch")
            console.log(JSON.stringify(this.boardDetail.board_id))
            this.router.push('/Board/'+ this.boardDetail.board_id)
        }
    },
    methods:{
        boardWrite(){
            const loginObj={
                title : this.title,
                content : this.content,
                user_Id : 'jh0508'
            }
            this.store.dispatch('board/boardWrite', loginObj);
        }
    }
}
</script>

<style scoped>
.tbAdd{
    margin-left: auto;
    margin-right: auto;
    border-collapse: collapse;
    width:80%;
}
.tbAdd{
    border-top:1px solid #888;
    table-layout: fixed;
}
.tbAdd th, .tbAdd td{border-bottom:1px solid #eee; padding:5px 0;}
.tbAdd td{padding:10px 10px; box-sizing:border-box; vertical-align: top;}
.tbAdd td input{width:100%; min-height:30px; box-sizing:border-box; padding:0 10px;}
.tbAdd td textarea{width:100%; min-height:300px; padding:10px; box-sizing:border-box;}
.btnWrap{text-align:center; margin:20px 0 0 0;}
.btnWrap a{margin:0 10px;}

.markdown{
    text-align: left;
    word-wrap: break-word;
    
}
</style>