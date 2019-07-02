<template>
  <div class="page">
    <tabsheader title="备件申请"></tabsheader>
    <scroller style="margin-top:1px;">
      <div class="row" style="margin-top:20px; ">
        <text class="title">公司名称：</text>
        <text class="subtitle">杭州厂房 3006</text>
      </div>
      <div class="row">
        <text class="title">公司名称：</text>
        <text class="subtitle">2019-04-16 10:36:34</text>
      </div>
      
      <titleblock color="#383838" title="类型">
        <div class="selector-box">
          <text
            class="selector-item"
            @click="selected=index"
            v-for="(item,index) in selectdata"
            :style="selected===index?active:inactive"
            :key="index"
          >{{item}}</text>
        </div>
      </titleblock>
      <div v-if="selected==0" class="row" @click="pickroom">
        <text class="title">选择仓库 </text>
        <text v-if="cangku" class="subtitle">{{cangku}}</text>
        <icon v-else @iclick="pickroom" style="margin-top:-8px" :size="28" color="#999999" :icon="'\ue674'"/>
      </div>
      <div v-if="selected==1" class="row" @click="pickperson">
        <text class="title">选择人员 </text>
        <text v-if="person" class="subtitle">{{person}}</text>
        <icon v-else @iclick="pickperson" style="margin-top:-8px" :size="28" color="#999999" :icon="'\ue674'"/>
      </div>
      <backdevicelist title="备件列表"></backdevicelist>
    </scroller>
    <div class="footer">
      <text class="btn2">提交申请</text>
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
  background-color: #ffffff;
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

.btn2 {
  width: 640px;
  height: 64px;
  background-color: $themeColor;
  color: white;
  font-size: 32px;
  text-align: center;
  line-height: 64px;
  border-radius: 60px;
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
</style>
