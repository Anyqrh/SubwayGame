import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/musicRechargeHistory',
    method: 'post',
    data
  })
}

export function del(rechargeId) {
  return request({
    url: 'api/musicRechargeHistory/' + rechargeId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/musicRechargeHistory',
    method: 'put',
    data
  })
}

export function downloadMusicRechargeHistory(params) {
  return request({
    url: 'api/musicRechargeHistory/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
