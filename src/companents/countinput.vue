<template>
    <div class="countinput">
        <input v-model="count" type='number' class="txtinput"/>
        <div class="btngroup">
            <button @click="count++" style="border-bottom-width: 1px;border-bottom-color: #e5e5e5;" class="btn"><icon @iclick="count++" class="btnicon" :size="20" color="#c3c3c3" :icon="'\ue669'"/></button>
            <button @click="count--" class="btn"><icon @iclick="count--" class="btnicon" :size="20" color="#c3c3c3" :icon="'\ue674'"/></button>
        </div>
    </div>
</template>
<script>
import icon from './icon.vue'

export default {
  components: {
    icon,
  },
  data() {
    return {
      count: 0,
    }
  },
}
</script>

<style lang="scss" scoped>
.countinput{
    background-color: white;
    height: 46px;
    width: 126px;
    border-width: 1px;
    border-color: #e5e5e5;
    border-radius: 5px;
    display: flex;
    flex-direction: row;
    align-items: stretch;
}
.txtinput{
    padding: 6px 20px;
    text-align: left;
    flex: 1;
	line-height: 44px;
	color: #333;
	font-size: 20px;
    align-self: center;
}
.btngroup{
    width: 30px;
    display: flex;
    flex-direction: column;
    justify-content: stretch;
    align-items: stretch;
    border-left-width: 1px;
    border-left-color: #e5e5e5;
}
.btn{
    align-items: center;
    justify-content: center;    
    flex: 1;
    background-color: white;
}
.btn:active{
    background-color: rgb(189, 188, 188);
}
</style>
