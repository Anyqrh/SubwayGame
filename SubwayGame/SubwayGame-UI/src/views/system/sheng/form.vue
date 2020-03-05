<template>
  <el-dialog :append-to-body="true" :close-on-click-modal="false" :before-close="cancel" :visible.sync="dialog" :title="isAdd ? '新增' : '编辑'" width="500px">
    <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
      <el-form-item label="省" >
        <el-input v-model="form.shengName" id="shengInputId" style="width: 370px;"/>
      </el-form-item>
      <div>
        <h4 align="center" color="#FF0000">{{tisi}}</h4>
      </div>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="CS(form.shengName)">确认</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { add, edit } from '@/api/gameSheng'
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
      sheng_name: '',
      tisi: '',
      loading: false, dialog: false,
      form: {
        shengId: '',
        shengName: ''
      },
      rules: {
      }
    }
  },
  methods: {
    CS: function(shengName){
        if(shengName == ''){
            this.tisi = '省份名称不能为空'
            return ;
        }
        this.$axios.get("http://localhost:8000/api/gameSheng/findExistByShengName",
            {params: {
                shengName,
            }
        }).then(res =>{
           this.doSubmit(res.data)
        }).catch(error =>{
            console.log("出现异常 "+error)
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
        shengId: '',
        shengName: ''
      }
    }
  }
}
</script>

<style scoped>

</style>
