<template>
  <div id="user">
    <!--    <router-view v-if="isRouterAlive"/>-->
    <router-view/>
  </div>
</template>

<script>
    const uploadURL = 'http://localhost:8000/api/gameUser/avatarUpload' // 图片上传访问的后台地址
    const imgLimit = 1 // 上传图片个数
    const imgSize = 5 // 图片最大为5MB
    export default {
        name: 'User',
        // 上传图片URL地址
        uploadURL,
        imgLimit,
        imgSize
    }
</script>
