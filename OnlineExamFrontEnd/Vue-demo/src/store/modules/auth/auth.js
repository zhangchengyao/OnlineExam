import $ from 'jquery'

const state = {
  signInForm: {
    username: '',
    password: '',
    role: ''
  }
}

// actions
// const actions = {}

// mutations
const mutations = {}

export default {
  namespaced: true,
  state,
  // getters,
  actions: {
    signIn () {
      let self = this
      console.log('auth signIn')
      console.log(self.signInForm.username)
      $.ajax({
        type: 'POST',
        url: 'http://127.0.0.1:8080/student/login',
        dataType: 'json',
        data: {
          email: self.signInForm.username,
          passWord: self.signInForm.password
        },
        success: function (req) {
          alert('登录成功！')
          alert(req)
        // this.$router.push({path: '/student/exam/list'})
        },
        error: function (req) {
          alert('登录失败')
          console.log(req)
        }
      })
    }
  },
  mutations
}
