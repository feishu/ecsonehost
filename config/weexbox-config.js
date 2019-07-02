const config = {
  develop: {
    // 从网络加载图片
    imagePublicPath: null,
  },
  test: {
    // 从网络加载图片
    imagePublicPath: 'http://weex.frp.apcan.cn',
  },
  preRelease: {
    // 从app加载图片
    imagePublicPath: 'bundle://',
  },
  release: {
    // 从网络加载图片
    imagePublicPath: 'bundle://',
  },
}

module.exports = config
