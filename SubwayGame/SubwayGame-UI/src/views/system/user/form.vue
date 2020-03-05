<template>
  <el-dialog :append-to-body="true" :close-on-click-modal="false" :before-close="cancel" :visible.sync="dialog" :title="isAdd ? '新增' : '编辑'" width="500px">
    <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
      <el-form-item label="用户账号" >
        <el-input v-model="form.gameAccount" style="width: 370px;"/>
      </el-form-item>
      <el-form-item label="昵称" >
        <el-input v-model="form.gameName" style="width: 370px;"/>
      </el-form-item>
      <el-form-item>
        <el-upload
          ref="upload"
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
          <!--
          action: 图片上传的地址
          show-file-list: 是否显示文件上传列表
          accept: 可接受的上传类型，image/*为图片
          headers: 头部信息
          on-success: 上传成功事件
          on-error: 上传失败事件
          before-upload: 上传前处理事件，返回一个值，值为false将阻止上传
          on-progress: 上传中事件
          -->
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img :src="dialogImageUrl" width="100%" alt="">
        </el-dialog>
      </el-form-item>
      <el-form-item label="头像路径" >
        <el-input v-model="form.gameAvatar" style="width: 370px;"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="doSubmit">确认</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { add, edit } from '@/api/gameUser'
import user_ from './user'
export default {
  props: {
    isAdd: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      uploadURL: user_.uploadURL,
      dialogImageUrl: '',
      dialogVisible: false,
      productImgs: [],
      isMultiple: true,
      imgLimit: user_.imgLimit,
      loading: false, dialog: false,
      form: {
        gameId: '',
        gameAccount: '',
        gameName: '',
        gameAvatar: ''
      },
      rules: {
      }
    }
  },
  methods: {
      headerObject() { // 设置请求头，传token
          window.sessionStorage.getItem('token')
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
          const isLt5M = file.size / 1024 / 1024 <= user_.imgSize
          if (extension || extension2 || extension3 || extension4 || extension5 || extension6) {
              console.log('上传模板只能是 jpg、jpge、png、gif、pdf、psd')
          }
          if (isLt5M > user_.imgSize) {
              console.log('上传图片大小超过' + user_.imgSize + 'M')
          }
          return extension || extension2 || extension3 || extension4 || extension5 || extension6 && isLt5M
      },
      handleAvatarSuccess(response, file) { // 图片上传成功
          this.form.gameAvatar = response
          console.log(response)
          console.log(file)
          this.imageUrl = URL.createObjectURL(file.raw)
      },
      handleExceed(files, fileList) { // 图片上传超过数量限制
          this.$message.error('图片不能超过' + user_.imgLimit + '张！')
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
        gameId: '',
        gameAccount: '',
        gameName: '',
        gameAvatar: ''
      }
    }
  }
}
</script>

<style scoped>

</style>
