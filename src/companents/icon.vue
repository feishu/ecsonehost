<template>
    <div @click="iclick" class='icon'><am-icon :color='color' :size="size" :type="icon" font-family="myIcon"/></div>
</template>
<script>
import { AmIcon } from 'weex-amui'

const dom = weex.requireModule('dom')
export default {
  props: {
    icon: String,
    color: String,
    size: Number,
  },
  components: { AmIcon },
  beforeCreate() {
    // 添加自己的图标文件
    dom.addRule('fontFace', {
      fontFamily: 'myIcon', // 与 <am-icon/> 组件的 fontFamily 属性对应
      src: "url('https://at.alicdn.com/t/font_1248465_uflpkowalsj.ttf')",
    })
  },
  methods: {
    iclick() {
      this.$emit('iclick')
    },
  },
}
</script>
<style lang="scss">

</style>
