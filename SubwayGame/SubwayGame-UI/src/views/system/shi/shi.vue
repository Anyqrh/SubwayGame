<template>
  <div id="shi">
    <!--    <router-view v-if="isRouterAlive"/>-->
    <router-view/>
  </div>
</template>

<script>
    const URL = 'http://localhost:8000/api/gameSheng/selectAll' // 访问城市的后台路径
    const ShiExistURL = "http://localhost:8000/api/gameShi/findExistByShiNameAndShengName" // 根据市级名称查询该市级是否已存在
    const shengName = [] // 城市
    export default {
        name: 'Shi',
        URL,
        shengName,
        ShiExistURL,
    }
</script>
