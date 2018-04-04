<template>
  <div>
    <ul>
      <li v-for="n in length">
        <i class="mark-button el-icon-star-off"></i>
        <a href="javascript:;" @click="jump(n)">
          {{n}}.  {{list[n-1]}}
        </a>
      </li>
    </ul>

    <el-button type="primary" @click="submit()">提交试卷</el-button>

  </div>
</template>

<script>
import $ from 'jquery'
export default {
  name: 'ExamCheck',
  data () {
    return {
      list: [],
      length: 0,
      quesList: '',
      load_complete: 0,
      markList: []
    }
  },
  created () {
    let that = this
    that.list = that.$route.params.answer.split('.')
    that.length = that.list.length
    that.load_complete = 1
  },
  watch: {
    load_complete () {
      this.getMark()
    }
  },
  methods: {
    getMark () {
      let that = this
      that.markList = that.$route.params.mark.split('.')
      var length = that.markList.length
      if (length > 0) {
        for (var i = 0; i < length; i++) {
          $('.mark-button').eq(that.markList[i] - 1).removeClass('el-icon-star-off')
          $('.mark-button').eq(that.markList[i] - 1).addClass('el-icon-star-on')
        }
      }
    },
    jump (n) {
      let that = this
      that.$router.push({path: '/student/exam/' + that.$route.params.examId + '/question/' + n + '/' + false})
    },
    submit () {
      let that = this
      $.ajax({
        type: 'GET',
        url: 'http://114.115.136.111:8080/exam/result/mark',
        dataType: 'text',
        data: {
          examid: that.$route.params.examId,
          studentid: that.$route.params.studentId,
          studentanswer: that.list.join('~')
        },
        success: function (req) {
          alert('提交成功！')
          that.$router.push({path: '/student/exam/list'})
          console.log('成功')
        },
        error: function (req) {
          console.log(req)
          console.log('失败！')
        }
      })
    }
  }
}
</script>


<style scoped src="./ExamCheck.css"></style>
