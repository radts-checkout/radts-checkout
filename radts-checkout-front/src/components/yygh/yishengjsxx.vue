<template>
  <div class="yishengjsxx-container">
    <el-row :gutter="20">
      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-form ref="yishengjsxxForm" :model="yishengjsxxForm" label-width="80px" size="small">
             <el-form-item label="医生代码">
              <el-input v-model="yishengjsxxForm.yiliaojgdm"></el-input>
            </el-form-item>
              <el-form-item label="医生代码">
              <el-input v-model="yishengjsxxForm.yishengdm"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">调用</el-button>
              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-form ref="yishengjsxxFormMsg" :model="yishengjsxxFormMsg" label-width="80px" size="small">
            <el-form-item label="入参" prop="inMsg">
              <el-input type="textarea" v-model="yishengjsxxFormMsg.inMsg" :rows="6"></el-input>
            </el-form-item>
            <el-form-item label="出参" prop="outMsg">
              <el-input type="textarea" v-model="yishengjsxxFormMsg.outMsg" :rows="6"></el-input>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: 'yishengjsxx',
  data () {
    return {
      yishengjsxxForm: {
        yiliaojgdm: '',
        yishengdm: ''
      },
      yishengjsxxFormMsg: {
        inMsg: `<data>
                <funCode>006202</funCode>
                <frontHospitalId>47100022633042111A1001</frontHospitalId>
                <transferId>9a88bee4-0698-49ba-be27-56d21e3f9ada</transferId>
                <transferNo>SZ2019010700005</transferNo>
                <operateType>3</operateType>
                <operateDoctorId>5435345</operateDoctorId>
                <operateTime>2019-01-07 11:01:09</operateTime>
                  <resultDate>2019/1/11 </resultDate>
                <resultTimeType></resultTimeType>
                <resultTimeRange></resultTimeRange>
                <resultClinicPlanNo>1f9bad547a45fb2a34e39ff2336429c3</resultClinicPlanNo>
                <resultClinicId>7e07c61df346f6a4e0531e31a8c09f8b</resultClinicId>
                <resultClinicNo> 4</resultClinicNo>
                <resultComment></resultComment>
                <cancelReason></cancelReason>
                <visitNo>SZ2019010700005</visitNo>
                  <serialNo></serialNo>
              </data>`,
        outMsg: ''
      }
    }
  },
  methods: {
    onSubmit () {
      return true
    }
  },
  mounted () {
    let setting = JSON.parse(localStorage.getItem('setting'))
    this.yishengjsxxForm.yiliaojgdm = setting.yiliaojgdm
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
  .yishengjsxx-container{
    margin: 4px 0 0 4px;
  }
</style>
