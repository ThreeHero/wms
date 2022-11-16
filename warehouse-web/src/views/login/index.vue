<template>
  <div class="container">
    <div class="login-div">
      <div class="login-box">
        <el-form :model="login" ref="loginForm" :rules="rules" class="form-box">
          <el-form-item prop="no">
            <el-input
              v-model="login.no"
              placeholder="Please input the account number"
            />
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="login.password" placeholder="Please input a password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loginHandle">Log In</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { login } from '@/api/user'
import { mapMutations } from 'vuex'

export default {
  data() {
    return {
      userInfo: {},
      login: {
        no: 'xiaoliu',
        password: '123456'
      },
      // 校验规则
      rules: {
        no: [
          { required: true, message: '账号不能为空', trigger: 'blur' },
          { pattern: /[a-zA-Z]+/, message: '账号需要包含字母', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 6, max: 15, message: '密码为6-15位', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    ...mapMutations(['setToken']),
    loginHandle() {
      this.$refs.loginForm.validate(async isOK => {
        if (isOK) {
          // 校验成功
          const res = await login(this.login)
          if (res.data.code === 200) {
            // 登录成功
            this.userInfo = res.data.data
            // 存入token
            this.setToken(JSON.stringify(this.userInfo))
            // 页面跳转
            this.$router.push('/')
          } else {
            this.$message.error(res.data.message)
          }
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: #2980b9; /* fallback for old browsers */
  background: -webkit-linear-gradient(45deg, #ffffff, #6dd5fa, #2980b9); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(45deg, #ffffff, #6dd5fa, #2980b9); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

  .login-div {
    width: 500px;
    height: 320px;
    background-color: #ffffff35;
    backdrop-filter: blur(2px);
    border-radius: 20px;

    .login-box {
      margin-top: 57px;
      text-align: center;
    }
  }
}

/deep/.el-input__inner {
  width: 80%;
  margin-bottom: 12px;
  border: 0;
  background-color: transparent;
  border-bottom: 1px solid #2980b9;
  border-radius: 0;
  transition: all .5s;
  box-sizing: border-box;
  color: #333;

  &:focus {
    transform: scale(1.05);
  }
}
/deep/input::-webkit-input-placeholder {
    color: #7e8aa6;
}
/deep/.el-button {
  margin-top: 18px;
  width: 80%;
  transition: all .5s;

  &:hover {
    transform: translateY(-2px);
  }
}
/deep/.el-form-item__error {
  top: 15%;
  left: 75%;
  color: orangered;
}
</style>
