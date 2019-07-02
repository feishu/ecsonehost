<template>
  <div class="page">
    <tabsheader :tabs="tabs" @change="tabchange"></tabsheader>
    <scroller style="margin-top:20px;">
      <current></current>
    </scroller>
    
  </div>
</template>

<script>
// import { AmTabBar, AmTabBarItem, AmNavBar } from 'weex-amui'
// import tag from '../../companents/tag.vue'

import current from './current.vue'
// import backdev from '../tabs/backdev.vue'
// import staticpage from '../tabs/static.vue'
// import messages from '../tabs/messages.vue'
// import user from '../tabs/user.vue'

// import icon from '../../companents/icon.vue'

// import fixorder from '../../companents/fixorder.vue'
import tabsheader from '../../companents/tabsheader.vue'

// import native, { router } from '../../utils/native'

// import { themeColor } from '../../utils/config'

// const dom = weex.requireModule('dom')

// const navigator = weex.requireModule('wb-navigator')
// const storage = weex.requireModule('storage')
export default {
  components: {
    current,
    tabsheader,
  },

  data() {
    return {
      tabs: ['当月目标查看', '下月目标设置'],
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
  methods: {},

}
</script>

<style lang="scss" scoped>
@import "../../style/global";
.page {
  background-color: #f4f4f4;
}

</style>
