import request from '@/utils/request'

export function getAll() {
  return request({
    url: '/level/all',
    method: 'get'
  })
}

export function curLevel() {
  return request({
    url: '/level/curLevel',
    method: 'get'
  })
}
