<template>
  <div class="page">
    <div class="header" v-if="selected<1" :style="{paddingTop:statusheight+10+'px'}">
      <div class="toolbar">
        <text class="toolbartitle">{{tabBar[selected].title}}</text>
        <div class='inputbar'>
          <input type='text' class="searchinput" placeholder="搜索设备内容"/>
          <icon :size="35" :color="themeColor" :icon="'\ue602'" style="position:absolute;right:20px;top:12px"/>
        </div>
        <icon :size="35" color="#ffffff" :icon="'\ue640'"/>
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
    <scroller v-if="selected==0" >
      <renew />
    </scroller>
    <scroller  v-if="selected==1" >
      <staticpage/>
    </scroller>
    <scroller  v-if="selected==2" >
      <messages/>
    </scroller>
    <scroller  v-if="selected==3" >
      <user/>
    </scroller>
    <am-tab-bar
      iconFontFamily="tabicon"
      :iconSize="30"
       :tintColor='themeColor'
    >
    <am-tab-bar-item
        iconFontFamily="tabicon"
        :iconSize="30"
        :icon="'\ue65c'"
        title="工单"
        :selected="selected === 0"
        @click="selected = 0"
      />
      <am-tab-bar-item
        iconFontFamily="tabicon"
        :iconSize="30"
        :icon="'\ue610'"
        title="报表"
        :selected="selected === 1"
        @click="selected = 1"
      />
      <div class="add">
          <icon :size="40" color="#ffffff" :icon="'\ue600'"/>
          <text class="add-text">新建工单</text>
      </div>
      <am-tab-bar-item
        iconFontFamily="tabicon"
        :iconSize="30"
        :icon="'\ue626'"
        title="消息"
        :selected="selected === 2"
        @click="selected = 2"
      />
      <am-tab-bar-item
        iconFontFamily="tabicon"
        :iconSize="30"
        :icon="'\ue62c'"
        title="我"
        :selected="selected === 3"
        @click="selected = 3"
      />
    </am-tab-bar>
  </div>
</template>

<script>
import { AmTabBar, AmTabBarItem, AmNavBar } from 'weex-amui'
import tag from '../../companents/tag.vue'

import renew from '../tabs/renew.vue'
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
    AmTabBar, AmTabBarItem, tag, fixorder, renew, backdev, icon, staticpage, messages, user, AmNavBar,
  },

  data() {
    return {
      a: '1124',
      statusheight: 0,
      themeColor,
      selected: 0,
      tabBar: [
        {
          icon: '\ue65c',
          title: '工单',
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
  methods: {

  },
  beforeCreate() {
    // 添加自己的图标文件
    dom.addRule('fontFace', {
      fontFamily: 'tabicon', // 与 <am-icon/> 组件的 fontFamily 属性对应
      src: "url('https://at.alicdn.com/t/font_1248465_unp0ivppvjd.ttf')",
    })
  },
}
</script>

<style lang="scss" scoped>
@import '../../style/global';
.page{
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
.header{
  width: 750px;
  min-height: 70px;
  background-color: $themeColor;
  padding:0 30px;
}
.top-tip{
  color: white;
  font-size: 24px;
	line-height: 60px;
}
.add{
  background-color: $themeColor;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 0 20px;
  opacity: 1;
}
.add:active{
  opacity: 0.8;
}
.add-text{
  color: white;
  font-size: 24px;
  line-height: 32px;
}

.inputbar{
  flex: 1;
  background-color: white;
  height: 60px;
  border-radius: 60px;
  margin: 0 30px; 
  position: relative;
}
.searchinput{
  height: 60px;
  line-height: 60px;
  color: rgba(166, 166, 166, 1);
	font-size: 28px;
  margin-left: 30px;
  margin-right: 30px;
  text-align: center;
}
</style>
