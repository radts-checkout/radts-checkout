<template>
  <div class="login_page">
    <m-header></m-header>
    <hr/>
    <el-form :model="loginInfoVo" :rules="rules" ref="loginInfoVo">
      <el-form-item prop="username">
        <el-input v-model="loginInfoVo.username" placeholder="用户名"><span>dsfsf</span></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" placeholder="密码" v-model="loginInfoVo.password"></el-input>
      </el-form-item>
      <el-form-item>
          <el-button type="primary" @click="login()" class="submit_btn">登陆</el-button>
        </el-form-item>
    </el-form>
      登录验证情况：<textarea cols="30" rows="10" v-model="responseResult"></textarea>
    <hr/>
    <m-footer></m-footer>
  </div>
</template>

<script>
import MHeader from '@/components/common/Header'
import MFooter from '@/components/common/Footer'

export default {
  name: 'Login',
  // blogHeader、blogFooter组件给申明到components里面然后在template里面使用
  components: { MHeader, MFooter },
  data () {
    return {
      loginInfoVo: { username: '', password: '' },
      responseResult: [],
      rules: {
					username: [
			            { required: true, message: '请输入用户名', trigger: 'blur' },
			        ],
					password: [
						{ required: true, message: '请输入密码', trigger: 'blur' }
					],
				},
    }
    
  },
  methods: {
    login () {
      this.$axios
        .post('/login', {
          username: this.loginInfoVo.username,
          password: this.loginInfoVo.password
        })
        .then(successResponse => {
          this.responseResult = JSON.stringify(successResponse.data)
          if (successResponse.data.code === 200) {
            this.$router.replace({path: '/index'})
          }
        })
        .catch(failResponse => {})
    }
  }
}
</script>

<style lang="less" scoped>
	.login_page{
		background-color: #324057;
	}

</style>
