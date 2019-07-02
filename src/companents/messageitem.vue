<template>
    <div class="msgitem">
        <text class="title">{{title}}</text>
        <text class="content">{{content}}</text>
    </div>
</template>
<script>
export default {
  props: {
    content: String,
    title: String,
  },
}
</script>

<style lang="scss">
    .msgitem{
        padding: 20px 25px 40px 20px;
        background-color: white;
        border-radius: 10px;
        margin-bottom: 50px;
    }
    .title{
        color: rgba(80, 80, 80, 1);
	    font-size: 28px;
        line-height: 40px;
    }
    .content{
        color: rgba(80, 80, 80, 1);
	    font-size: 24px;
        line-height: 36px;
    }
</style>
