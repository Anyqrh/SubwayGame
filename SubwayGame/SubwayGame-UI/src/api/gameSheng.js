import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/gameSheng',
    method: 'post',
    data
  })
}

export function del(shengId) {
  return request({
    url: 'api/gameSheng/' + shengId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/gameSheng',
    method: 'put',
    data
  })
}

export function downloadGameSheng(params) {
  return request({
    url: 'api/gameSheng/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
