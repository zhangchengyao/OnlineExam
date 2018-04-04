<template>
  <div class="card form">
    <h3>学生注册</h3>
    <el-form label-position="left" label-width="80px" :model="register">
      <el-form-item label="邮箱">
        <el-input v-model="register.email" class="username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="register.password" class="password"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="register.name" class="password"></el-input>
      </el-form-item>
      <el-form-item label="学号">
        <el-input v-model="register.id" class="password"></el-input>
      </el-form-item>
      <el-form-item label="班级">
        <el-input v-model="register.class" class="password"></el-input>
      </el-form-item>
      <el-form-item label="年级">
        <el-input v-model="register.grade" class="password"></el-input>
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
          email: '',
          password: '',
          name: '',
          id: '',
          grade: '',
          class: ''
        }
      }
    },
    methods: {
      back: function () {
        this.$router.push({path: '/student/login'})
      },
      doRegister: function () {
        let that = this
        $.ajax({
          type: 'POST',
          url: 'http://114.115.136.111:8080/student/register',
          dataType: 'json',
          data: {
            email: that.register.email,
            password: that.register.password,
            name: that.register.name,
            stNo: that.register.id,
            stGrade: that.register.grade,
            stClass: that.register.class
          },
          success: function (req) {
            alert('注册成功！')
            that.$router.push({path: '/student/login'})
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

