<template>
    <div class="titleblock" :style="{backgroundColor:bgcolor||'transparent',paddingTop:bgcolor?'20px':'0px',marginTop:bgcolor?'15px':'34px'}">
        <text class="title" :style="{color:color||'rgba(128, 128, 128, 1)'}">{{title}}</text>
        <div class="content">
            <slot></slot>
        </div>
    </div>
</template>
<script>
export default {
  props: {
    title: String,
    color: String,
    bgcolor: String,
  },
}
</script>

<style lang="scss" scoped>
.titleblock{
    margin-top: 34px;
}
.title{
    height: 42px;
	color: rgba(128, 128, 128, 1);
	font-size: 28px;
    padding-left: 20px;
}
.content{
    margin-top: 8px;
    background-color: white;
    padding: 20px;
    position: relative;
}
</style>
