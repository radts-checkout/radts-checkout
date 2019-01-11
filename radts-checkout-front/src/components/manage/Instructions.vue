<template>
  <div class="instructions-container">
    <el-row :gutter="20">
      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-form ref="form" :model="form" :rules="rules" label-width="100px" size="small">
            <el-form-item label="姓名" prop="fromName">
              <el-input v-model="form.fromName"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="fromEmail">
              <el-input v-model="form.fromEmail"></el-input>
            </el-form-item>
            <el-form-item label="意见/建议" prop="fromMsg">
              <el-input type="textarea" v-model="form.fromMsg" :rows="6"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('form')">发送</el-button>
              <el-button @click="resetForm('form')">取消</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Instructions',
  data () {
    let checkEmail = (rule, value, callback) => {
      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
      if (!value) {
        return callback(new Error('邮箱不能为空'))
      }
      setTimeout(() => {
        if (mailReg.test(value)) {
          callback()
        } else {
          callback(new Error('请输入正确的邮箱格式'))
        }
      }, 100)
    }
    return {
      form: {
        fromName: '',
        fromEmail: '',
        fromMsg: ''
      },
      rules: {
        fromName: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 2 到 32 个字符', trigger: 'blur' }
        ],
        fromEmail: [
          { required: true, validator: checkEmail, trigger: 'blur' }
        ],
        fromMsg: [
          { required: true, message: '请输入意见/建议', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('/sendEmail', this.form).then(successResponse => {
            this.responseResult = JSON.stringify(successResponse.data)
            console.log(successResponse.data)
            if (successResponse.data.code === 200) {
              this.$message({
                showClose: true,
                message: '发送成功',
                type: 'success'
              })
              this.resetForm('form')
            } else {
              this.$message({
                showClose: true,
                message: '发送失败',
                type: 'error'
              })
            }
          }).catch(() => {
            this.loading = false
          })
        } else {
          this.$message({
            showClose: true,
            message: '信息验证失败',
            type: 'error'
          })
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.instructions-container{
    margin: 4px 0 0 4px;
  }
</style>
