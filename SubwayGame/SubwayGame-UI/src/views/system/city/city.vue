<template>
  <div id="city">
    <!--    <router-view v-if="isRouterAlive"/>-->
    <router-view/>
  </div>
</template>

<script>
    const shengURL = 'http://localhost:8000/api/gameSheng/selectAll' // 获取省份的接口
    const shiURL = 'http://localhost:8000/api/gameShi/findShiNameByShengName'
    const sheng = []      // 城市
    const shi = []
    const start = [{"key": 0, "value": "否"}, {"key": 1, "value": "是"}]
    export default {
        name: 'City',
        shengURL,
        shiURL,
        sheng,
        shi,
        start
    }
</script>
