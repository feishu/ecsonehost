<template>
    <div class="deviceitem">
        <div class="header">
            <div class="tipline"></div>
            <text class="tiptext">调</text>
            <text class="headertext">2000000203</text>
            <div class="fixright">
                <text class="fixright-tip">未审核</text>
            </div>
            <div class="headerline"></div>
        </div>
        <div class="content">
            <div class="content-row">
                <text class="content-title">申请人:</text>
                <text class="content-text">XMS10018  黄文强</text>
            </div>
            <div class="content-row">
                <text class="content-title">类型:</text>
                <text class="content-text">调用</text>
            </div>
            <div class="content-row">
                <text class="content-title">申请时间:</text>
                <text class="content-text">2019-04-08 09:46:33</text>
            </div>
        </div> 
    </div>
</template>
<script>
export default {

}
</script>

<style lang="scss">
.deviceitem{
    margin-bottom: 12px;
    background-color: white;
    border-radius: 10px;
    // padding: 25px 10px;
}
.header{
    position: relative;
    height: 100px;
    padding: 0px 20px;
    display: flex;
    flex-direction: row;
    align-items: center;
}
.tipline{
    background-color: #20aa39;
    width: 4px;
    height: 32px;
}
.tiptext{
    width: 34px;
    margin-left: 10px;
    height: 34px;
    border-radius: 34px;
    background-color: #d43030;
    color: white;
    font-size: 20px;
    text-align: center;
    line-height: 34px;
}
.headertext{
	height: 50px;
	color: rgba(0, 0, 0, 1);
	font-size: 34px;
	line-height: 50px;
    margin-left: 15px;

}
.headerline{
    height: 1px;
    border-width: 2px;
    border-style: dashed;
    border-color: rgba(153, 153, 153, 1);
    position: absolute;
    bottom: 0;
    left: 20px;
    right: 20px;
}
.fixright{
    position: absolute;
    right: 0;
    top: 0;
    bottom: 0;
    flex:1;
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    justify-content: flex-start;
}
.fixright-tip{
    margin-top: 12px;
    color: #a6a6a6;
    font-size: 22px;
    height: 40px;
    line-height: 40px;
    padding: 0 20px;
	color: rgba(255, 255, 255, 1);
	background-color: rgba(21, 126, 251, 1);
	border-top-left-radius: 22px;
	border-bottom-left-radius: 22px;
    text-align: center;
}
.content{
    padding: 22px;
}
.content-text{
	line-height: 44px;
	color: rgba(56, 56, 56, 1);
	font-size: 28px;
	text-align: left;
}
.content-title{
    line-height: 44px;
	color: rgba(166, 166, 166, 1);
	font-size: 28px;
    margin-right: 20px;
    width: 140px;
}
.content-row{
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
}

</style>
