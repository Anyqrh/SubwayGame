import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/musicPurchaseHistory',
    method: 'post',
    data
  })
}

export function del(purchaseId) {
  return request({
    url: 'api/musicPurchaseHistory/' + purchaseId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/musicPurchaseHistory',
    method: 'put',
    data
  })
}

export function downloadMusicPurchaseHistory(params) {
  return request({
    url: 'api/musicPurchaseHistory/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
