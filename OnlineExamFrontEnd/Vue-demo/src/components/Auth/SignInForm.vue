<template>
  <div class="card sign-in-form">
    <h3>登录</h3>
    <el-form :model="signInForm" :rules="rules" ref="signInForm" label-position="top">

      <el-form-item label="身份" prop="role" class="role-item">
        <el-radio-group v-model="signInForm.role">
          <el-radio label="教师"></el-radio>
          <el-radio label="学生"></el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="用户名" prop="username">
        <el-input v-model="signInForm.username"></el-input>
      </el-form-item>

      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="signInForm.password" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('signInForm')">登录</el-button>
        <!--<el-button @click="resetForm('signInForm')">重置</el-button>-->
      </el-form-item>

      <router-link :to="'/student/register'" class="register">学生注册</router-link>
      <router-link :to="'/teacher/register'" class="register">教师注册</router-link>

    </el-form>
  </div>
</template>

<script>
  import { router } from '../../main'
  import { mapActions } from 'vuex'
  import $ from 'jquery'

  export default {
    data () {
      let checkUsername = (rule, value, callback) => {
        if (value === '') {
          return callback(new Error('请输入用户名'))
        } else {
          callback()
        }
      }
      let validatePassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
          callback()
        }
      }
      return {
        signInForm: {
          username: '',
          password: '',
          role: ''
        },
        rules: {
          password: [
            {validator: validatePassword, trigger: 'blur'}
          ],
          username: [
            {validator: checkUsername, trigger: 'blur'}
          ],
          role: [
            {required: true, message: '请选择登录身份'}
          ]
        },
        loginUrl: ''
      }
    },
    methods: {
      ...mapActions('auth', [
        'signIn'
      ]),
      signIn () {
        let that = this
        console.log('sign in as ' + this.signInForm.role)
        if (this.signInForm.role === '学生') {
          this.loginUrl = 'http://114.115.136.111:8080/student/login'
        } else {
          this.loginUrl = 'http://114.115.136.111:8080/teacher/login'
        }
        $.ajax({
          type: 'POST',
          url: this.loginUrl,
          dataType: 'json',
          data: {
            email: this.signInForm.username,
            passWord: this.signInForm.password
          },
          success: function (req) {
            if (req === -1) {
              alert('登录失败!')
            } else {
              alert('登录成功！')
              if (that.signInForm.role === '学生') {
                that.$router.push({path: '/student/exam/list/' + req})
              } else {
                that.$router.push({path: '/teacher/question/' + req})
              }
            }
          },
          error: function (req) {
            alert('登录失败')
            console.log(req)
          }
        })
      },
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            const {username, password, role} = this.signInForm
            console.log(password + ' ' + role)
            this.signIn({
              data: {
                username: this.signInForm.username,
                password: this.signInForm.password,
                role: this.signInForm.role
              },
              onSuccess: () => {
                this.$message.success('您好，' + username + '！')
                router.push({ name: 'UserListPage' })
              },
              onError: () => {
                this.$message.error('用户名或密码错误！')
              }
            })
          } else {
//            console.log('error submit!!')
//            Message.error('')
            return false
          }
        })
      }
    }
  }
</script>

<style scoped src="./SignInForm.css"></style>
