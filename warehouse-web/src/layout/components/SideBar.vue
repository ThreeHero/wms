<template>
  <el-aside :width="isCollapse ? '64px' : '200px'" style="background-color: rgb(238, 241, 246); height: 100%">
    <el-menu
      default-active="/home"
      :collapse="isCollapse"
      :collapse-transition="false"
      :router="true"
    >
      <div class="logo">
        <span class="repo">仓库</span>
        <span v-if="!isCollapse">管理系统</span>
      </div>

      <el-menu-item index="/home">
        <i class="el-icon-s-home"></i>
        <span slot="title">个人中心</span>
      </el-menu-item>
      <el-menu-item index="/user">
        <i class="el-icon-user"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
  name: 'SideBar',
  data() {
    return {
      isCollapse: localStorage.getItem('collapse') === 'true'
    }
  },
  mounted() {
    this.$bus.$on('toggleCollapse', collapse => {
      this.isCollapse = collapse
    })
  },
  beforeDestroy() {
    this.$bus.$off('toggleCollapse')
  }
}
</script>

<style lang="less" scoped>
@logoHeight: 60px;

.logo {
  text-align: center;
  height: @logoHeight;
  line-height: @logoHeight;
  user-select: none;
  border-bottom: 1px solid #ccc;
  .repo {
    color: #4588ca;
  }
}

</style>
