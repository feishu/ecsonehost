<template>
  <div class="module-wrapper">
    <div class="module-title">
      <text class="title">功能模块</text>
      <text class="title-name">请选择功能模块</text>
    </div>
    <div class="module-content">
      <am-grid
          @click="handleClick"
          :data="list"
          :hasLine="false"
          :column-num="2"></am-grid>
    </div>
  </div>
</template>
<script>

import { AmGrid } from 'weex-amui'

export default {
  components: {
    AmGrid,
  },
  data() {
    return {
      list: [{ text: '维修工单-市场', icon: '' }, { text: '维修工单-市场', icon: '' }, { text: '维修工单-市场', icon: '' }, { text: '维修工单-市场', icon: '' }],
    }
  },
  created() {

  },
  mounted() {

  },
  methods: {

  },
}
</script>
<style lang="scss" scoped>
.module-wrapper{
  flex:1;
  position:relative;
  padding:200px 40px 0;
  background-color:#ff5733;
}
.module-title{
  position:absolute;
  top:0;
  left:40px;
  right:40px;
  height:200px;
}
.title{
  padding-top: 50px;
  line-height: 50px;
  font-size: 34px;
  color:#fff;
}
.title-name{
  line-height:90px;
  font-size: 56px;
  color:#fff;
  font-weight:bold;
}
</style>