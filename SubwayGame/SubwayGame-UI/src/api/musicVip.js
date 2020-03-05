import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/musicVip',
    method: 'post',
    data
  })
}

export function del(vipId) {
  return request({
    url: 'api/musicVip/' + vipId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/musicVip',
    method: 'put',
    data
  })
}

export function downloadMusicVip(params) {
  return request({
    url: 'api/musicVip/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
