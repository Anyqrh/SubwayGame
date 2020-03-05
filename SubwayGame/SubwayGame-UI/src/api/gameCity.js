import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/gameCity',
    method: 'post',
    data
  })
}

export function del(cityId) {
  return request({
    url: 'api/gameCity/' + cityId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/gameCity',
    method: 'put',
    data
  })
}

export function downloadGameCity(params) {
  return request({
    url: 'api/gameCity/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
