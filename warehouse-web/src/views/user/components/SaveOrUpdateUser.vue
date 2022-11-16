<template>
  <el-dialog :title="title" width="600px" :visible="isDialog" :show-close="false" :destroy-on-close="true">
    <el-form label-width="80px" :model="userInfo" :rules="rules" ref="form">
      <el-form-item label="账号" prop="no">
        <el-input placeholder="请输入账号" v-model="userInfo.no" />
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input placeholder="请输入姓名" v-model="userInfo.name" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" placeholder="请输入密码" v-model="userInfo.password" show-password />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input placeholder="请输入年龄" v-model="userInfo.age" />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select placeholder="请选择性别" v-model.number="userInfo.sex">
            <el-option label="男" :value="1"></el-option>
            <el-option label="女" :value="0"></el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input placeholder="请输入电话" v-model="userInfo.phone" />
      </el-form-item>
       <el-form-item label="角色" prop="roleId">
        <el-select placeholder="请选择角色" v-model.number="userInfo.roleId">
            <el-option label="超级管理员" :value="0"></el-option>
            <el-option label="管理员" :value="1"></el-option>
            <el-option label="用户" :value="2"></el-option>
          </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="$emit('toggleDialog')">取 消</el-button>
      <el-button type="primary" @click="saveOrUpdateUser">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { saveUser, getUserInfoById, updateUser } from '@/api/user'

export default {
  props: {
    title: {
      type: String,
      required: true
    },
    isDialog: {
      type: Boolean,
      default: false
    },
    isEdit: {
      type: Boolean,
      default: false
    },
    currentId: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      userInfo: {
        no: '',
        name: '',
        password: '',
        age: '',
        sex: '',
        phone: '',
        roleId: ''
      },
      // 校验规则
      rules: {
        no: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          { pattern: /[a-zA-Z]/, message: '账号需要含有英文字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 15, message: '密码6-15位', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '请输入年龄', trigger: 'blur' },
          {
            pattern: /^\d+$/,
            message: '请输入正确的年龄',
            trigger: 'blur'
          }
        ],
        sex: [
          { required: true, message: '请选择性别', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          {
            pattern: /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/,
            message: '手机号码格式不正确',
            trigger: 'blur'
          }
        ],
        roleId: [
          { required: true, message: '请选择角色', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    if (this.isEdit) {
      this.getUserInfoById()
    }
  },
  methods: {
    // 根据id获取用户信息
    async getUserInfoById() {
      const res = await getUserInfoById(this.currentId)
      this.userInfo = res.data.data
    },
    // 保存或新增用户
    async saveOrUpdateUser() {
      // 校验表单
      this.$refs.form.validate(async isOK => {
        // 判断是否校验成功
        if (isOK) {
          if (this.isEdit) {
            await updateUser(this.userInfo)
          } else {
            await saveUser(this.userInfo)
          }
          // 关闭弹层
          this.$emit('toggleDialog')
          // 重新请求数据
          this.$emit('againRequest')
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
/deep/.el-input__inner {
  margin-left: 15px;
  width: 400px;
}
</style>
