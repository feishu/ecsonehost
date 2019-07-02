<template>
    <div>
    <div class="bgheader" ></div>

    <scroller class="content">
      <div class="info-bars">
        <div class="info-item">
          <text class="info-item-num">10</text>
          <text class="info-item-title">今日新增</text>
        </div>
        <div class="info-item">
          <text class="info-item-num">10</text>
          <text class="info-item-title">今日完成</text>
        </div>
        <div class="info-item">
          <text class="info-item-num">10</text>
          <text class="info-item-title">历史未完</text>
        </div>
        <div class="info-item">
          <text class="info-item-num">10</text>
          <text class="info-item-title">超时工单</text>
        </div>
      </div>
      <div class="class-tabs">
        <tag class="tagitem" active='true' content="全部"/>
        <tag class="tagitem" content="全部"/>
        <tag  class="tagitem" content="全部"/>
      </div>
      <fixorder @eclick="orderdetail('dd')"/>
      <fixorder @eclick="orderdetail('xd')"/>
    </scroller>
    </div>
</template>
<script>
import { AmTabBar, AmTabBarItem } from 'weex-amui'
import tag from '../../companents/tag.vue'
import fixorder from '../../companents/fixorder.vue'
import { themeColor } from '../../utils/config'
import { router } from '../../utils/native'

const navigator = weex.requireModule('wb-navigator')
export default {
  components: {
    AmTabBar, AmTabBarItem, tag, fixorder,
  },
  data() {
    return {
      a: '1124',
      themeColor,
    }
  },
  created() {
    navigator.setCenterItem({
      text: '工单',
      color: '#ffffff',
    }, () => {})
    navigator.setLeftItems([{
      text: '',
      color: '#ffffff',
    }], () => {})
    navigator.setNavColor(themeColor)
  },
  methods: {
    orderdetail(mode) {
      router.open({
        url: 'page/orderdetail.js',
        navBarHidden: true,
        params: {
          mode,
        },
      })
    },
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
.class-tabs{
  display: flex;
  flex-direction:row ;
  background-color: white;
  padding: 20px 16px;
}
.class-tabs .tagitem{
  margin-right: 16px;
}
</style>
