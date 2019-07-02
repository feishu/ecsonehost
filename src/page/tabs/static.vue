<template>
    <div>
        <iconitem :iconname="'\ue64c'" title="维修员翻新进度汇总表"/>
        <iconitem :iconname="'\ue629'" title="待翻新设备库存报表"/>
        <iconitem :iconname="'\ue61d'" title="个人备件报表" :line="false"/>
    </div>
</template>
<script>
import { themeColor } from '../../utils/config'
import iconitem from '../../companents/iconitem.vue'

const navigator = weex.requireModule('wb-navigator')
export default {
  components: {
    iconitem,
  },
  data() {
    return {
      themeColor,
    }
  },
  created() {
    navigator.setCenterItem({
      text: '报表',
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

</style>
