<template>
  <div class="page">
    <div class="header" :style="{paddingTop:`${statusheight+30}px`}">
      <input placeholder="搜索备件内容" class="searchinput"/>
      <text class="cancelbtn">取消</text>
    </div>
    <div class="content">
      <div class="tablist">
        <text class="tab" @click="tabchange(index1)" :style="selecttab===index1?tabstyle.active:tabstyle.normal" v-for="(tab,index1) in tabdata" :key="index1">{{tab.title}}</text>
      </div>
      <scroller class="tabcontent" ref="tabcontent">
        <div class="contentitem-wrap" :ref="'tab'+index1" v-for="(tab,index1) in tabdata" :key="index1" :style="{borderBottomWidth:index1==tabdata.length-1?'1px':'0px'}">
          <div class="contentitem" v-for="(item,index2) in tab.list" :key="index2">
            <div class="betweenrow">
              <text class="title-text">{{item.id}}</text>
              <text class="tip-text">库存 {{item.count}}</text>
            </div>
              <text class="title-text">{{item.name}}</text>
            <div class="betweenrow" style="margin-top:20px;">
              <text class="tip-text">自有备件</text>
              <icon :icon="'\ue690'" :size="40" :color="themeColor"/>
            </div>
          </div>
            <text class="tabname">{{tab.title}}</text>
        </div>
      </scroller>
    </div>
    <div class="bottombar betweenrow">
      <div class="hasbox" @click="popshow=!popshow">
              <icon @iclick='popshow=!popshow' :icon="'\ue640'" :size="40" :color="themeColor"/>
              <text class="hasselecttext">已选</text>
              <text class="hasselectcounttext">2</text>
      </div>
      <text class="okbtn">选好了</text>
    </div>
    <div class="shadowbox" @click="popshow=false" v-if="popshow"></div>

    <div class="popview" v-if="popshow">

      <text class="info-title-s">共2件备件</text>
                <scroller style="margin-top:25px;">
                    <div class="beijianitem" v-for="i in 3" :key="i">
                        <div class="row">
                            <text class="info-title-s">150000001</text>
                            <text class="info-title-s red">自有备件</text>
                        </div>
                        <div class="row">
                            <text class="info-title">TISCAC-24V电子镇流器通用</text>
                            <div class="counter">
                                <icon :color="themeColor" :size="40" :icon="'\ue692'"/>
                                <text class="info-title red" style="width:30px;text-align:center;">1</text>
                                <icon :color="themeColor" :size="40" :icon="'\ue690'"/>
                            </div>
                        </div>
                    </div>
                </scroller>
    </div>
  </div>
</template>

<script>

import {
  AmPopup,
} from 'weex-amui'
import icon from '../../companents/icon.vue'
// import native, { router } from '../../utils/native'

import { themeColor } from '../../utils/config'

const dom = weex.requireModule('dom')
const modal = weex.requireModule('wb-modal')

const navigator = weex.requireModule('wb-navigator')
// const storage = weex.requireModule('storage')
export default {
  components: {
    icon, AmPopup,
  },

  data() {
    return {
      popshow: false,
      statusheight: 0,
      themeColor,
      selecttab: 0,
      tabstyle: {
        normal: {
          color: '#505050',
          backgroundColor: '#f8f8f8',
          fontSize: '28px',
          fontWeight: '400',
          borderLeftColor: '#f8f8f8',
        },
        active: {
          color: '#383838',
          backgroundColor: '#ffffff',
          fontSize: '28px',
          fontWeight: 'bold',
          borderLeftColor: themeColor,
        },
      },
      tabdata: [
        {
          title: '冰柜',
          list: [
            {
              id: '150000001',
              name: 'TISCAC-24V电子镇流器通用',
              count: 5,
            }, {
              id: '150000001',
              name: 'TISCAC-24V电子镇流器通用',
              count: 5,
            }, {
              id: '150000001',
              name: 'TISCAC-24V电子镇流器通用',
              count: 5,
            },
          ],
        }, {
          title: '展示柜',
          list: [
            {
              id: '150000001',
              name: 'TISCAC-24V电子镇流器通用',
              count: 5,
            }, {
              id: '150000001',
              name: 'TISCAC-24V电子镇流器通用',
              count: 5,
            }, {
              id: '150000001',
              name: 'TISCAC-24V电子镇流器通用',
              count: 5,
            },
          ],
        }, {
          title: '现调机',
          list: [
            {
              id: '150000001',
              name: 'TISCAC-24V电子镇流器通用',
              count: 5,
            }, {
              id: '150000001',
              name: 'TISCAC-24V电子镇流器通用',
              count: 5,
            }, {
              id: '150000001',
              name: 'TISCAC-24V电子镇流器通用',
              count: 5,
            },
          ],
        }, {
          title: '自贩机',
          list: [
            {
              id: '150000001',
              name: 'TISCAC-24V电子镇流器通用',
              count: 5,
            }, {
              id: '150000001',
              name: 'TISCAC-24V电子镇流器通用',
              count: 5,
            }, {
              id: '150000001',
              name: 'TISCAC-24V电子镇流器通用',
              count: 5,
            },
          ],
        },
      ],
    }
  },
  created() {
    this.statusheight = navigator.getHeight()
    // navigator.setCenterItem({
    //   text: '工单',
    //   color: '#ffffff',
    // }, () => {})
    // navigator.setLeftItems([{
    //   text: '',
    //   color: '#ffffff',
    // }], () => {})
    // navigator.setNavColor(themeColor)
  },
  methods: {
    tabchange(index) {
      this.selecttab = index
      dom.scrollToElement(this.$refs[`tab${index}`][0])
    },
    alert(msg) {
      modal.alert({
      // 标题
        title: 'Msg',
        // 弹窗内容
        message: msg,
        // 是否在view最上面一层打开
        root: false,
        // 确定按钮文字
        okTitle: '确定',
      }, () => {

      })
    },
  },
}
</script>

