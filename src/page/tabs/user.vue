<template>
    <div>
        <div class="header">
            <text class="name">黄文强</text>
            <div class="row">
                <text class="normaltext">XMS001253</text>
                <text class="normaltext mg100">18758878000</text>
            </div>
            <text class="normaltext">厦门-某某第三方维修商</text>
        </div>
        <am-list style="margin-top:22px">
            <am-list-item
            title="版本号"
            extra="v1.0.0"
            arrow='empty'
            ></am-list-item>
            <am-list-item
            title="质检员设置"
            ></am-list-item>
            <am-list-item
            title="目标维护设置"
            ></am-list-item>
        </am-list>
        <am-list style="margin-top:50px">
            <am-list-item
            title="退出登入"
            ></am-list-item>
        </am-list>
    </div>
</template>
<script>
import { AmList, AmListItem } from 'weex-amui'
import { themeColor } from '../../utils/config'
import iconitem from '../../companents/iconitem.vue'

const navigator = weex.requireModule('wb-navigator')
export default {
  components: {
    iconitem, AmList, AmListItem,
  },
  data() {
    return {
      themeColor,
    }
  },
  created() {
    navigator.setCenterItem({
      text: '个人中心',
      color: '#ffffff',
    }, () => {})
    navigator.setLeftItems([{
      text: '',
      color: '#ffffff',
    }], () => {})
    navigator.setNavColor(themeColor)
  },
  methods: {

  },
}
</script>
<style lang="scss" scoped>
@import '../../style/global';
.content{
  padding: 0 30px;
  position: relative;
  z-index: 2;
}
.header{
    padding: 10px 34px 50px 34px; 
    background-color: white;
}
.normaltext{
    line-height: 62px;
	color: rgba(56, 56, 56, 1);
	font-size: 32px;
}
.name{
    @extend .normaltext;
    font-weight: bold;
}
.row{
    display: flex;
    flex-direction: row;
    align-items: center;

}
.mg100{
    margin-left: 100px;
}
</style>
