<template>
  <div id="site">
    <!--    <router-view v-if="isRouterAlive"/>-->
    <router-view/>
  </div>
</template>

<script>
    const URL = 'http://localhost:8000/api/gameCity/selectAll' // 访问城市的后台路径
    const SiteNameURL = 'http://localhost:8000/api/gameSite/findBySiteName'
    const cityName = []      // 城市
    const start = [{"key": 0, "value": "否"}, {"key": 1, "value": "是"}]
    export default {
        name: 'Site',
        URL,
        cityName,
        start
    }
</script>

