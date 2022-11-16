import service from '@/utils/request'

/**
 * 获取用户分页信息
 * @param {*} params
 * @returns
 */
export function getUserInfo(params) {
  return service({
    url: '/user/page',
    method: 'GET',
    params
  })
}

/**
 * 根据id查询用户信息
 * @param {*} id
 * @returns
 */
export function getUserInfoById(id) {
  return service({
    url: `/user/${id}`,
    method: 'GET'
  })
}

/**
 * 删除用户
 * @param {*} id
 * @returns
 */
export function deleteUser(id) {
  return service({
    url: `/user/${id}`,
    method: 'DELETE'
  })
}

/**
 * 新增用户
 * @param {*} data
 * @returns
 */
export function saveUser(data) {
  return service({
    url: '/user',
    method: 'POST',
    data
  })
}

/**
 * 修改用户
 * @param {*} data
 * @returns
 */
export function updateUser(data) {
  return service({
    url: '/user',
    method: 'PUT',
    data
  })
}

/**
 * 用户登录
 * @param {*} data
 * @returns
 */
export function login(data) {
  return service({
    url: '/user/login',
    method: 'POST',
    data
  })
}

/**
 * 退出登录
 * @returns
 */
export function logout() {
  return service({
    url: '/user/logout',
    method: 'POST'
  })
}
