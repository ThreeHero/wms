<template>
  <div class="container">
    <!-- 大标题 -->
    <div class="title">
      <div :class="isTitle ? 'text' : 'text1'">
        {{ userInfo.name }}
      </div>
      <el-switch
        class="switch"
        v-model="isTitle"
        active-color="#74b9ff"
        inactive-color="#fdcb6e">
      </el-switch>
    </div>

    <el-descriptions style="marginTop: 30px" title="个人中心" :column="1" :size="medium" border>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-collection-tag"></i>
          账号
        </template>
        {{ userInfo.no }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          姓名
        </template>
        {{ userInfo.name }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-mobile-phone"></i>
          手机号
        </template>
        {{ userInfo.phone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-male" v-if="userInfo.sex"></i>
          <i class="el-icon-female" v-else></i>
          性别
        </template>
        <el-tag size="small" :type="userInfo.sex ? '' : 'success'">
          {{ userInfo.sex === 0 ? '女' : '男' }}
        </el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-s-custom"></i>
          角色
        </template>
        <el-tag size="small" :type="tagType[userInfo.roleId]">
          <span v-if="userInfo.roleId === 0">超级管理员</span>
          <span v-else-if="userInfo.roleId === 1">管理员</span>
          <span v-else-if="userInfo.roleId === 2">用户</span>
        </el-tag>
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
export default {
  // 个人中心
  name: 'PersonalCenter',
  data() {
    return {
      userInfo: JSON.parse(this.$store.state.token),
      tagType: ['danger', '', 'success'],
      isTitle: true
    }
  }
}
</script>

<style lang="less" scoped>
.title {
  position: relative;
  .switch {
    position: absolute;
    top: 50px;
    right: 50px;
  }
}
.text1 {
  height: 115px;
  text-align: center;
  letter-spacing: 2rem;
  font-size: 100px;
  font-family: 'KaiTi';
  user-select: none;
  color: #00a2c8;
}

.text {
  height: 115px;
  text-align: center;
  letter-spacing: 2rem;
  font-size: 100px;
  background-image: -webkit-linear-gradient(left, #005AA7, #FFFDE4 25%, #0082c8 50%, #005AA7 75%, #FFFDE4);
  -webkit-text-fill-color: transparent;
  background-clip: text;
  background-size: 200% 100%;
  animation: maskedAnimation 4s infinite linear;
  font-family: 'KaiTi';
  user-select: none;
}
@keyframes maskedAnimation {
  0% {
    background-position: 0 0;
  }

  100% {
    background-position: -100% 0;
  }
}

/deep/.el-descriptions-item__cell {
  height: 60px;
}
</style>
