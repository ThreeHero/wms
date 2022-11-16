const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: false,
  chainWebpack: config => {
    config.plugin('html')
      .tap(args => {
        args[0].title = '仓库管理系统'
        return args
      })
  }
})
