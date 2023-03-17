<template>
    <h1>게시글 등록</h1>
        <textarea v-model="contents"/>
        <div contenteditable="false">{{ contents }}</div>
        <div class="AddWrap">
            <button v-on:click="editForm()">edit</button>
            <button v-on:click="previewForm()">preview</button>
            <table class="tbAdd">
                <colgroup>
                    <col width="10%" />
                    <col width="90%" />
                </colgroup>
                <tr>
                    <th>제목</th>
                    <td v-if = "editFlag==true"><input type="text" v-model="title"/></td>
                    <td v-else class="title" >{{ title }}</td>
                </tr>
                <tr v-if = "editFlag==true">
                    <th>내용</th>
                    <td><textarea v-model="content"></textarea></td>
                </tr>
                <tr v-else>
                    <th>내용</th>
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
            contents:'<span>테스트코드</span>',
            title: "",
            content: "",
            router : this.$router,
            editFlag : true
        }
    },
    setup(){
        const store = useStore();
        const boardDetail = computed(() => store.getters['board/getBoardDetail'])
        return {store, boardDetail}
    },
    watch: {
        //등록후 변화를 감지해서 등록한 게시물로 이동 
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
        },
        editForm(){
            this.editFlag = true;
        },
        previewForm(){
            this.editFlag = false;
        }
    }
}
</script>

<style scoped>
.tbAdd{
    border-collapse: collapse;
    width:100%;
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