<template>
    <div class="page">
        <tabsheader @change="tabchange"></tabsheader>
        <fixorder :detail='false'></fixorder>
        <scroller>
        <titleblock title="工单信息">
            <div>
                <div class="row">
                    <text class="info-title">资产编号</text>
                    <text class="info-content">140GA47110006</text>
                </div>
                <div class="row">
                    <text class="info-title">报修时间</text>
                    <text class="info-content">2019-04-08 09:46</text>
                </div>
                <div class="row">
                    <text class="info-title">计划完修时间</text>
                    <text class="info-content">2019-04-08 12:30</text>
                </div>
                <text class="info-title">故障描述</text>
                <text class="info-content">开不了机了开不了机了开不了机了开不机了开不了机了开不了机了开不了机了开不</text>
            </div>
        </titleblock>
        <titleblock title="工单处理">
            <div>
                <text class="info-title">故障原因</text>
                <div style="margin-top:25px;min-height:200px">
                    <text v-for="i in 20" :key="i" class="info-content">{{i}}.电源坏了</text>
                </div>
                <div class='contentbar'>
                    <icon color="#999999" :size="35" :icon="'\ue600'"/>
                    <icon style="margin-top:30px" color="#999999" :size="30" :icon="'\ue646'"/> 
                </div>
                
            </div>
        </titleblock>
        <titleblock title="备件信息" ref="beijian">
            <div>
                <text class="info-title-s">共2件备件</text>
                <scroller style="margin-top:25px;height:200px">
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
                <div class='contentbar'>
                    <icon color="#999999" :size="35" :icon="'\ue600'"/>
                </div>
                
            </div>
        </titleblock>
        </scroller>
        <div class="btngroup" v-if="mode=='dd'">
            <button class="btn1" @click="pickperson"><text class="btntext">转派</text></button>
            <button class="btn2" @click="revertaction"><text class='btntext'>到达</text></button>
        </div>
        <div class="btngroup" v-if="mode=='xd'">
            <button class="btn3"><text class="btntext">销单</text></button>
            <icon :color="themeColor" :size="50" :icon="'\ue60b'"/>
        </div>
        <am-popup
            :style="{marginBottom:mgbottom}"
            :show.sync="popshow"
            position="bottom"
            height="880"
        >   
            <div class="header">
                <text class="title">到达处理</text>
                <icon @iclick="popshow=false" class="icondiv" color="#999999" :size="35" :icon="'\ue60e'"/>
            </div>
            <scroller class="content">
                <text class="info-title">到达情况反馈</text>
                <div class="selector-box">
                    <text class="selector-item" @click="arriveform.replytype=0" :style="arriveform.replytype==0?active:inactive">维修到达</text>
                    <text class="selector-item" @click="arriveform.replytype=1" :style="arriveform.replytype==1?active:inactive">备件不足</text>
                    <text class="selector-item" @click="arriveform.replytype=2" :style="arriveform.replytype==2?active:inactive">客户改期</text>
                    <text class="selector-item" @click="arriveform.replytype=3" :style="arriveform.replytype==3?active:inactive">路途遥远</text>
                </div>
                <div class="row pickTime"  @click="pickTime">
                    <text class="info-title">到达时间</text>
                    <div class="rowright">
                        <text class="info-title" style="color:#a6a6a6;margin-right:5px;">{{arriveform.arrivetime||'请选择'}}</text>
                        <icon color="#999999" :size="30" :icon="'\ue68b'" />
                    </div>
                </div>
                <text class="info-title">备注</text>
                <textarea @blur="mgbottom='0px'" class="note" @keyboard='textinputwindow' v-model="arriveform.note" rows='5'></textarea>
                <!-- <am-list :noBorder="true"> -->
                    <!-- <am-date-picker
                        title="到达时间"
                        v-model="arriveform.arrivetime"
                    >
                        <div
                            slot-scope="{extra, show}"
                            @click="show"
                        >
                            {{arriveform.arrivetime}}
                        </div> -->
                        <!-- <am-list-item
                        
                        title="到达时间"
                        :extra="extra"
                        @click="show"
                        ></am-list-item> -->
                    <!-- </am-date-picker> -->
                <!-- </am-list> -->
                <div class="photosbox">
                    <image class="photoitem" v-for="(img,index) in arriveform.imgs" :key="index" :src='img'>
                    </image>
                    <div class="photoitem" @click="takephoto">
                        <icon @iclick="takephoto" size="70" color="#999999" :icon="'\ue6f9'"/>
                        <text class="info-title-s">拍照</text>
                    </div>
                </div>
            </scroller>
            <button class="submitbtn">
                <text class="submitbtntext">提交</text>
            </button>
        </am-popup>
    </div>
</template>
<script>
import {
  AmPopup, AmDatePicker, AmListItem, AmList,
} from 'weex-amui'
import icon from '../../companents/icon.vue'
import fixorder from '../../companents/fixorder.vue'
import tabsheader from '../../companents/tabsheader.vue'
import titleblock from '../../companents/titleblock.vue'
// import { router } from '../../utils/native'
import { themeColor } from '../../utils/config'

