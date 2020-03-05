import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/musicComment',
    method: 'post',
    data
  })
}

export function del(commentId) {
  return request({
    url: 'api/musicComment/' + commentId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/musicComment',
    method: 'put',
    data
  })
}

export function downloadMusicComment(params) {
  return request({
    url: 'api/musicComment/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
