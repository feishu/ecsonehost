<template>
  <div class="page">
    <tabsheader title="创建工单"></tabsheader>
    <am-tabs
        v-model="selected"
        :tab-width="160"
        :tabs="tabs"
      />
      <am-native-tab-container :index.sync="selected">
      <am-native-tab-container-item>
        <scroller style="margin-top:20px;">
          <titleblock bgcolor='#fff' color="#333" v-for="(yj,i) in yujian" :title="yj.title" :key="i">
            <div class="checkitemslist">
              <text class="checkitem" @click="check(i+item.title+index)" v-for="(item,index) in yj.datas" :key="index" :style="{borderColor:(val.indexOf(i+item.title+index)>-1)?themeColor:'#f1f0f1',color:item.env===1?'#5f646e':(item.env===2?themeColor:'#ff8d1a')}">{{item.title}}</text>
            </div>
          </titleblock>
          <div class="textaera">
            <textarea class="note" rows="6" placeholder="备注"/>
          </div>
        </scroller>
        <text v-if="mode=='new'" @click="selected++" class="largebtn">下一步</text>
        <text v-if="mode=='renew'" class="largebtn">分配</text>
      </am-native-tab-container-item>
      <am-native-tab-container-item>
        <scroller style="margin-top:20px;">
          <am-list>
            <am-list-item
              arrow='empty'
              title="工单类型"
              extra="ZC15:整新工单"/>
              <am-list-item
              arrow='empty'
              title="资产编号"
              extra="140GA47110006"/>
              <am-list-item
              arrow='empty'
              title="设备大类"
              extra="展示柜"/>
              <am-list-item
              arrow='empty'
              title="设备型号"
              extra="140GA47110006/三电CMW-960D"/>
              <am-list-item
              arrow='right'
              title="翻新等级"
              extra="二类翻新"/>
              <am-list-item
              arrow='right'
              title="作业类型"
              extra="W33-三门设备"/>
              <am-list-item
              arrow='empty'
              title="设备购买日期"
              extra="2011-05-17"/>
              <am-list-item
              arrow='empty'
              title="设备机龄(月)"
              extra="87"/>
              <am-list-item
              arrow='empty'
              title="门数"
              extra="2"/>
          </am-list>
          <am-list style="margin-top:20px;">
              <am-list-item
              arrow='right'
              title="维修员"
              extra="ZJR20010-李铭"/>
              <am-list-item
              arrow='right'
              title="计划开始时间"
              extra="2018-04-17"/>
              <am-list-item
              arrow='right'
              title="计划结束时间"
              extra="2018-04-18"/>
          </am-list>
        </scroller>
        <text v-if="mode=='new'" @click="selected++" class="largebtn">下一步</text>
        <text v-if="mode=='renew'" class="largebtn">分配</text>
      </am-native-tab-container-item>
      <am-native-tab-container-item>
        <scroller style="flex:1">
          <backdevicelist style="margin-top:-50px;"/>
        </scroller>
        <text v-if="mode=='new'" @click="selected++" class="largebtn">下一步</text>
        <text v-if="mode=='renew'" class="largebtn">分配</text>
      </am-native-tab-container-item>
      <am-native-tab-container-item>
        <scroller style="margin-top:20px;">
          <div class="photolist">
            <div class="photoitem">
              <text class="photoname">整机外观</text>
              <div class="img" @click="takephoto('waiguan')">
                 <image v-if="photos['waiguan']" class="img" :src="photos['waiguan']"/>
                <icon v-else @iclick="takephoto('waiguan')" size="70" color="#999999" :icon="'\ue6f9'"/>
              </div>
            </div>
            <div class="photoitem">
              <text class="photoname">内箱</text>
               <div class="img" @click="takephoto('neixiang')">
                  <image v-if="photos['neixiang']" class="img" :src="photos['neixiang']"/>
                <icon v-else @iclick="takephoto('neixiang')" size="70" color="#999999" :icon="'\ue6f9'"/>
              </div>
            </div>
            <div class="photoitem">
              <text class="photoname">机组</text>
               <div class="img" @click="takephoto('jizu')">
                 <image v-if="photos['jizu']" class="img" :src="photos['jizu']"/>
                <icon v-else @iclick="takephoto('jizu')" size="70" color="#999999" :icon="'\ue6f9'"/>
              </div>
            </div>
          </div>
          <div class="photolist" style="margin-top:20px;">
            <div class="photoitem">
              <text class="photoname">其它</text>
              <div class="img" @click="takephoto('qita1')">
                 <image v-if="photos['qita1']" class="img" :src="photos['qita1']"/>
                <icon @iclick="takephoto('qita1')" size="70" color="#999999" :icon="'\ue6f9'"/>
              </div>
            </div>
            <div class="photoitem">
              <text class="photoname"> </text>
               <div class="img" @click="takephoto('qita2')">
                 <image v-if="photos['qita2']" class="img" :src="photos['qita2']"/>
                <icon @iclick="takephoto('qita2')" size="70" color="#999999" :icon="'\ue6f9'"/>
              </div>
            </div>
            <div class="photoitem">
              <text class="photoname"> </text>
               <div class="img" @click="takephoto('qita3')">
                 <image v-if="photos['qita3']" class="img" :src="photos['qita3']"/>
                <icon @iclick="takephoto('qita3')" size="70" color="#999999" :icon="'\ue6f9'"/>
              </div>
            </div>
          </div>
        </scroller>
        <text v-if="mode=='new'" class="largebtn">提交</text>
        <text v-if="mode=='renew'" class="largebtn">分配</text>

      </am-native-tab-container-item>
    </am-native-tab-container>
    
  </div>