<style lang="scss" scoped>
@import "../../style/global";
.page {
  background-color: #f4f4f4;
}
.header{
  background-color: $themeColor;
   padding-left: 20px;
   padding-right:20px;
   padding-bottom:55px;
   display: flex;
   flex-direction: row;
   align-items: center;
}
.searchinput{
  flex: 1;
  color: #a6a6a6;
  background-color: white;
  text-align: center;
  height: 60px;
  line-height: 60px;
  border-radius: 60px;
  font-size: 28px;
}
.cancelbtn{
  color: white;
  font-size: 26px;
  margin-left: 15px;
}
.content{
  flex:1;
  display: flex;
  flex-direction: row;
  background-color: white;
}

.tablist{
  width: 180px;
  display: flex;
  flex-direction: column;
  justify-content: stretch;
}
.tab{
  height: 88px;
  line-height: 88px;
  text-align: center;
  border-left-width: 6px;
  border-bottom-color: #f2f2f2;
  border-bottom-width: 1px;
}
.tabcontent{
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: stretch;
}
.contentitem-wrap{
  padding: 18px 16px;
  position: relative;
  border-bottom-width: 1px;
  border-bottom-color: #f2f2f2;
}
.contentitem{
  border-top-width: 1px;
  border-top-color: #f2f2f2;
  padding-top: 40px;
  padding-left: 8px;
  padding-right: 50px;
  padding-bottom: 26px;
}
.tabname{
  position: absolute;
  left:0;
  top:0;
  background-color: white;
  height: 40px;
  line-height: 40px;
  padding-left: 8px ;
  font-size: 26px;
  padding-right: 68px;
}

.betweenrow{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.title-text{
  height: 44px;
	line-height: 44px;
	color: rgba(56, 56, 56, 1);
	font-size: 30px;
}
.tip-text{
  height: 28px;
  line-height: 28px;
	color: rgba(166, 166, 166, 1);
	font-size: 24px;
}
.bottombar{
  height: 90px;
  background-color: #f4f4f4;
  padding: 0 30px;
}

.hasbox{
  position: relative;
  display: flex;
  flex-direction: column;
  padding: 0 44px;
  align-items: center;
  justify-content: center;
}
.hasselecttext{
  font-size: 24px;
  color: $themeColor;
}
.hasselectcounttext{
  font-size: 22px;
  color: white;
  background-color: $themeColor;
  position: absolute;
  right:0;
  top:0;
  padding: 0 15px;
  height: 28px;
  line-height: 28px;
  border-radius: 28px;
}
.okbtn{
  width: 180px;
  height: 64px;
  background-color: $themeColor;
  color: white;
  border-radius: 64px;
  line-height: 64px;
  text-align: center;
  font-size: 28px;
}


.row{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-bottom: 10px;
    align-items: center;
}
.info-title{
    line-height: 42px;
	color: rgba(128, 128, 128, 1);
	font-size: 28px;
}
.info-title-s{
    line-height: 36px;
	color: rgba(128, 128, 128, 1);
	font-size: 22px;
}

.red{
    color: $themeColor;
}
.counter{
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    align-items: center;
}
.beijianitem{
    border-bottom-width: 1px; 
    border-bottom-color: #f4f4f4;
    margin-bottom: 8px;
}
.popview{
  position: fixed;
  bottom: 90px;
  padding: 20px;
  left: 0;
  right: 0;
  max-height: 500px;
  background-color: white;
}
.shadowbox{
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 90px;
  background-color: rgba(0, 0, 0, 0.1);
}
</style>
