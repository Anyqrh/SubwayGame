import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/gameSite',
    method: 'post',
    data
  })
}

export function del(siteId) {
  return request({
    url: 'api/gameSite/' + siteId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/gameSite',
    method: 'put',
    data
  })
}

export function downloadGameSite(params) {
  return request({
    url: 'api/gameSite/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
