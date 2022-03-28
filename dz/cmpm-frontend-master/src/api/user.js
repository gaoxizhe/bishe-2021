import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    params:data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function changePassword(data) {
  return request({
    url: '/user/changePassword',
    method: 'post',
    params: data
  })
}

export function logout(token) {
  return request({
    url: '/logout',
    method: 'post',
    params: { token }
  })
}

export function userRank(levelId,userId) {
  return request({
    url: '/userRank/my',
    method: 'get',
    params: { levelId, userId }
  })
}


export function register(data) {
  return request({
    url: '/register',
    method: 'post',
    data
  })
}

export function registerEnd() {
  return request({
    url: '/registerEnd',
    method: 'get',
  })
}



export function getAllTeam() {
  return request({
    url: '/team',
    method: 'get'
  })
}


