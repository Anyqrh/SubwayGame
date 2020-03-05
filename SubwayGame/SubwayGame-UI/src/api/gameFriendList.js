import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/gameFriendList',
    method: 'post',
    data
  })
}

export function del(friendlistId) {
  return request({
    url: 'api/gameFriendList/' + friendlistId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/gameFriendList',
    method: 'put',
    data
  })
}

export function downloadGameFriendList(params) {
  return request({
    url: 'api/gameFriendList/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
