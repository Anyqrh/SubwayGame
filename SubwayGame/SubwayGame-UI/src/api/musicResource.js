import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/musicResource',
    method: 'post',
    data
  })
}

export function del(resourceId) {
  return request({
    url: 'api/musicResource/' + resourceId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/musicResource',
    method: 'put',
    data
  })
}

export function downloadMusicResource(params) {
  return request({
    url: 'api/musicResource/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
