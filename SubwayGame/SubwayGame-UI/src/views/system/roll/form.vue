<template>
  <el-dialog :append-to-body="true" :close-on-click-modal="false" :before-close="cancel" :visible.sync="dialog" :title="isAdd ? '新增' : '编辑'" width="500px">
    <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
      <el-form-item>
        <el-upload
          ref="upload"
          v-model="form.rollPicture"
          :limit="imgLimit"
          :file-list="productImgs"
          :multiple="isMultiple"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :on-exceed="handleExceed"
          :on-error="imgUploadError"
          :header="headerObject"
          :action="uploadURL"
          :auto-upload="false"
          class="upload-demo"
          accept="image/*"
          list-type="picture">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
          <div slot="tip" class="el-upload_tip">只能上传jpg/png文件，且不超过5M，数量最多{{ imgLimit }}张</div>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img :src="dialogImageUrl" width="100%" alt="">
        </el-dialog>
      </el-form-item>
      <el-form-item label="轮播图名" >
        <el-input v-model="form.rollName" style="width: 370px;"/>
      </el-form-item>
      <el-form-item label="轮播图地址" >
        <el-p>{{ form.rollPicture }}</el-p>
      </el-form-item>
      <el-form-item label="轮播图状态" >
        <el-select v-model="form.state" size="mini" placeholder=" " style="width: 370px">
          <el-option v-for="item in state" :key="item.key" :value="item.key" :label="item.display_name"/>
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
import { add, edit } from '@/api/musicRoll'
import roll_ from './roll'
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
      uploadURL: roll_.uploadURL,
      dialogVisible: false,
      dialogImageUrl: '',
      isMultiple: true,
      productImgs: [],
      state: roll_.roll_state,
      imgLimit: roll_.imgLimit,
      form: {
        rollId: '',
        rollName: '',
        rollPicture: '',
        state: ''
      },
      rules: {
      }
    }
  },
  methods: {
    headerObject() { // 设置请求头，传token
      window.sessionStorage.getItem('token')
      window.sessionStorage.getItem('Access-Control-Allow-Origin')
    },
    handleRemove(file, fileList) { // 移除图片
      console.log(file, fileList)
    },
    handlePictureCardPreview(file) { // 预览图片时调用
      console.log(file)
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    beforeAvatarUpload(file) { // 文件上传之前调用做一些拦截器现在
      console.log(file)
      // eslint-disable-next-line no-unused-vars
      const isJPG = true
      const extension = file.name.split('.')[1] === 'jpg'
      const extension2 = file.name.split('.')[1] === 'png'
      const extension3 = file.name.split('.')[1] === 'gif'
      const extension4 = file.name.split('.')[1] === 'psd'
      const extension5 = file.name.split('.')[1] === 'jpeg'
      const extension6 = file.name.split('.')[1] === 'pdf'
      const isLt5M = file.zize / 1024 / 1024 <= roll_.imgSize
      if (extension || extension2 || extension3 || extension4 || extension5 || extension6) {
        console.log('上传模板只能是 jpg、jpge、png、gif、pdf、psd')
      }
      if (isLt5M > roll_.imgSize) {
        console.log('上传图片大小超过' + roll_.imgSize + 'M')
      }
      return extension || extension2 || extension3 || extension4 || extension5 || extension6 && isLt5M
    },
    handleAvatarSuccess(response, file) { // 图片上传成功
      this.form.rollPicture = response
      console.log(response)
      console.log(file)
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    handleExceed(files, fileList) { // 图片上传超过数量限制
      this.$message.error('图片不能超过' + roll_.imgLimit + '张！')
      console.log(files, fileList)
    },
    imgUploadError(err, file, fileList) { // 图片上传失败调用
      console.log(err)
      this.$message.error('上传图片失败!')
    },
    submitUpload() {
      this.$refs.upload.submit()
    },
    cancel() {
      this.resetForm()
    },
    doSubmit() {
      this.loading = true
      this.dialogVisible = false
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
        rollId: '',
        rollName: '',
        rollPicture: '',
        state: ''
      }
    }
  }
}
</script>

<style scoped>

</style>
