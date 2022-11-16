<template>
  <el-container class="container">
    <!-- 侧边栏 -->
    <side-bar />
    <!-- 右侧盒子区域 -->
    <div class="right-box">
      <!-- 顶部导航栏 -->
      <nav-bar />
      <!-- 主体区域 -->
      <app-main />
    </div>
  </el-container>
</template>

<script>
import SideBar from '@/layout/components/SideBar'
import NavBar from '@/layout/components/NavBar'
import AppMain from '@/layout/components/AppMain'

export default {
  name: 'Layout',
  components: {
    SideBar,
    NavBar,
    AppMain
  }
}
</script>

<style lang="less" scoped>
.container {
  height: 100%;
  .right-box {
    width: 100%;
  }
}

</style>
