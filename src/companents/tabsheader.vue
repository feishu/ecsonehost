<template>
    <div class="header" :style="{paddingTop:statusheight+'px',backgroundColor:bgcolor}">
        <div class="header-content">
            <div class="left">
                <icon :size="35" color="#383838" :icon="'\ue604'"/>
            </div>
            <input v-if="search" placeholder="姓名/拼音/手机号" class="searchinput"/>
            <text class="title" v-else-if="title">{{title}}</text>
            <div class="tabs" v-else>
              <text class="tab" :style="selected==index?activestyle:{}" @click="selected=index" v-for="(tab,index) in tabs" :key="index">{{tab}}</text>
            </div>
            
            <div class="right">
                <slot name="right"/>
            </div>
        </div>
        
    </div>
</template>
<script>
import { AmTabs } from 'weex-amui'
import icon from './icon.vue'
import { themeColor } from '../utils/config'

const navigator = weex.requireModule('wb-navigator')
export default {
  props: {
    title: String,
    tabs: {
      type: Array,
      default: () => ['工单', '备件'],
    },
    bgcolor: {
      type: String,
      default: '#ffffff',
    },
    search: Boolean,
  },
  components: {
    icon, AmTabs,
  },
  data() {
    return {
      statusheight: 0,
      selected: 0,
      activestyle: {
        color: themeColor,
        borderBottomWidth: '2px',
        borderBottomColor: themeColor,
      },
    }
  },
  created() {
    this.statusheight = navigator.getHeight()
  },
  watch: {
    selected() {
      this.$emit('change', this.selected)
    },
  },
}
</script>

<style lang="scss" scoped>
@import '../style/global';

.header{
    background-color: white;
    border-bottom-width: 1px ;
    border-bottom-color: rgba(221, 203, 203, 0.685);
}
.header-content{
    height: 88px;
    justify-content: space-between;
    flex-direction: row;
    display: flex;
    align-items: center;
}
.left{
    width: 150px;
    justify-content: flex-start;
    padding-left: 20px;
}
.right{
    width: 150px;
    padding-right: 20px;
    display: flex;
    flex-direction: row;
    justify-content:flex-end;;
}
.tabs{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;

}
.title{
  height: 50px;
  line-height: 50px;
	color: rgba(56, 56, 56, 1);
	font-size: 34px;
}
.tab{
  margin: 0 20px;
  color:#383838;
  height:48px;
  line-height: 42px;
  font-size: 28px;
  
}
.active{
  
}
.searchinput{
  flex: 1;
	height: 60px;
	line-height: 60px;
	color: rgba(166, 166, 166, 1);
	background-color: rgba(255, 255, 255, 1);
	font-size: 28px;
	border-color: rgba(244, 244, 244, 1);
  border-width: 1px;
	text-align: center;
}
</style>
