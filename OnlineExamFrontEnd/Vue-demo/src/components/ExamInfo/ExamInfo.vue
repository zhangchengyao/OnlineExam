<template>
  <div>
    <div class="row">
      <span>考试名称</span>
      <span>{{exam}}</span>
    </div>
    <div class="row">
      <span>考生姓名</span>
      <span>{{name}}</span>
    </div>
    <div class="row">
      <span>考试成绩</span>
      <span>{{mark}}</span>
    </div>
    <div class="buttons">
      <el-button type="primary" @click="generate()">生成试卷</el-button>
      <el-button @click="back()">返回</el-button>
    </div>
  </div>

</template>

<script>
import $ from 'jquery'
import {mapState} from 'vuex'
export default {
  name: 'ExamInfo',
  data () {
    return {
      exam: '',
      name: '',
      mark: ''
    }
  },
  created () {
    let that = this
    that.getInfo()
  },
  computed: {
    ...mapState('auth', {
      loginData: state => state.data
    })
  },
  methods: {
    getInfo () {
      // console.log(this.loginData)
      let that = this
      $.ajax({
        type: 'GET',
        url: 'http://114.115.136.111:8080/exam/show/result',
        dataType: 'json',
        data: {
          examid: that.$route.params.examId,
          studentid: that.$route.params.studentId
        },
        success: function (req) {
          that.exam = req.examName
          that.name = req.name
          that.mark = req.mark
        },
        error: function (req) {
          console.log('失败')
        }
      })
    },
    back () {
      this.$router.push({path: '/student/exam/list/' + this.$route.params.studentId})
    },
    generate () {
      this.$router.push({path: '/student/exam/' + this.$route.params.studentId + '/' + this.$route.params.examId + '/paper'})
    }
  }
}
</script>

<style scoped src="./ExamInfo.css"></style>
