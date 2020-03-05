import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/gameShi',
    method: 'post',
    data
  })
}

export function del(shiId) {
  return request({
    url: 'api/gameShi/' + shiId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/gameShi',
    method: 'put',
    data
  })
}

export function downloadGameShi(params) {
  return request({
    url: 'api/gameShi/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
