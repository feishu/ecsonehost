<template>
  <div class="page">
   <text class="title">请先同步数据</text>
   <wb-lottie v-if="isloading" class="lottie" :sourceJson='loadingjson' resizeMode="contain" ref="lottie"></wb-lottie>
   <image v-else :src='loadingPreview' resize='cover' class="lottie"/>
   <text class="sync-text" v-if="isloading" >正在同步...</text>
   <button class="sync-btn"><text class="sync-btn-text" @click="sync">同步</text></button>
   <text class="text-btn" @click="gohome">跳过</text>
   <image :src='loginlogo' class="bottomlogo"/>
  </div>    
</template>

<script>
import loadingPreview from '../../../static/img/loading-preview.png'
import loginlogo from '../../../static/img/login_logo.png'

import { router } from '../../utils/native'

import { themeColor } from '../../utils/config'

const loadingjson = require('../../style/sync-loading.json')

const navigator = weex.requireModule('wb-navigator')
// const storage = weex.requireModule('storage')
export default {
  components: {
  },
  data() {
    return {
      a: '1124',
      loadingjson,
      loadingPreview,
      isloading: null,
      loginlogo,
    }
  },
  created() {
    navigator.setCenterItem({
      text: '同步',
      color: '#ffffff',
    }, () => {})
    navigator.setLeftItems([{
      text: '',
      color: '#ffffff',
    }], () => {})
    navigator.setNavColor(themeColor)
    setTimeout(() => {

    }, 2000)

    // storage.getItem('islogin', (e) => {
    //   if (!e.data) {

    //   }
    // })
  },
  mounted() {
    this.$refs.lottie.playFromProgress(0, 1)
  },
  methods: {
    gohome() {
      router.open({
        url: 'page/home.js',
        params: {},
      })
    },
    startloading() {
      if (this.isloading) return
      this.isloading = setInterval(() => {
        if (!this.$refs.lottie.isAnimationPlaying()) {
          this.$refs.lottie.play()
        }
      }, 100)
    },
    stoploading() {
      clearInterval(this.isloading)
      this.isloading = null
      this.isplaying = false
      this.$refs.lottie.stop()
    },
    sync() {
      if (!this.isloading) {
        this.startloading()
      } else {
        this.stoploading()
      }
    },
  },
}
</script>

<style lang="scss" scoped>
@import '../../style/global';
.page{
  background-color: $themeColor;
}
.title{
    color: #fff;
    font-size: 56px;
    text-align: left;
    margin-top: 80px;
    margin-left: 40px;
    line-height: 120px;
  }
.lottie{
    width: 750px;
  height: 550px;
}
.sync-btn{
    width: 660px;
    height: 100px;
    border-radius: 50px;
    background-color: white;
    margin-top: 100px;
    margin-left: 45px;
    
}
.sync-btn-text{
    text-align: center;
    line-height: 100px;
    font-size: 32px;
    color: $themeColor;
}
.sync-text{
    font-size: 32px;
    margin-top: -50px;
    text-align: center;
    color: white;
}
.text-btn{
    font-size: 32px;
    color: white;
    margin-top: 32px;
    text-align: center;
}
.bottomlogo{
    position: fixed;
    bottom:50px;
    // left: 50%;
    // transform: translateX(-50%);
    width: 240px;
    height: 60px;
    margin-left: 255px;
}
</style>
