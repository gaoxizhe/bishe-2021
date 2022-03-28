import request from '@/utils/request'

export function challengeStart(userId,levelId) {
  return request({
    url: '/record/start',
    method: 'post',
    params: {
      userId: userId,
      levelId: levelId
    }
  })
}

export function submit(data) {
  return request({
    url: '/record/submitAnswer',
    method: 'post',
    data
  })
}

export function cacheSubmit(data) {
  return request({
    url: '/record/cacheSubmit',
    method: 'post',
    data
  })
}




