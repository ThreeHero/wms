<template>
  <div>
    <div class="container">
      <div>
        <el-input
          v-model="name"
          placeholder="请输入姓名"
          suffix-icon="el-icon-search"
          style="width: 240px; margin-right: 15px"
          @keyup.enter.native="getUserInfoByParams"
        >
        </el-input>
         <el-select v-model="sex" placeholder="请选择性别" style="margin-right: 15px;">
          <el-option
            v-for="item in [{value: 0, label: '女'}, {value: 1, label: '男'}]"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <el-button type="warning" plain icon="el-icon-refresh" @click="name = '';sex = ''">重置</el-button>
        <el-button type="primary" plain icon="el-icon-search" @click="getUserInfoByParams">查询</el-button>
      </div>
      <div style="margin-right: 20px;">
        <el-button type="primary" @click="saveUser">新增用户</el-button>
      </div>
    </div>

    <!-- 弹出层 -->
    <save-or-update-user
      :isDialog="isDialog"
      :title="title"
      :isEdit="isEdit"
      :currentId="currentId"
      v-if="isDialog"
      @toggleDialog="isDialog = false"
      @againRequest="getUserInfo()"
    />

    <el-table :data="userList" border style="width: 100%" v-loading="loading">
      <el-table-column align="center" type="index"> </el-table-column>
      <el-table-column align="center" prop="no" label="账号" width="180"> </el-table-column>
      <el-table-column align="center" prop="name" label="姓名" width="180"> </el-table-column>
      <el-table-column align="center" prop="age" label="年龄" width="100"> </el-table-column>
      <el-table-column align="center" prop="roleId" label="角色">
        <template slot-scope="scope">
          <el-tag :type="tagType[scope.row.roleId]">
            <span v-if="scope.row.roleId === 0">超级管理员</span>
            <span v-else-if="scope.row.roleId === 1">管理员</span>
            <span v-else-if="scope.row.roleId === 2">用户</span>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="sex" label="性别" width="100">
        <template slot-scope="scope">
          <el-tag :type="scope.row.sex ? '' : 'success'">
            <span v-if="scope.row.sex === 0">女</span>
            <span v-else>男</span>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="phone" label="电话"> </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="updateUserInfo(scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="deleteUserInfo(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页插件 -->
    <div class="paging">
      <el-pagination
        :page-size="pageSize"
        layout="total, prev, pager, next, jumper"
        :current-page="page"
        :total="total"
        :hide-on-single-page="true"
        @current-change="changePage"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { getUserInfo, deleteUser } from '@/api/user'
import SaveOrUpdateUser from './components/SaveOrUpdateUser.vue'

export default {
  components: {
    SaveOrUpdateUser
  },
  name: 'User',
  data() {
    return {
      page: 1,
      pageSize: 5,
      name: '',
      userList: [],
      tagType: ['danger', '', 'success'],
      total: 0,
      sex: '',
      // 新增用户或修改用户
      title: '新增用户',
      isDialog: false,

      // 加载中
      loading: false,
      isEdit: false,
      currentId: 0
    }
  },
  created() {
    // 获取用户数据
    this.getUserInfo()
  },
  methods: {
    // 获取用户数据
    async getUserInfo() {
      this.loading = true
      const res = await getUserInfo({
        page: this.page,
        pageSize: this.pageSize
      })
      this.loading = false
      if (res.data.code === 200) {
        // 获取用户数据
        this.userList = res.data.data.records
        // 获取总条数
        this.total = res.data.data.total
      } else {
        this.$message.error('获取用户信息失败')
      }
    },
    async getUserInfoByParams() {
      const res = await getUserInfo({
        page: this.page,
        pageSize: this.pageSize,
        name: this.name === '' ? null : this.name,
        sex: this.sex === '' ? null : this.sex
      })
      if (res.data.code === 200) {
        // 获取用户数据
        this.userList = res.data.data.records
        // 获取总条数
        this.total = res.data.data.total
        this.$message.success(res.data.message)
      } else {
        this.$message.error('获取用户信息失败')
      }
    },
    // 删除用户
    async deleteUserInfo({ id }) {
      try {
        await this.$confirm('您确定删除该用户吗', '删除', {
          type: 'warning'
        })
        const res = await deleteUser(id)
        console.log(res)
        this.getUserInfo()
        this.$message.success('删除员工成功')
      } catch (err) {
        this.$message.info('取消删除')
      }
    },
    // 分页查询
    changePage(newPage) {
      this.page = newPage
      console.log(this.page, this.pageSize)
      this.getUserInfo()
    },
    // 新增用户
    saveUser() {
      this.currentId = 0
      this.isEdit = false
      this.title = '新增用户'
      this.isDialog = true
    },
    // 修改用户
    updateUserInfo(row) {
      this.currentId = row.id
      this.isEdit = true
      this.title = '编辑用户'
      this.isDialog = true
    }
  }
}
</script>

<style lang="less" scoped>
.container {
  height: 60px;
  display: flex;
  justify-content: space-between;

  // 分页
  .paging {
    display: flex;
    justify-content: end;
    margin-top: 20px;
    margin-right: 20px;
  }

}
</style>
