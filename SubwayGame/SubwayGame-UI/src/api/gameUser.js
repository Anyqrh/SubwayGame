import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/gameUser',
    method: 'post',
    data
  })
}

export function del(gameId) {
  return request({
    url: 'api/gameUser/' + gameId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/gameUser',
    method: 'put',
    data
  })
}

export function downloadGameUser(params) {
  return request({
    url: 'api/gameUser/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
