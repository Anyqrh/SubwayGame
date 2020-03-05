<template>
  <el-dialog :append-to-body="true" :close-on-click-modal="false" :before-close="cancel" :visible.sync="dialog" :title="isAdd ? '新增' : '编辑'" width="500px">
    <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
      <el-form-item label="地铁路线名称" >
        <el-input v-model="form.subwaypathName" style="width: 370px;"/>
      </el-form-item>
      <el-form-item label="所属用户" >
        <el-input v-model="form.userId" style="width: 370px;"/>
      </el-form-item>
      <el-form-item label="城市名称" >
        <el-input v-model="form.cityName" style="width: 370px;"/>
      </el-form-item>
      <el-form-item label="地铁路线" >
        <el-input v-model="form.path" style="width: 370px;"/>
      </el-form-item>
      <el-form-item label="是否完成 0/否 1/是" >
        <el-input v-model="form.finish" style="width: 370px;"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="doSubmit">确认</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { add, edit } from '@/api/gameSubwaypath'
export default {
  props: {
    isAdd: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      loading: false, dialog: false,
      form: {
        subwaypathId: '',
        subwaypathName: '',
        userId: '',
        cityName: '',
        path: '',
        finish: ''
      },
      rules: {
      }
    }
  },
  methods: {
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
        subwaypathId: '',
        subwaypathName: '',
        userId: '',
        cityName: '',
        path: '',
        finish: ''
      }
    }
  }
}
</script>

<style scoped>

</style>
