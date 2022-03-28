import request from '@/utils/request'

export function getSystemParam() {
  return request({
    url: '/systemParam',
    method: 'get'
  })
}