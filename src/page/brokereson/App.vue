<template>
  <div class="page">
    <tabsheader title="故障原因">
    </tabsheader>
    <scroller style="margin-top:1px;">
      <div>
        <titleblock title="工单处理">
          <div class="selector-box">
            <text class="selector-item" @click="selected=index" v-for="(item,index) in selectdata" :style="selected===index?active:inactive" :key="index">{{item}}</text>
          </div>
        </titleblock>
        <titleblock title="模块">
          <div class="selector-box">
            <text class="selector-item" @click="item.ischecked=!item.ischecked" v-for="(item,index) in checkdata" :style="item.ischecked?active:inactive" :key="index">{{item.title}}</text>
          </div>
        </titleblock>
        <titleblock title="原因">
            <div class="checkboxline" @click="r.ischecked=!r.ischecked" v-for="(r,i) in resons" :key="i">
            <text class="checkboxtext">{{r.title}}</text>
            <am-checkbox :checked='r.ischecked' :color="themeColor"></am-checkbox>
          </div>
        </titleblock>
      </div>
    </scroller>
    <div class="footer">
        <text class="btn2">添加故障</text>
    </div>
  </div>
</template>

<script>
import {
  AmList, AmCheckbox, AmPicker, AmListItem,
} from 'weex-amui'
import titleblock from '../../companents/titleblock.vue'

import icon from '../../companents/icon.vue'
import tabsheader from '../../companents/tabsheader.vue'
import { themeColor } from '../../utils/config'

const modal = weex.requireModule('wb-modal')
// const dom = weex.requireModule('dom')

// const navigator = weex.requireModule('wb-navigator')
// const storage = weex.requireModule('storage')
export default {
  components: {
    tabsheader, icon, AmList, AmCheckbox, AmPicker, AmListItem, titleblock,
  },

  data() {
    return {
      checked: [],
      resoncheck: [],
      themeColor,
      selectdata: [
        '展示柜', '冰柜', '现调机', '自贩机', '非真正故障',
      ],
      checkdata: [
        { title: '电器控制部分', ischecked: false },
        { title: '电器控制部分', ischecked: false },
        { title: '电器控制部分', ischecked: false },
        { title: '电器控制部分', ischecked: false },
        { title: '电器控制部分', ischecked: false },
        { title: '电器控制部分', ischecked: false },
      ],
      resons: [
        { title: '展示柜电源线破块、插头损坏，设备不通电', ischecked: false },
        { title: '展示柜电源线破块、插头损坏，设备不通电', ischecked: false },
        { title: '展示柜电源线破块、插头损坏，设备不通电', ischecked: false },
        { title: '展示柜电源线破块、插头损坏，设备不通电', ischecked: false },
        { title: '展示柜电源线破块、插头损坏，设备不通电', ischecked: false },
        { title: '展示柜电源线破块、插头损坏，设备不通电', ischecked: false },
      ],
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
  created() {

  },
  watch: {

  },
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
  },

}
</script>

<style lang="scss" scoped>
@import "../../style/global";
.page {
  background-color: #ffffff;
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
.footer{
  height: 92px;
  padding: 0 26px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.tiptext{
  color: #505050;
  font-size: 28px;
}
.row{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}

.btn2{
  width: 640px;
  height: 64px;
  background-color: $themeColor;
  color: white;
  font-size: 32px;
  text-align: center;
  line-height: 64px;
  border-radius: 60px;
}
.listheader{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding: 14px 24px;
}
.listheadertext{
  color: rgba(80, 80, 80, 1);
	font-size: 28px;
}
.checkboxline{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  padding: 9px 60px;
}
.checkboxtext{
  width: 520px;
  color: rgba(80, 80, 80, 1);
  font-size: 28px;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-right:40px;
}
</style>
