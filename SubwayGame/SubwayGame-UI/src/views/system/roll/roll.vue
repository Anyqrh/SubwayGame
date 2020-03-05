<template>
  <div id="roll">
    <!--    <router-view v-if="isRouterAlive"/>-->
    <router-view/>
  </div>
</template>

<script>
const uploadURL = 'http://localhost:8000/api/musicRoll/rollUpload' // 图片上传访问的后台地址
const roll_state = [{ 'key': 0, 'display_name': '启用' },
  { 'key': 1, 'display_name': '禁用' }]
const imgLimit = 1 // 上传图片个数
const imgSize = 5 // 图片最大为5MB
export default {
  name: 'Roll',
  uploadURL,
  roll_state,
  imgLimit,
  imgSize
}
</script>
