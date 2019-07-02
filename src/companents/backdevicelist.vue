<template>
    <div>
        <titleblock :color="color" :title="title" ref="beijian">
            <div>
                <text class="info-title-s">共2件备件</text>
                <scroller style="margin-top:25px">
                    <div class="beijianitem" v-for="i in 23" :key="i">
                        <div class="row">
                            <text class="info-title-s">150000001</text>
                            <text class="info-title-s red">自有备件</text>
                        </div>
                        <div class="row">
                            <text class="info-title">TISCAC-24V电子镇流器通用</text>
                            <div class="counter">
                                <icon v-if="!readonly" :color="themeColor" :size="40" :icon="'\ue692'"/>
                                <text class="info-title red" style="width:30px;text-align:center;">1</text>
                                <icon v-if="!readonly" :color="themeColor" :size="40" :icon="'\ue690'"/>
                            </div>
                        </div>
                    </div>
                </scroller>
                <div class='contentbar' v-if="!readonly" >
                    <icon color="#999999" :size="35" :icon="'\ue600'"/>
                </div>
                
            </div>
        </titleblock>
    </div>
</template>
<script>
import titleblock from './titleblock.vue'
import icon from './icon.vue'
import { themeColor } from '../utils/config'

export default {
  props: {
    title: String,
    color: {
      type: String,
      default: '#383838',
    },
    readonly: Boolean,
  },
  components: {
    titleblock, icon,
  },
  data() {
    return { themeColor }
  },
}
</script>

<style lang="scss" scoped>
@import '../style/global';

.row{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-bottom: 10px;
    align-items: center;
}
.info-title{
    line-height: 42px;
	color: rgba(128, 128, 128, 1);
	font-size: 28px;
}
.info-title-s{
    line-height: 36px;
	color: rgba(128, 128, 128, 1);
	font-size: 22px;
}
.info-content{
    line-height: 42px;
	color: rgba(56, 56, 56, 1);
	font-size: 28px;
}
.contentbar{
    position: absolute;
    right: 0;
    top: 0;
    display: flex;
    flex-direction: column;
}
.red{
    color: $themeColor;
}
.counter{
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    align-items: center;
}
.beijianitem{
    border-bottom-width: 1px; 
    border-bottom-color: #f4f4f4;
    margin-bottom: 8px;
}
</style>
