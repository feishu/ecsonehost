<template>
  <div class="page">
    <tabsheader title="DC-质检员" @change="tabchange">
      <icon slot="right" :size="35" color="#383838" :icon="'\ue600'"/>
    </tabsheader>
    <scroller style="margin-top:20px;">
      <div>
        <am-list>
          <am-list-checkbox
              v-for="(i,index) in chkdata" :key="index"
              :title="i.id"
              :value="i"
              :checked-values.sync="value"
              :left='true'
              :color='themeColor'
              arrow='right'
          >
          <text slot="extra" class="righttext">{{i.count}}个质检员</text>
          </am-list-checkbox>
        </am-list>
      </div>
    </scroller>
    <am-list-checkbox
              style="margin-top:20px;"
              title="全选"
              :value="true"
              :checked-values.sync="isall"
              :left='true'
              :color='themeColor'
          >
          <div slot="extra" class="extrabtns"><text class="righttext">已选 {{value.length}} 仓库</text>
          <button class="delbtn">
            <text class="delbtntext">删除</text>
            </button>
            </div>
    </am-list-checkbox>
  </div>
</template>

<script>
 import { AmList, AmListCheckbox } from 'weex-amui'
import icon from '../../companents/icon.vue'
import tabsheader from '../../companents/tabsheader.vue'
import { themeColor } from '../../utils/config'

// const dom = weex.requireModule('dom')

// const navigator = weex.requireModule('wb-navigator')
// const storage = weex.requireModule('storage')
export default {
   components: {
     tabsheader, icon, AmList, AmListCheckbox,
   },

   data() {
     return {
       isall: [],
       value: [],
       themeColor,
       chkdata: [
         {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         }, {
           id: '0573',
           count: 5,
           ischk: false,
         },
       ],
     }
   },
   created() {
     // this.statusheight = navigator.getHeight()
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
   watch: {
     isall() {
       if (this.isall[0]) this.value = this.chkdata
       else {
         this.value = []
       }
     },
   },
   methods: {},

}
</script>

<style lang="scss" scoped>
@import "../../style/global";
.page {
  background-color: #f4f4f4;
}
.extrabtns{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content:flex-end;
}
.righttext{
  line-height: 45px;
	color: rgba(166, 166, 166, 1);
	font-size: 32px;
}
.delbtn{
  width: 180px;
  height: 60px;
  background-color: $themeColor;
  border-radius: 55px;
  text-align: center;
  margin-left: 10px;
}
.delbtntext{
  color: white;
  text-align: center;
  font-size: 28px;
  line-height: 60px;
  
}
</style>
