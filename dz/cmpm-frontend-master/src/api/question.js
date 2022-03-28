import request from '@/utils/request'

export function drawQuestions(userId,levelId) {
  return request({
    url: '/question/drawQuestions',
    method: 'get',
    params:{
      userId: userId,
      levelId: levelId,
      size:25
    }
  })
}
