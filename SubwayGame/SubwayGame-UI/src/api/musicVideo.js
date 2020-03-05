import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/musicVideo',
    method: 'post',
    data
  })
}

export function del(videoId) {
  return request({
    url: 'api/musicVideo/' + videoId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/musicVideo',
    method: 'put',
    data
  })
}

export function downloadMusicVideo(params) {
  return request({
    url: 'api/musicVideo/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
