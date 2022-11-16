import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: localStorage.getItem('token')
  },
  getters: {
  },
  mutations: {
    // 存储token
    setToken(state, token) {
      state.token = token
      localStorage.setItem('token', token)
    },
    removeToken(state) {
      state.token = ''
      localStorage.removeItem('token')
    }
  },
  actions: {
  },
  modules: {
  }
})
