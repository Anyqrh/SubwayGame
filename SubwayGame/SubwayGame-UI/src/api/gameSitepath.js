import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/gameSitepath',
    method: 'post',
    data
  })
}

export function del(sitepathId) {
  return request({
    url: 'api/gameSitepath/' + sitepathId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/gameSitepath',
    method: 'put',
    data
  })
}

export function downloadGameSitepath(params) {
  return request({
    url: 'api/gameSitepath/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
