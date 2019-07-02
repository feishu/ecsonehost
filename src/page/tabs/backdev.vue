<template>
    <div class="content">
      <backdeviceitem v-for="i in 10" :key="i"></backdeviceitem>
    </div>
</template>
<script>
import { themeColor } from '../../utils/config'
import backdeviceitem from '../../companents/backdeviceitem.vue'

const navigator = weex.requireModule('wb-navigator')
export default {
  components: {
    backdeviceitem,
  },
  data() {
    return {
      themeColor,
    }
  },
  created() {
    navigator.setCenterItem({
      text: '备件',
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
