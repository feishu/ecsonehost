<template>
  <div class="page">
    <tabsheader title="备件审批"></tabsheader>
    <scroller style="margin-top:1px;">
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
                <text class="content-title">公司名称:</text>
                <text class="content-text">140GA47110006</text>
            </div>
            <div class="content-row">
                <text class="content-title">类型:</text>
                <text class="content-text">调用</text>
            </div>
            <div class="content-row">
                <text class="content-title">用途:</text>
                <text class="content-text">市场</text>
            </div>
            <div class="content-row">
                <text class="content-title">申请人:</text>
                <text class="content-text">XMS10018  黄文强</text>
            </div>
            <div class="content-row">
                <text class="content-title">审批人:</text>
                <text class="content-text">XMS20003  毛善容</text>
            </div>
            <div class="content-row">
                <text class="content-title">预留号:</text>
                <text class="content-text">无</text>
            </div>
            <div class="content-row">
                <text class="content-title">申请时间:</text>
                <text class="content-text">2019-04-16 10:36:34</text>
            </div>
            <div class="content-row">
                <text class="content-title">反馈信息:</text>
                <text class="content-text">无</text>
            </div>
        </div> 
    </div>
      <backdevicelist :readonly="true" color='#808080' title="备件信息"></backdevicelist>
    </scroller>
    <div class="btngroup" >
            <button class="btn1" @click="pickperson"><text class="btntext">转派</text></button>
            <button class="btn2" @click="revertaction"><text class='btntext'>到达</text></button>
    </div>
  </div>
</template>

<script>
import {
  AmList, AmCheckbox, AmPicker, AmListItem,
} from 'weex-amui'
import titleblock from '../../companents/titleblock.vue'
import backdevicelist from '../../companents/backdevicelist.vue'
import icon from '../../companents/icon.vue'
import tabsheader from '../../companents/tabsheader.vue'
import { themeColor } from '../../utils/config'

const picker = weex.requireModule('picker')
const modal = weex.requireModule('wb-modal')
// const dom = weex.requireModule('dom')

// const navigator = weex.requireModule('wb-navigator')
// const storage = weex.requireModule('storage')
export default {
  components: {
    tabsheader,
    icon,
    AmList,
    AmCheckbox,
    AmPicker,
    AmListItem,
    titleblock,
    backdevicelist,
  },

  data() {
    return {
      person: '',
      cangku: '',
      checked: [],
      resoncheck: [],
      themeColor,
      selectdata: ['领用', '调用'],

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
      selected: 0,
    }
  },
  created() {},
  watch: {},
  methods: {
    toast(info = '') {
      modal.showToast({
        text: info,
        duration: 2,
      })
    },
    check(index) {
      this.toast(`check${index}`)
      if (this.checked[index] === index) this.checked[index] = null
      else this.checked[index] = index
      this.toast(this.checked.join(','))
    },
    ischecked(index) {
      this.toast(this.checked.join(','))

      if (this.checked[index] === index) return this.active
      return this.inactive
    },
    pickroom() {
      const datasource = ['0683', '0683', '0683', '0683', '0683', '0683', '0683']
      picker.pick({
        items: datasource,
        title: '仓库选择',
      }, (event) => {
        if (event.result === 'success') {
          this.cangku = datasource[event.data]
        }
      })
    },
    pickperson() {
      const datasource = ['HZS100003 - 王信东', 'HZS100003 - 王信东', 'HZS100003 - 王信东', 'HZS100003 - 王信东', 'HZS100003 - 王信东']
      picker.pick({
        items: datasource,
        title: '选择人员',
      }, (event) => {
        if (event.result === 'success') {
          this.person = datasource[event.data]
        }
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
.extrabtns {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-end;
}
.righttext {
  line-height: 45px;
  color: rgba(166, 166, 166, 1);
  font-size: 32px;
}
.delbtn {
  width: 180px;
  height: 60px;
  background-color: $themeColor;
  border-radius: 55px;
  text-align: center;
  margin-left: 10px;
}
.delbtntext {
  color: white;
  text-align: center;
  font-size: 28px;
  line-height: 60px;
}
.footer {
  height: 92px;
  padding: 0 26px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.tiptext {
  color: #505050;
  font-size: 28px;
}
.row {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
}
.title {
  height: 42px;
  color: #383838;
  font-size: 28px;
  padding-left: 20px;
}
.subtitle{
  height: 42px;
	color: rgba(128, 128, 128, 1);
	font-size: 28px;
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
.listheader {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding: 14px 24px;
}
.listheadertext {
  color: rgba(80, 80, 80, 1);
  font-size: 28px;
}

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
    justify-content: space-between;
    align-items: center;
}
</style>
