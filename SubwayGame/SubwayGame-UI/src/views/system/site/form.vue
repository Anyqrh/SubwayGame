<template>
  <el-dialog :append-to-body="true" :close-on-click-modal="false" :before-close="cancel" :visible.sync="dialog" :title="isAdd ? '新增' : '编辑'" width="500px">
    <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
      <el-form-item label="站点名称" >
        <el-input v-model="form.siteName" style="width: 370px;"/>
      </el-form-item>
      <el-form-item label="所属城市" prop="dep">
        <el-select v-model="form.cityName" size="mini" placeholder=" " style="width: 370px">
          <el-option v-for="item in cityName" :key="item.cityId" :value="item.cityName" :label="item.cityName"/>
        </el-select>
      </el-form-item>
      <el-form-item label="是否启用" >
        <el-select v-model="form.start" size="mini" placeholder=" " style="width: 370px">
          <el-option v-for="item in start" :key="item.key" :value="item.key" :label="item.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="图片">
        <div v-for="person in personList" id="wm">
          <img style="width: 370px":src="person.img">
        </div>
      </el-form-item>
      <el-form-item label="图片1">
        <!--#include virtural="tp01.html" -->
      </el-form-item>

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="doSubmit">确认</el-button>
    </div>
  </el-dialog>
</template>

<script type="text/javascript">

import { add, edit } from '@/api/gameSite'
import site_ from './site'
import Vue from 'vue'
import Axios from 'axios'
import body from '../../../../static/1.jpg'
Vue.prototype.$axios = Axios
export default {
  props: {
    isAdd: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      cityName: [],
      start: site_.start,
      loading: false, dialog: false,
      form: {
        siteId: '',
        siteName: '',
        cityName: '',
        start: ''
      },
      rules: {
      }
    }
  },
  created: function() {
      this.getDep();
  },
  mounted(){
  },
  methods: {
      getDep: function() {
          this.$axios.get(site_.URL).then(res => {
              this.cityName = res.data
          }).catch(function(error) {
              this.$message.error('错误!' + '   ' + error)
          })
          this.personList = [{
              'img': body
          }]
      },
    cancel() {
      this.resetForm()
    },
    doSubmit() {
      this.loading = true
      if (this.isAdd) {
        this.doAdd()
      } else this.doEdit()
    },
    doAdd() {
      add(this.form).then(res => {
        this.resetForm()
        this.$notify({
          title: '添加成功',
          type: 'success',
          duration: 2500
        })
        this.loading = false
        this.$parent.init()
      }).catch(err => {
        this.loading = false
        console.log(err.response.data.message)
      })
    },
    doEdit() {
      edit(this.form).then(res => {
        this.resetForm()
        this.$notify({
          title: '修改成功',
          type: 'success',
          duration: 2500
        })
        this.loading = false
        this.$parent.init()
      }).catch(err => {
        this.loading = false
        console.log(err.response.data.message)
      })
    },
    resetForm() {
      this.dialog = false
      this.$refs['form'].resetFields()
      this.form = {
        siteId: '',
        siteName: '',
        cityName: '',
        start: ''
      }
    }
  }
}
</script>

<style scoped>
</style>
