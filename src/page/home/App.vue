<template>
  <div class="page">
    <text class="title">请选择功能模块</text>
    <div class="content">
      <div class="item">
        <div class="item-icon-wrap" @click="gofix">
          <image :src="iconFix" class="item-icon"/>
        </div>
        <text class="item-title">维修工单-市场</text>
      </div>
      <div class="item">
        <div class="item-icon-wrap" @click="gorenew">
          <image :src="iconFix" class="item-icon"/>
        </div>
        <text class="item-title">维修工单-在库</text>
      </div>
      <div class="item">
        <div class="item-icon-wrap">
          <image :src="iconFix" class="item-icon"/>
        </div>
        <text class="item-title">整新工单</text>
      </div>
      <div class="item">
        <div class="item-icon-wrap">
          <image :src="iconFix" class="item-icon"/>
        </div>
        <text class="item-title">其它</text>
      </div>
    </div>
  </div>
</template>

<script>
import iconFix from '../../../static/img/icon-fixed.png'

import native, { router } from '../../utils/native'

import { appName, themeColor } from '../../utils/config'

const navigator = weex.requireModule('wb-navigator')
// const storage = weex.requireModule('storage')
export default {
  components: {
  },
  data() {
    return {
      a: '1124',
      iconFix,
    }
  },
  created() {
    navigator.setCenterItem({
      text: appName,
      color: '#ffffff',
    }, () => {})
    navigator.setLeftItems([{
      text: '',
      color: '#ffffff',
    }], () => {})
    navigator.setNavColor(themeColor)
    // this.login()
    // storage.getItem('islogin', (e) => {
    //   if (!e.data) {

    //   }
    // })
  },
  methods: {
    goLogin() {
      router.open({
        name: 'weex',
        title: '登录',
        url: 'page/login.js',
        params: {},
      })
    },
    gofix() {
      router.open({
        url: 'page/fixindex.js',
        navBarHidden: true,
        params: {},
      })
    },
    gorenew() {
      router.open({
        url: 'page/renewindex.js',
        navBarHidden: true,
        params: {},
      })
    },
    news() {
      router.open({
        name: 'weex',
        title: '资讯',
        url: 'https://raw.githubusercontent.com/aygtech/incubator-weex/master/ios/playground/bundlejs/news.weex.js',
      })
    },
    weexPage() {
      router.open({
        name: 'weex',
        title: 'Weex实例',
        url: native.examplesWeex,
      })
    },
    login() {
      router.open({
        url: 'page/login.js',
        navBarHidden: true,
        params: {},
      })
    },
    weexboxPage() {
      router.open({
        url: 'page/module.js',
        params: {},
      })
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

  .content{
    margin-top: 100px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    flex-wrap: wrap;
    padding:20px;
  }
  .item{
      align-items: center;
    justify-content: center;
    margin-bottom: 30px;
    }
  .item-title{
    color:white;
    font-size: 34px;
    line-height: 60px;
    margin-top: 7px;
  }
  .item-icon-wrap{
    width: 320px;
    height: 320px;
    background-color: #fff;
    align-items: center;
    justify-content: center;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, .3);
  }
  .item-icon{
    width: 180px;
    height: 180px;
  }
</style>
