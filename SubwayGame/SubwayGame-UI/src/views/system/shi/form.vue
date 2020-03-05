<template>
  <el-dialog :append-to-body="true" :close-on-click-modal="false" :before-close="cancel" :visible.sync="dialog" :title="isAdd ? '新增' : '编辑'" width="500px">
    <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
      <el-form-item label="市" >
        <el-input v-model="form.shiName" style="width: 370px;"/>
      </el-form-item>
      <el-form-item label="所属省份" >
        <el-select size="small" style="width: 370px"
                   v-model="form.shengName"
                   placeholder="请选择省份">
          <el-option v-for="item in sheng" :key="item.shengId" :value="item.shengName" :label="item.shengName"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <h4 align="center">{{tisi}}</h4>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="CS(form.shiName, form.shengName)">确认</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { add, edit } from '@/api/gameShi'
import shi_ from './shi'
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
      tisi: '',
      sheng: [],
      loading: false, dialog: false,
      form: {
        shiId: '',
        shiName: '',
        shengName: ''
      },
      rules: {
      }
    }
  },
  created:function(){
      this.getShengs()
  },
  methods: {
      CS: function(shiName, shengName){
          if(shengName == ''){
              this.tisi = '省级名称不能为空'
              return ;
          }
          if(shiName == ''){
              this.tisi = '市级名称不能为空'
              return ;
          }
          this.$axios.get(shi_.ShiExistURL,
              {params: {
                  shengName,
                  shiName,
                  }
              }).then(res =>{
              this.doSubmit(res.data)
          }).catch(error =>{
              console.log("出现异常 "+error)
          })
      },
    getShengs(){
        this.$axios.get(shi_.URL).then(res => {
            this.sheng = res.data
        })
    },
    cancel() {
      this.tisi = ''
      this.resetForm()
    },
    doSubmit(flag) {
      this.loading = flag
      if (flag === false) {
          this.tisi = '该省份名称已存在'
          return;
       }
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
        shiId: '',
        shiName: '',
        shengName: ''
      }
    }
  }
}
</script>

<style scoped>

</style>
