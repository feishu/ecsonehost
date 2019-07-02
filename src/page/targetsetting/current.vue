<template>
    <div class="page">
        <div class="row titlerow" style="border-bottom-width:0px;">
            <div class="left">
            </div>
            <div class="right">
                <text class="item title-text">展示柜</text>
                <text class="item title-text">现调机</text>
                <text class="item title-text">自贩机</text>
            </div>
        </div>
        <scroller>
        <div class="row" v-for="i in 20" :key="i">
            <div class="left">
                <text class="title-text">HZS100003</text>
                <text class="title-text">王信东</text>
            </div>
            <div class="right">
                <div class="item"><countinput/></div>
                <div class="item"><countinput/></div>
                <div class="item"><countinput/></div>
            </div>
        </div>
        </scroller>
    </div>
</template>
<script>

import countinput from '../../companents/countinput.vue'

export default {
  components: { countinput },
}
</script>

<style lang="scss" scoped>
.page{
    background-color: white;
    flex: 1;
}
.row{
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    border-bottom-color: #e5e5e5;
    border-bottom-width: 1px;
    padding: 10px 20px;
}
.titlerow{
    padding-top: 20px;
    padding-bottom: 20px;
}
.left{
    width: 220px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
}
.title-text{
	height: 30px;
    line-height: 30px;
	color: #333333;
	font-size: 28px;
}

.right{
    flex: 1;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-around;
}
</style>
