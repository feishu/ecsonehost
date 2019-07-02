<template>
    <div class="iconitem">
        <icon size='45' :color="themeColor" :icon="iconname" v-if="iconname"/>
        <text class="title">{{title}}</text>
        <div class="iconright">
        <icon v-if="link" size='30' color="#cfcfcf" :icon="'\ue68b'"/>
        </div>
        <div class="line" v-if="line"></div>
    </div>
</template>
<script>
import icon from './icon.vue'
import { themeColor } from '../utils/config'

export default {
  props: {
    iconname: String,
    title: String,
    link: {
      type: Boolean,
      default: true,
    },
    line: {
      type: Boolean,
      default: true,
    },
  },
  components: {
    icon,
  },
  data() {
    return {
      themeColor,
    }
  },
}
</script>

<style lang="scss">
.iconitem{
    position: relative;
    min-height: 110px;
    flex-direction: row;
    display: flex;
    justify-content: flex-start;
    background-color: white;
    align-items: center;
    padding: 0 20px;
}
.iconright{
    position: absolute;
    right: 20px;
    top: 35px;
}
.title{
    margin-left: 22px;
    color: rgba(80, 80, 80, 1);
	font-size: 32px;
}
.line{
    position: absolute;
    bottom: 0;
    left: 20px;
    height: 1px;
    background-color: #c3c3c3;
    right: 0;
}
</style>
