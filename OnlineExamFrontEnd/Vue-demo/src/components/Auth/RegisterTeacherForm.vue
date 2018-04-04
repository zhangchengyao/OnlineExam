<template>
  <div class="card form">
    <h3>教师注册</h3>
    <el-form label-position="left" label-width="80px" :model="register">
      <el-form-item label="工号">
        <el-input v-model="register.id" class="username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="register.password" class="password"></el-input>
      </el-form-item>
      <el-form-item label="授课课程">
        <el-input v-model="register.course" class="password"></el-input>
      </el-form-item>
      <div class="btn-box">
        <el-button @click="back">登录</el-button>
        <el-button type="primary" @click="doRegister">注册</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
  import $ from 'jquery'
  export default {
    name: 'login',
    data () {
      return {
        register: {
          id: '',
          password: '',
          course: ''
        }
      }
    },
    methods: {
      back: function () {
        this.$router.push({path: '/teacher/login'})
      },
      doRegister: function () {
        let that = this
        $.ajax({
          type: 'POST',
          url: 'http://114.115.136.111:8080/teacher/register',
          dataType: 'json',
          data: {
            account: that.register.id,
            password: that.register.password,
            courses: that.register.course
          },
          success: function (req) {
            alert('注册成功！')
            that.$router.push({path: '/teacher/login'})
          },
          error: function () {
            alert('注册失败！')
          }
        })
      }
    }
  }
</script>

<style scoped src="./RegisterForm.css"></style>
