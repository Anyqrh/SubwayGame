import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/classSchedule',
    method: 'post',
    data
  })
}

export function del(scheduleId) {
  return request({
    url: 'api/classSchedule/' + scheduleId,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/classSchedule',
    method: 'put',
    data
  })
}

export function downloadClassSchedule(params) {
  return request({
    url: 'api/classSchedule/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
