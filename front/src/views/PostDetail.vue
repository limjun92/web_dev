<template>
    <div>
        <h1>PostDetail</h1>
        <div class="editor-menu">
            <button onclick="document.execCommand('bold')">
                <b>B</b>
            </button>
            <button onclick="document.execCommand('Italic')">
                <i>I</i>
            </button>
            <button onclick="document.execCommand('Underline')">
                <u>U</u>
            </button>
            <button onclick="document.execCommand('StrikeThrough')">
                <s>S</s>
            </button>
        </div>

        <div class = "content" contenteditable="true">
            <p>
                <br>
            </p>
        </div>
    </div>
</template>

<script>
export default {
    methods:{
        bold(){
            console.log(window.getSelection());
            document.execCommand('bold')
        }
    }
}
</script>

<style>
.content{
    border: 1px solid;
    width:100%;
    height: 400px;
}
</style>