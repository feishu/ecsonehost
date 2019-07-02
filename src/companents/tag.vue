<template>
    <text class="tag" :style="{...(active?colors.active:colors.normal)}">{{content}}</text>
</template>
<script>
export default {
  props: {
    content: String,
    active: Boolean,
    colors: {
      type: Object,
      default: () => ({
        normal: {
          borderColor: '#d9d9d9',
          backgroundColor: '#ffffff',
          color: '#808080',
        },
        active: {
          borderColor: '#88afd5',
          backgroundColor: 'rgba(136, 175, 213, 0.1)',
          color: '#88afd5',
        },
      }),
    },
  },
}
</script>
<style lang="scss">
.tag{
    height:50px;
    padding:0px 20px;
    line-height: 50px;
    font-size: 25px;
    text-align: center;
    color:#808080;
    border-radius: 50px;
    border-width: 1px;
    border-style: solid;
    border-color: #d9d9d9;
    margin-right: 15px;
}
</style>
