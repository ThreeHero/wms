<template>
  <el-header>
    <i class="el-icon-s-fold" v-if="!isCollapse" style="cursor: pointer;" @click="toggleCollapse" />
    <i class="el-icon-s-unfold" v-else style="cursor: pointer;" @click="toggleCollapse" />
    <div style="fontSize: 16px;">
      欢迎{{ JSON.parse($store.state.token).name }}来到仓库管理系统
    </div>
    <el-dropdown trigger="click">
      <span style="margin-right: 15px; cursor: pointer;">
        {{ JSON.parse($store.state.token).name }}
        <i class="el-icon-arrow-down" style="margin-left: 10px"></i>
      </span>
      <el-dropdown-menu class="more-card__dropdown" slot="dropdown">
        <el-dropdown-item>
          <span @click="$router.push('/')">个人中心</span>
        </el-dropdown-item>
        <el-dropdown-item>
          <span @click="logoutHandle">退出登录</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </el-header>
</template>

<script>
import { logout } from '@/api/user'
import { mapMutations } from 'vuex'

export default {
  name: 'NavBar',
  data() {
    return {
      // 是否折叠侧边栏
      isCollapse: localStorage.getItem('collapse') === 'true'
    }
  },
  methods: {
    ...mapMutations(['removeToken']),
    // 切换侧边栏展开或隐藏
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
      localStorage.setItem('collapse', this.isCollapse)
      this.$bus.$emit('toggleCollapse', this.isCollapse)
    },
    // 退出登录
    async logoutHandle() {
      console.log(1)
      const res = await logout()
      if (res.data.code === 200) {
        // 退出登录成功
        this.$emit('退出登录成功')
        // 删除token
        this.removeToken()
        // 路由跳转
        this.$router.push('/login')
      }
    }
  }
}
</script>

<style lang="less" scoped>
.el-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-flow: row nowrap;
  width: 100%;
  background-color: #d3d0cf30;
  border-bottom: 1px solid #ccc;
  color: #333;
  line-height: 60px;
  user-select: none;
}
.more-card__dropdown {
  box-sizing: border-box;
  margin-top: -10px !important;
}
</style>