</template>

<script>
import {
  AmTabs, AmNativeTabContainer,
  AmNativeTabContainerItem, AmList, AmListItem,
} from 'weex-amui'
import icon from '../../companents/icon.vue'
import tabsheader from '../../companents/tabsheader.vue'
import { themeColor } from '../../utils/config'
import titleblock from '../../companents/titleblock.vue'
import backdevicelist from '../../companents/backdevicelist.vue'

const modal = weex.requireModule('wb-modal')
const external = weex.requireModule('wb-external')
const picker = weex.requireModule('picker')

export default {
  components: {
    tabsheader,
    icon,
    AmTabs,
    titleblock,
    AmNativeTabContainer,
    AmNativeTabContainerItem,
    AmList,
    AmListItem,
    backdevicelist,
  },

  data() {
    return {
      mode: 'renew',
      photos: {
        waiguan: '',
        neixiang: '',
        jizu: '',
        qita1: '',
        qita2: '',
        qita3: '',
      },
      val: [],
      selected: 0,
      themeColor,
      active: {
        borderColor: themeColor,
      },
      inactive: {
        borderColor: '#f1f0f1',
      },
      tabs: ['预检工作', '基础信息', '预计配件', '设备拍照'],
      yujian: [

      ],
    }
  },
  created() {
    for (let i = 0; i < 5; i += 1) {
      const data = []
      const tmp = [
        '灯箱',
        '箱体破损锈蚀',
        '电源线',
        '灯箱',
        '箱体破损锈蚀',
        '电源线',
        '灯箱',
        '箱体破损锈蚀',
        '电源线',
        '灯箱',
        '箱体破损锈蚀',
        '电源线',
        '灯箱',
        '箱体破损锈蚀',
        '电源线',
      ]
      tmp.forEach((item, ii) => {
        let env = 1
        if (ii % 3) {
          if (ii % 2) {
            env = 1
          } else {
            env = 2
          }
        } else {
          env = 3
        }
        data.push({
          title: item,
          ischeck: false,
          env,
        })
      })
      this.yujian.push({
        title: '外观',
        datas: data,
      })
    }
  },
  watch: {},
  methods: {
    takephoto(target) {
      picker.pick({
        items: ['相册', '拍照'],
      }, (event) => {
        if (event.result === 'success') {
          if (parseInt(event.data, 10) === 0) {
            external.openPhoto({
              // 最大选取张数
              count: 1,
              root: false,
              width: 1920,
              height: 1080,
            }, (result) => {
              const [url] = result.data.urls
              this.photos[target] = url
              modal.showToast({
                // 提示信息
                text: JSON.stringify(this.photos),
                // 显示时间，默认2秒
                duration: 2,
              })
            })
          } else {
            external.openCamera({
              // 是否在view最上面一层打开
              root: false,
              width: 1920,
              height: 1080,
            }, () => {
              modal.showToast({
                // 提示信息
                text: 'dsdasdsadsad',
                // 显示时间，默认2秒
                duration: 2,
              })
              // const { url } = result.data

              // this.photos[target] = url
            })
          }
        }
      })
    },
    check(val) {
      if (this.val.indexOf(val) > -1) {
        this.val.splice(this.val.indexOf(val), 1)
      } else this.val.push(val)
    },
  },
}
</script>

<style lang="scss" scoped>
@import "../../style/global";
.page {
  background-color: #f4f4f4;
}
.checkitemslist{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: center;
}
.checkitem{
  height: 60px;
  line-height: 60px;
  margin-bottom: 16px;
  font-size: 28px;
  color: #5f646e;
  min-width: 220px;
  text-align: center;
  border-width:1px;
  border-radius: 3px;
  border-color: #f1f0f1;
}
.textaera{
  background-color: white;
  padding: 0 24px;
}
.note{
   width: 702px;
    padding: 10px;
    margin-top:10px;
	color: #333;
    border-width: 1px;
    border-color: rgba(229, 229, 229, 1);
	border-radius: 4px;
  font-size: 28px;
  margin-bottom: 30px;
}
.largebtn{
  height: 80px;
  width: 750px;
  background-color: $themeColor;
  color: white;
  line-height: 80px;
  text-align: center;
  font-size: 28px;
}
.photolist{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: flex-end;
  background-color: white;
}
.photoitem{
  flex: 1;
  padding: 15px;
  align-items: center;
  justify-content: stretch;
}
.img{
  width: 200px;
  height: 200px;
  align-items: center;
  justify-content: center;
  background-color: #eeeeee;
  border-radius: 5px;
}
.photoname{
  font-size: 28px;
  color: #3c4245;
  margin-bottom: 10px;
}
</style>
