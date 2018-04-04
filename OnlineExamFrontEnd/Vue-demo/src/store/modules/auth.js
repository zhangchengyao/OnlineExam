import $ from 'jquery'

const state = {
  signInForm: {
    username: '',
    password: '',
    role: ''
  }
}

// actions
const actions = {
  signIn () {
    console.log('signIn')
    $.ajax({
      type: 'POST',
      url: 'http://127.0.0.1:8080/student/login',
      dataType: 'json',
      data: {
        email: this.signInForm.username,
        passWord: this.signInForm.password
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
}

// mutations
const mutations = {}

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
