<template>
  <div class="page">
    <tabsheader title="我的工单"></tabsheader>
    <am-tabs
        v-model="selected"
        :tab-width="160"
        :tabs="tabs"
      />
    <scroller style="margin-top:20px;">
      <gongdanitem v-for="i in 20" :key="i"/>
    </scroller>
  </div>
</template>

<script>
import { AmTabs } from 'weex-amui'
import gongdanitem from '../../companents/gongdanitem.vue'
import icon from '../../companents/icon.vue'
import tabsheader from '../../companents/tabsheader.vue'
import { themeColor } from '../../utils/config'

export default {
  components: {
    tabsheader,
    icon,
    AmTabs,
    gongdanitem,
  },

  data() {
    return {
      selected: 0,
      themeColor,
      tabs: ['全部', '已分派(3)', '未分派(2)', '已销单(2)', '可确认(2)'],
    }
  },
  created() {},
  watch: {},
  methods: {

  },
}
</script>

<style lang="scss" scoped>
@import "../../style/global";
.page {
  background-color: #f4f4f4;
}

</style>
