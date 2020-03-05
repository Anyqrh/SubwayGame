import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/gameSubwaypath',
    method: 'post',
    data
  })
}

export function del(subwaypathId) {
  return request({
    url: 'api/gameSubwaypath/' + subwaypathId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/gameSubwaypath',
    method: 'put',
    data
  })
}

export function downloadGameSubwaypath(params) {
  return request({
    url: 'api/gameSubwaypath/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
