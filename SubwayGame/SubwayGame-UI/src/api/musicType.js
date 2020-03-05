import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/musicType',
    method: 'post',
    data
  })
}

export function del(typeId) {
  return request({
    url: 'api/musicType/' + typeId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/musicType',
    method: 'put',
    data
  })
}

export function downloadMusicType(params) {
  return request({
    url: 'api/musicType/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