const modal = weex.requireModule('wb-modal')
const external = weex.requireModule('wb-external')
const dom = weex.requireModule('dom')
const picker = weex.requireModule('picker')
const navigator = weex.requireModule('wb-navigator')
export default {
  components: {
    fixorder,
    icon,
    tabsheader,
    titleblock,
    AmPopup,
    AmDatePicker,
    AmListItem,
    AmList,
  },

  data() {
    return {
      popposition: 'bottom',
      arriveform: {
        replytype: 0,
        arrivetime: null,
        note: '',
        imgs: [],
      },
      popshow: false,
      statusheight: 0,
      themeColor,
      beijian: null,
      mode: 'dd', // xd
      active: {
        borderColor: themeColor,
        color: '#ffffff',
        backgroundColor: themeColor,
      },
      inactive: {
        borderColor: '#a6a6a6',
        color: '#808080',
        backgroundColor: 'white',
      },
      mgbottom: '0px',
    }
  },
  created() {
    this.statusheight = navigator.getHeight()
    // const params = router.getParams()
    // this.mode = params.mode || 'xd'
  },
  methods: {
    toast(info = '') {
      modal.showToast({
        text: info,
        duration: 2,
      })
    },
    pickTime() {
      picker.pickTime({

      }, (event) => {
        console.log(event)
        if (event.result === 'success') {
          this.arriveform.arrivetime = event.data
        }
      })
    },
    tobeijian() {
      dom.scrollToElement(this.$refs.beijian)
    },
    tabchange(e) {
      if (parseInt(e, 10) === 1) {
        this.tobeijian()
      }
    },
    textinputwindow({ isShow, keyboardSize }) {
    //   const _keyboardSize = parseInt(keyboardSize, 10)

      if (isShow && this.mgbottom === '0px') this.mgbottom = keyboardSize
      else this.mgbottom = '0px'
    },
    pickperson() {
      picker.pick({
        items: ['Apple', 'Banana', 'Orange'],
      }, (event) => {
        if (event.result === 'success') {
          this.value = event.data
        }
      })
    },
    revertaction() {
      modal.prompt({
        title: '输入驳回理由',
        cancelTitle: '取消',
        okTitle: '确定',
      }, (result) => {
        const { status } = result
        if (status < 0) return
        modal.confirm({
          title: '消息',
          message: '确定要驳回吗？',
          cancelTitle: '取消',
          okTitle: '确定',
        }, () => {

        })
      })
    },
    takephoto() {
      picker.pick({
        items: ['相册', '拍照'],
      }, (event) => {
        if (event.result === 'success') {
          if (parseInt(event.data, 10) === 0) {
            external.openPhoto({
              // 最大选取张数
              count: 9,
              root: false,
              width: 1920,
              height: 1080,
            }, (result) => {
              this.arriveform.imgs = this.arriveform.imgs.concat(result.data.urls)
            })
          } else {
            external.openCamera({
              // 是否在view最上面一层打开
              root: false,
              width: 1920,
              height: 1080,
            }, (result) => {
              const { urls } = result.data
              this.arriveform.imgs.push(urls[0])
            })
          }
        }
      })
    },
  },
}
</script>
<style lang="scss" scoped>
@import '../../style/global';

.page{
    background-color: #f4f4f4;
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
.info-content{
    line-height: 42px;
	color: rgba(56, 56, 56, 1);
	font-size: 28px;
}
.contentbar{
    position: absolute;
    right: 0;
    top: 0;
    display: flex;
    flex-direction: column;
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
.btngroup{
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    margin: 16px 25px;
    align-items: center;
}
.btn1{
    height: 64px;
    line-height: 64px;
    width: 180px;
    text-align: center;
    background-color: #ff8000;
    color: white !important;
    border-top-left-radius: 64px;
    border-bottom-left-radius: 64px;
}
.btn2{
    height: 64px;
    width: 180px;
    text-align: center;
    background-color: #ff5733;
    border-top-right-radius: 64px;
    text-align: center;
    border-bottom-right-radius: 64px;
}
.btn3{
    height: 64px;
    text-align: center;
    background-color: $themeColor;
    border-radius: 64px;
    text-align: center;
    flex:1;
    margin: 0 30px;
}
.btntext{
    color: white;
    line-height: 64px;
    font-size: 28px;
    text-align: center;
} 
.header{
    height: 95px;
    background-color: white;
    border-bottom-width: 2px;
    border-bottom-color: #f4f4f4;
    justify-content: center;
    align-items: center;
    position: relative;
}
.title{
    line-height: 95px;
	color: rgba(80, 80, 80, 1);
	font-size: 30px;
}
.icondiv{
    position: absolute;
    right:20px;

}
.content{
    background-color: white;
    padding: 20px;
}
.selector-box{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items: center;
    justify-content: flex-start;
}
.selector-item{
    width: 160px;
	  height: 60px;
    line-height: 60px;
	  color: #808080;
	  border-radius: 30px;
	  font-size: 28px;
    border-color: #a6a6a6;
    border-width: 2px;
    text-align: center;
    margin-right: 15px;
    margin-top: 10px;
    margin-bottom: 10px;

}
.pickTime{
    height: 90px;
    margin: 10px 0;
}
.rowright{
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    align-items: center;
    padding-right: 20px;
}
.note{
    width: 720px;
    padding: 10px;
    margin-top:10px;
	color: #333;
    border-width: 1px;
    border-color: rgba(229, 229, 229, 1);
	border-radius: 4px;
	font-size: 28px;
}
.photosbox{
    margin-top: 20px;
    display:flex;
    flex-direction: row;
    align-items: center;
    justify-content: flex-start;
    flex-wrap: wrap;
}
.photoitem{
    width: 120px;
    height: 120px;
    margin-right: 15px;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}
.photoimg{
    width: 80px;
    height: 80px;
}
.submitbtn{
    width: 640px;
    height: 64px;
    border-radius: 50px;
    align-self: center;
    align-items: center;
    justify-content: center;
    background-color: $themeColor;
    margin-bottom: 20px;
}
.submitbtntext{
    color: white;
    font-size: 28px;
    text-align: center;
}
</style>
