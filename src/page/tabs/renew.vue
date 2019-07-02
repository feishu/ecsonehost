<template>
    <div>
    <div class="bgheader" ></div>
    <scroller class="content">
      <div class="info-bars">
        <div class="info-item">
          <text class="info-item-num">10</text>
          <text class="info-item-title">今日未完成</text>
        </div>
        <div class="info-item">
          <text class="info-item-num">10</text>
          <text class="info-item-title">今日完成</text>
        </div>
        <div class="info-item">
          <text class="info-item-num">10</text>
          <text class="info-item-title">本月目标</text>
        </div>
        <div class="info-item">
          <text class="info-item-num">10</text>
          <text class="info-item-title">本月已完成</text>
        </div>
      </div>
      <web class="chart" src="http://192.168.29.99:8081/#/"></web>
      <div class="info-bars-m">
          <div class="info-bars-m-item">
              <text class="info-bars-m-item-title">展示柜</text>
              <text class="info-bars-m-item-num">2/5</text>
          </div>
          <div class="info-bars-m-line"></div>
          <div class="info-bars-m-item">
              <text class="info-bars-m-item-title">展示柜</text>
              <text class="info-bars-m-item-num">2/5</text>
          </div>
          <div class="info-bars-m-line">
              <text class="info-bars-m-item-title">展示柜</text>
              <text class="info-bars-m-item-num">2/5</text>
          </div>
          <div class="info-bars-m-item">
              <text class="info-bars-m-item-title">展示柜</text>
              <text class="info-bars-m-item-num">2/5</text>
              </div>
      </div>

      <div class="myorder">
        <div class="myorder-header">
            <text class="myorder-header-title">我的工单</text>
            <div class="myorder-header-right">
                <text class="myorder-header-right-tip">查看全部工单</text>
                <icon color="#808080" :size="25" :icon="'\ue68b'"/>
            </div>
        </div>
        <div class="orderdesc">
            <div class="orderitem">
                <div class="orderitem-icon">
                    <icon :size="40" :color="themeColor" :icon="'\ue6db'"/>
                    <text class="orderitem-tip">1</text>
                </div>
                <text class="orderitem-title">未分派</text>
            </div>
            <div class="orderitem">
                <div class="orderitem-icon">
                    <icon :size="40" :color="themeColor" :icon="'\ue627'"/>
                    <text class="orderitem-tip">1</text>
                </div>
                <text class="orderitem-title">待销单</text>
            </div>
            <div class="orderitem">
                <div class="orderitem-icon">
                    <icon :size="40" :color="themeColor" :icon="'\ue60a'"/>
                    <text class="orderitem-tip">1</text>
                </div>
                <text class="orderitem-title">可确认</text>
            </div>
            <div class="orderitem">
                <div class="orderitem-icon">
                    <icon :size="40" :color="themeColor" :icon="'\ue603'"/>
                    <text class="orderitem-tip">1</text>
                </div>
                <text class="orderitem-title">已完成</text>
            </div>
        </div>
        <div class="newmsg">
            <div class="newmsg-header">
                <text class="newmsg-text">最新消息</text>
                <text class="newmsg-text">09:44</text>
            </div>
            <div style="margin-top:16px;">
                <text class="newmsg-text">1201000902</text>
                <text class="newmsg-text">维修员[XMS10003的工单已提交销单]</text>
            </div>
        </div>
      </div>
    </scroller>
    </div>
</template>
<script>
import icon from '../../companents/icon.vue'
import { themeColor } from '../../utils/config'

export default {
  components: {
    icon,
  },
  data() {
    return {
      themeColor,
    }
  },
}
</script>
<style lang="scss" scoped>
@import '../../style/global';
.content{
  padding: 0 30px;
  margin-top: -130px;
  position: relative;
  z-index: 2;
}

.bgheader{
  height: 130px;
  background-color: $themeColor;
}
.info-bars{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  height:120px;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  border-bottom-width: 1px;
  border-bottom-color: rgba(153, 153, 153, 0.1);
}
.info-item{
  flex:1;
  border-right: 1px solid #e4e4e4;
  text-justify: center;
  flex-direction: column;
  align-items: center;
}
.info-item-num{
  font-size: 50px;
  color:#383838;
}
.info-item-title{
  color:#a6a6a6;
  font-size: 25px;
}
.chart{
    height: 300px;
    background-color: white;
}
.info-bars-m{
    margin-top: 20px;
    background-color: white;
    border-radius: 10px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    padding: 22px 0;
}
.info-bars-m-item{
    flex:1;
    justify-content: center;
    align-items: center;
}
.info-bars-m-line{
    height: 110px;
    width: 1px;
    background-color: rgba(153, 153, 153, 0.1);
}
.info-bars-m-item-title{
    height: 50px;
	color: rgba(56, 56, 56, 1);
	font-size: 34px;
}
.info-bars-m-item-num{
    height: 36px;
	color: rgba(166, 166, 166, 1);
	font-size: 24px;
}


.myorder{
    margin-top: 20px;
    margin-bottom: 20px;
    background-color: white;
    border-radius: 10px;
}
.myorder-header{
    padding: 16px ;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    border-bottom-width: 1px;
    border-bottom-color: rgba(153, 153, 153, 0.1);

}
.myorder-header-title{
    line-height: 42px;
	color: rgba(56, 56, 56, 1);
	font-size: 28px;
}
.myorder-header-right{
    display: flex;
    flex-direction: row;
    justify-content:flex-end;
    align-items: center;
}
.myorder-header-right-tip{
    line-height: 36px;
	color: rgba(128, 128, 128, 1);
	font-size: 24px;
}

.orderdesc{
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    margin-top: 20px;
    margin-bottom: 20px;
}
.orderitem{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.orderitem-title{
    height: 36px;
	color: rgba(56, 56, 56, 1);
	font-size: 24px;
}
.orderitem-icon{
    position: relative;
    padding: 14px;
}
.orderitem-tip{
    position: absolute;
    right: 0;
    top:0;
    width: 28px;
    height: 28px;
    border-width: 2px;
    border-color: $themeColor;
    border-radius: 28px;
    color: $themeColor;
    text-align: center;
    line-height: 28px;
    font-size: 22px;
    background-color: white;
}
.newmsg{
    margin: 20px;
    padding: 20px;
    background-color: #f4f4f4;
    border-radius: 10px;
}
.newmsg-header{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}
.newmsg-text{
    line-height: 36px;
	color: rgba(128, 128, 128, 1);
	font-size: 24px;
}
</style>
