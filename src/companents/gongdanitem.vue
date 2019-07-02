<template>
    <div class="deviceitem">
        <div class="header">
            <div class="tipline"></div>
            <text class="headertext">2000000203      现调机   </text>
            <div class="fixright">
                <text class="fixright-tip">未审核</text>
            </div>
            <div class="headerline"></div>
        </div>
        <div class="content">
            <div class="content-row">
                <text class="content-title">申请编号:</text>
                <text class="content-text">2a754fd9-1cf6-42</text>
            </div>
            <div class="content-row">
                <text class="content-title">型号:</text>
                <text class="content-text">140GA47110006/富申L-9005</text>
            </div>
            <div class="content-row">
                <text class="content-title">资产编号:</text>
                <text class="content-text">140GA47110006</text>
            </div>
            <div class="content-row">
                <text class="content-title">翻新等级:</text>
                <text class="content-text">1</text>
            </div>
            <div class="content-row">
                <text class="content-title">门数:</text>
                <text class="content-text">1</text>
            </div>
            <div class="content-row">
                <text class="content-title">工单类型:</text>
                <text class="content-text">ZC15</text>
            </div>
            <div class="content-row">
                <text class="content-title">工单中心:</text>
                <text class="content-text">ZJR20010李铭</text>
            </div>
            <div class="content-row">
                <text class="content-title">计划开始日间:</text>
                <text class="content-text">2019-04-16</text>
            </div>
        </div> 
    </div>
</template>
<script>
export default {

}
</script>

<style lang="scss">
@import '../style/global';
.deviceitem{
    margin: 12px;
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
    justify-content: center;
}
.fixright-tip{
    margin-top: 12px;
    color: #a6a6a6;
    font-size: 28px;
    height: 40px;
    line-height: 40px;
    padding: 0 20px;
	color: $themeColor;
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
    width: 190px;
}
.content-row{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
}

</style>
