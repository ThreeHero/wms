// axios的封装
import axios from 'axios'

// 设置超时时间
const TimeOut = 5000

const service = axios.create({
  baseURL: 'http://127.0.0.1:8090',
  timeout: TimeOut
})

// 请求拦截器

// 相应拦截器

export default service
