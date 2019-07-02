<template>
  <div class="page">
    <div class="header" v-if="selected<2" :style="{paddingTop:statusheight+'px'}">
      <div class="toolbar">
        <text class="toolbartitle">{{tabBar[selected].title}}</text>
        <icon :size="35" v-if="selected==0" color="#ffffff" :icon="'\ue601'"/>
        <icon :size="35" v-if="selected==1" color="#ffffff" :icon="'\ue600'"/>
      </div>
      <text class="top-tip">7条工单未处理</text>
    </div>
    <AmNavBar
      :style="{marginTop:statusheight+'px'}"
      mode="light"
      :left-btn="[{is: 'text', text: '', key: ''}]"
      :title="tabBar[selected].title"
      v-else>
    </AmNavBar>

    <scroller v-if="selected==0">
      <index/>
    </scroller>

    <scroller v-if="selected==1">
      <backdev/>
    </scroller>
    <scroller v-if="selected==2">
      <staticpage/>
    </scroller>
    <scroller v-if="selected==3">
      <messages/>
    </scroller>
    <scroller v-if="selected==4">
      <user/>
    </scroller>
    <am-tab-bar iconFontFamily="tabicon" :iconSize="30" :tintColor="themeColor">
      <am-tab-bar-item
        iconFontFamily="tabicon"
        :iconSize="30"
        v-for="(item,index) in tabBar"
        :key="item.title"
        :icon="item.icon"
        :title="item.title"
        :selected="selected === index"
        @click="selected = index"
      />
    </am-tab-bar>
  </div>
</template>

<script>
import { AmTabBar, AmTabBarItem, AmNavBar } from 'weex-amui'
import tag from '../../companents/tag.vue'

import index from '../tabs/index.vue'
import backdev from '../tabs/backdev.vue'
import staticpage from '../tabs/static.vue'
import messages from '../tabs/messages.vue'
import user from '../tabs/user.vue'

import icon from '../../companents/icon.vue'

import fixorder from '../../companents/fixorder.vue'

// import native, { router } from '../../utils/native'

import { themeColor } from '../../utils/config'

const dom = weex.requireModule('dom')

const navigator = weex.requireModule('wb-navigator')
// const storage = weex.requireModule('storage')
export default {
  components: {
    AmTabBar,
    AmTabBarItem,
    tag,
    fixorder,
    index,
    backdev,
    icon,
    staticpage,
    messages,
    user,
    AmNavBar,
  },

  data() {
    return {
      statusheight: 0,
      a: '1124',
      themeColor,
      selected: 0,
      tabBar: [
        {
          icon: '\ue65c',
          title: '工单',
        },
        {
          icon: '\ue644',
          title: '备件',
        },
        {
          icon: '\ue610',
          title: '报表',
        },
        {
          icon: '\ue626',
          title: '消息',
        },
        {
          icon: '\ue62c',
          title: '我',
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
  methods: {},
  beforeCreate() {
    // 添加自己的图标文件
    dom.addRule('fontFace', {
      fontFamily: 'tabicon', // 与 <am-icon/> 组件的 fontFamily 属性对应
      src: "url('https://at.alicdn.com/t/font_1248465_uflpkowalsj.ttf')",
    })
  },
}
</script>

<style lang="scss" scoped>
@import "../../style/global";
.page {
  background-color: #f4f4f4;
}
.toolbar{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.toolbartitle{
  height: 46px;
	color: rgba(255, 255, 255, 1);
	font-size: 34x;
}
.header {
  width: 750px;
  min-height: 70px;
  background-color: $themeColor;
  padding: 0 30px;
}
.top-tip {
  color: white;
  font-size: 24px;
  line-height: 60px;
}
</style>
