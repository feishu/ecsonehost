<template>
  <div class="page">
    <tabsheader title="DC-质检员" @change="tabchange">
      <icon slot="right" :size="35" color="#383838" :icon="'\ue600'"/>
    </tabsheader>
    <am-picker
              title="DC-仓库"
              :data="cangku"
              v-model="cangkuval"
            >
              <am-list-item
                slot-scope="{ extra, show }"
                title="仓库"
                :extra="extra"
                @click="show"
              ></am-list-item>
            </am-picker>
    <scroller style="margin-top:20px;">
      <div>
        <div class="listheader">
          <text class="listheadertext">已选质检员</text>
          <icon slot="right" :size="35" color="#383838" :icon="'\ue600'"/>
        </div>
        <am-list>
          <am-list-checkbox
              v-for="(i,index) in chkdata" :key="index"
              title="ZJS10007-张三"
              :value="i"
              :checked-values.sync="value"
              :left='true'
              :color='themeColor'
          >
          </am-list-checkbox>
        </am-list>
      </div>
    </scroller>
    <div class="footer">
      <text class="tiptext">已选 0 人</text>
      <div class="row">
        <text class="btn1">删除</text>
        <text class="btn2">确定</text>
      </div>
    </div>
  </div>
</template>

<script>
import {
  AmList, AmListCheckbox, AmPicker, AmListItem,
} from 'weex-amui'
import icon from '../../companents/icon.vue'
import tabsheader from '../../companents/tabsheader.vue'
import { themeColor } from '../../utils/config'

// const dom = weex.requireModule('dom')

// const navigator = weex.requireModule('wb-navigator')
// const storage = weex.requireModule('storage')
export default {
  components: {
    tabsheader, icon, AmList, AmListCheckbox, AmPicker, AmListItem,
  },

  data() {
    return {
      isall: [],
      value: [],
      themeColor,
      cangkuval: '',
      cangku: [{
        label: '杭州厂房',
        value: '2013',
      }, {
        label: '杭州厂房',
        value: '2013',
      }, {
        label: '杭州厂房',
        value: '2013',
      }, {
        label: '杭州厂房',
        value: '2013',
      }, {
        label: '杭州厂房',
        value: '2013',
      }, {
        label: '杭州厂房',
        value: '2013',
      }],
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
.footer{
  background-color: #f4f4f4;
  height: 92px;
  padding: 0 26px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
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
.btn1{
  width: 260px;
  height: 64px;
  background-color: #a6a6a6;
  color: white;
  font-size: 32px;
  text-align: center;
  line-height: 64px;
  border-top-left-radius: 60px;
  border-bottom-left-radius: 60px;
}
.btn2{
  width: 260px;
  height: 64px;
  background-color: $themeColor;
  color: white;
  font-size: 32px;
  text-align: center;
  line-height: 64px;
  border-top-right-radius: 60px;
  border-bottom-right-radius: 60px;
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
</style>
