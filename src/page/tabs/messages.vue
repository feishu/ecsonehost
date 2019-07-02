<template>
    <div class="content">
        <div v-for="ii in 4" :key="ii">
            <text class="date">4月19日 15:06</text>
            <messageitem v-for="i in 3" :key="i" title="保养工单" content="1000045工单 维修员[XMS10003的工单已提交销单]"/>
        </div>
    </div>
</template>
<script>
import { themeColor } from '../../utils/config'
import messageitem from '../../companents/messageitem.vue'

const navigator = weex.requireModule('wb-navigator')
export default {
  components: {
    messageitem,
  },
  data() {
    return {
      themeColor,
    }
  },
  created() {
    navigator.setCenterItem({
      text: '消息',
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
.date{
    height: 36px;
    line-height: 36px;
    padding: 0 10px;
    border-radius: 8px;
    font-size: 22px;
    color: rgba(80, 80, 80, 1);
	background-color: rgba(196, 196, 196, 1);
    display: inline-block;
    align-self:center;
    margin-bottom: 28px;
    margin-top: 15px;
}
.content{
  padding: 0 30px;
  position: relative;
}


</style>
