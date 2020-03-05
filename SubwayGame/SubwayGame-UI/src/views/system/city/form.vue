<template>
  <el-dialog :append-to-body="true" :close-on-click-modal="false" :before-close="cancel" :visible.sync="dialog" :title="isAdd ? '新增' : '编辑'" width="500px">
    <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
      <el-form-item label="城市名称" >
        <el-input v-model="form.cityName" style="width: 370px;"/>
      </el-form-item>
      <el-form-item label="所在地方">
        <el-select size="small" style="width: 183px"
                   v-model="form.shengName"
                   placeholder="请选择省份"
                   v-on:change="selectshengId">
          <el-option v-for="item in shengs" :key="item.shengId" :value="item.shengName" :label="item.shengName"/>
        </el-select>
        <el-select size="small" style="width: 182px"
                   v-model="form.shiName"
                   placeholder="请选择市级"
                   >
          <el-option v-for="item in shis" :key="item" :label="item" :value="item"/>
        </el-select>
      </el-form-item>
      <el-form-item label="是否启用" >
        <el-select v-model="form.start" size="mini" placeholder=" " style="width: 370px">
          <el-option v-for="item in start" :key="item.key" :value="item.key" :label="item.value"/>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="doSubmit">确认</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { add, edit } from '@/api/gameCity'
import city_ from './city'
import Vue from 'vue'
import Axios from 'axios'
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
      shengs: '',
      shis: '',
      start: city_.start,
      loading: false, dialog: false,
      form: {
        cityId: '',
        cityName: '',
        shengName: '',
        shiName: '',
        start: ''
      },
      rules: {
      }
    }
  },
  created:function(){
      this.getShengIds()
  },
  methods: {
    getShengIds: function(){
      this.$axios({
          method: 'get',
          url: city_.shengURL,
      }).then(res => {
        this.shengs = res.data
      }).catch(error=>{
          console.log(error);
      })
    },
    selectshengId: function(shengName){
        this.shis = ''
        this.$axios.get(city_.shiURL,{
            params: {
                shengName
            }
        }).then(res => {
            if(res.data == '') {
                this.form.shiName = ''
                this.this = ''
            }else {
                this.shis = res.data
            }
        }).catch(error=>{
            console.log(error);
        })
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
        cityId: '',
        cityName: '',
        shengName: '',
        shiName: '',
        start: ''
      }
    }
  }
}
</script>

<style scoped>

</style>
