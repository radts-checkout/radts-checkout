<template>
  <div class="setting-container">
    <el-row :gutter="20">
      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
             <el-form ref="form" :model="form" label-width="80px" size="small">
              <el-form-item label="接口URL">
                <el-input v-model="form.apiURI"></el-input>
              </el-form-item>
              <el-form-item label="接口模式">
                <el-radio-group v-model="form.interfaceType">
                  <el-radio label="0" >DoBusiness</el-radio>
                  <el-radio label="1" >RunService</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="机构代码">
                <el-input v-model="form.yiliaojgdm"></el-input>
              </el-form-item>
              <!-- <el-form-item label="活动区域">
                <el-select v-model="form.region" placeholder="请选择活动区域">
                  <el-option label="区域一" value="shanghai"></el-option>
                  <el-option label="区域二" value="beijing"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="活动时间">
                <el-col :span="11">
                  <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                  <el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
                </el-col>
              </el-form-item>
              <el-form-item label="即时配送">
                <el-switch v-model="form.delivery"></el-switch>
              </el-form-item>
              <el-form-item label="活动性质">
                <el-checkbox-group v-model="form.type">
                  <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
                  <el-checkbox label="地推活动" name="type"></el-checkbox>
                  <el-checkbox label="线下主题活动" name="type"></el-checkbox>
                  <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="活动形式">
                <el-input type="textarea" v-model="form.desc"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">立即创建</el-button>
                <el-button>取消</el-button>
              </el-form-item> -->
            </el-form>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: 'Setting',
  data () {
    return {
      form: {
        apiURI: '',
        interfaceType: '',
        yiliaojgdm: ''
      }
    }
  },
  methods: {
    onSubmit () {
      console.log('submit!')
    }
  },
  mounted () {
    this.$watch('form', () => {
      localStorage.setItem('setting', JSON.stringify(this.form))
    }, {
      deep: true
    })
    let setting = JSON.parse(localStorage.getItem('setting'))
    if (setting) {
      this.form.apiURI = setting.apiURI
      this.form.interfaceType = setting.interfaceType
      this.form.yiliaojgdm = setting.yiliaojgdm
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
  .setting-container{
    margin: 4px 0 0 4px;
  }
</style>
