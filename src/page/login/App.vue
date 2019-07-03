<template>
  <div class="login-wrapper">
    <image :src="loginbg" class="login-image"></image>
    <div class="login-content">
      <text class="title">注册</text>
      <text class="title-name">新用户注册</text>
      <text class="app-name">ECSOne</text>
      <text class="app-version">v 1.0.0</text>
      <div class="form-item">
        <text class="form-label">手机号</text>
          <input class="phone"/>
      </div>
      <div class="form-item">
        <text class="form-label">验证码{{deviceInfo.imei}}</text>
        <input class="code" return-key-type="注册"/>
        <text class="btn btn-getcode">获取验证码</text>
      </div>
    
      <div class="btn button-box-login" @click="formshow = true">
        <text class="button-text">注册并登入</text>
      </div>
      <div class="footer-logo-wrapper">
        <image :src="loginlogo" class="footer-logo"></image>
      </div>
    </div>

    <am-popup :show.sync="formshow" :position="bottom" :offset="{}" :auto-close="true" :popup-style="style" height="860">
      <div class="popup-content">
        <div class="popup-header">
          <text class="popup-header-title">第三方维修员资料填写 </text>
        </div>
        <div class="popup-main">
          <am-list :noBorder="false">
            <am-list-input v-model="inputValue" title="姓名" placeholder="请输入名字"></am-list-input>
            <am-picker
              title="请选择"
              :data="changfangdata"
              v-model="changfangselectval"
            >
              <am-list-item
                slot-scope="{ extra, show }"
                title="厂房"
                :extra="extra"
                @click="show"
              ></am-list-item>
            </am-picker>
            <am-picker
              title="请选择"
              :data="changfangdata"
              v-model="changfangselectval"
            >
              <am-list-item
                slot-scope="{ extra, show }"
                title="维修商"
                :extra="extra"
                @click="show"
              ></am-list-item>
            </am-picker>
          </am-list>
          <div class="btn button-box-confirm" @click="submit">
            <text class="btn-confirm">确认</text>
          </div>
        </div>
      </div>
    </am-popup>
 
  </div>
</template>
<script>
// import native, { router } from '../../utils/native'

import {
  AmPopup, AmList, AmListInput, AmListItem, AmButton, AmPicker,
} from 'weex-amui'

import loginbg from '../../../static/img/bg_login.png'

import loginlogo from '../../../static/img/login_logo.png'
import { themeColor } from '../../utils/config'
import { router } from '../../utils/native'

const utility = weex.requireModule('utility')
const picker = weex.requireModule('picker')
const navigator = weex.requireModule('wb-navigator')

export default {
  components: {
    AmPopup,
    AmList,
    AmListItem,
    AmListInput,
    AmButton,
    AmPicker,
  },
  data() {
    return {
      loginbg,
      loginlogo,
      formshow: false,
      changfangselectval: '',
      changfangselector: true,
      deviceInfo: {},
      changfangdata: [{
        label: '杭州厂房',
        value: '2013',
      }, {
        label: '杭州厂房',
        value: '2013',
      }, {
        label: '杭州厂房',
        value: '2013',
      }, {
        label: '杭州厂房',
        value: '2013',
      }, {
        label: '杭州厂房',
        value: '2013',
      }, {
        label: '杭州厂房',
        value: '2013',
      }],
    }
  },
  created() {
    navigator.setNavColor(themeColor)
    navigator.setCenterItem({
      text: '注册',
      color: '#ffffff',
    }, () => {})
    navigator.setLeftItems([{
      text: '',
      color: '3d3d3d',
    }], () => {})
    utility.getDeviceInfo((res) => { this.deviceInfo = res })
  },
  methods: {
    pick() {
      picker.pick({
        items: ['Apple', 'Banana', 'Orange'],
        confirmTitleColor: themeColor,
      }, (event) => {
        if (event.result === 'success') {
          this.value = event.data
        }
      })
    },
    submit() {
      this.formshow = false
      router.open({
        name: 'weex',
        title: '登录',
        navBarHidden: true,
        url: 'page/sync.js',
        params: {},
      })
    },
  },
}
</script>

<style lang="scss" scoped>
@import '../../style/global';
  .login-wrapper{
    flex:1;
    position:relative;
  }
  .login-image{
    background-color: $themeColor;
    // flex:1;
    height: 1334px;
    position:fixed;
    top:0;
    bottom:0;
    left: 0;
    right: 0;
    // z-index: 5;
  }
  .login-content{
    position:fixed;
    top:0;
    left:0;
    right:0;
    bottom:0;
    padding:0 40px;
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
  .app-name{
    padding-top: 40px;
    line-height:80px;
    font-size: 56px;
    color:#fff;
    text-align:center;
    font-weight:bold;
  }
  .app-version{
    line-height: 46px;
    padding-left:180px;
    padding-bottom: 50px;
    font-size:34px;
    text-align:center;
    color:#fff;
  }
  .form-item{
    display:flex;
    position:relative;
    height:144px;
    padding-top:30px;
  }
  .form-label{
    line-height:40px;
    font-size:30px;
    color:#fff;
  }
  .phone{
    line-height:70px;
    color:#fff;
    border-bottom-color:#fff;
    border-bottom-width:1px;
  }
  .code{
    line-height:70px;
    color:#fff;
    border-bottom-color:#fff;
    border-bottom-width:1px;
    padding-right:200px;
    z-index:10;
  }
  .btn-getcode{
    position:absolute;
    right:0;
    bottom:0;
    line-height:70px;
    color:#fff;
    font-size:30px;
    z-index:100;
  }
  .button-box-login{
    justify-content: center;
    align-items: center;
    align-content: center;
    height:96px;
    margin-top:200px;
    line-height:96px;
    background-color:#fff;
    border-radius:96px;
  }
  .button-text{
    color:#d43030;
    font-size:32px;
  }
  .footer-logo-wrapper{
    justify-content: center;
    align-items: center;
    align-content: center;
    padding: 80px 0;
    text-align:center;
  }
  .footer-logo{
    width:270px;
    height:70px;
  }
  .btn{
    opacity: 1;
  }
  .btn:active {
    opacity: .8;
}
.popup-header{
  justify-content: center;
  align-items: center;
  align-content: center;
  height:84px;
  line-height:84px;
  border-bottom-width:1px;
  border-bottom-color:#e5e5e5;
}
.popup-header-title{
  font-size: 28px;
  color:#505050;
}
.popup-main{
  padding:100px 26px;
}
.name{
  height:60px;
  border-width: 1px;
  border-color:#f4f4f4;
}
.button-box-confirm{
  justify-content: center;
  align-items: center;
  align-content: center;
  height:72px;
  line-height:72px;
  margin:180px 80px 0;
  border-width:1px;
  border-color:#ff5733;
  border-radius:72px;
}
.btn-confirm{
  font-size: 32px;
  color:#ff5733;
}
</style>