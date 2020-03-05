import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/musicUser',
    method: 'post',
    data
  })
}

export function del(userId) {
  return request({
    url: 'api/musicUser/' + userId,
    method: 'delete'
  })
}
export function getInfo(userId) {
  return request({
    url: 'api/musicUser',
    method: 'get'
  })
}

export function edit(data) {
  return request({
    url: 'api/musicUser',
    method: 'put',
    data
  })
}

export function downloadMusicUser(params) {
  return request({
    url: 'api/musicUser/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
