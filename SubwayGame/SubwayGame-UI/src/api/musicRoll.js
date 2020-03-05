import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/musicRoll',
    method: 'post',
    data
  })
}

export function del(rollId) {
  return request({
    url: 'api/musicRoll/' + rollId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/musicRoll',
    method: 'put',
    data
  })
}

export function downloadMusicRoll(params) {
  return request({
    url: 'api/musicRoll/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
