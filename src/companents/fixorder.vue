<template>
   <div class="fixorder" @click="orderclick">
        <div class="header">
            <div class="tipline"></div>
            <text class="tiptext">修</text>
            <text class="tiptext">转</text>
            <text class="headertext">2000000203</text>
            <text class="headertext other">展示柜</text>   
            <text class="tiptext-long">催 3</text>
            <div class="headerline"></div>
            <div class="fixright">
                <text class="fixright-tip">待处理</text>
                <text class="fixright-time">48 小时</text>
            </div>
        </div>  
        <div class="content">
            <text class="content-text">江厦公园 | 0515478595</text>
            <div class="content-row">
                <icon color='#383838' :size="30" :icon="'\ue617'" />
                <text class="content-text" style="margin-left:5px">拱墅区大关路213号</text>
            </div>
            <div class="around-row">
                <div class="content-row">
                    <icon color='#383838' :size="26" :icon="'\ue62f'" />
                    <text class="content-text" style="margin-left:8px">057186521258</text>
                </div>  
                <text class="content-text">建立工单      二线</text>
            </div>
            <div class="content-row" v-if="detail">
                <text class="content-title">故障描述:</text>
                <text class="content-text">无法开机</text>
            </div>
            <div class="content-row" v-if="detail">
                <text class="content-title">报修时间:</text>
                <text class="content-text">2019-04-08 09:46:33</text>
            </div>
            <div class="content-row" v-if="detail">
                <text class="content-title">反馈描述:</text>
                <text class="content-text">设备电源坏了</text>
            </div>
        </div> 
    </div> 
</template>
<script>
// import { AmIcon } from 'weex-amui'
import icon from './icon.vue'

export default {
  props: {
    detail: {
      type: Boolean,
      default: true,
    },
  },
  components: { icon },
  methods: {
    orderclick() {
      this.$emit('eclick', {})
    },
  },
}
</script>
<style lang="scss" scoped>
.fixorder{
    background-color: white;
    margin: 5px 0;
    border-radius: 10px;
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
.other{
    margin-left: 35px;
}
.tiptext-long{
    @extend .tiptext;
    width: 52px;
    margin-left: 38px;
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
    justify-content: space-around;
}
.fixright-tip{
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
.fixright-time{
    margin-right: 20px;
    color: #a6a6a6;
    font-size: 24px;
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
}
.content-row{
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
}
.around-row{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}
</style>
