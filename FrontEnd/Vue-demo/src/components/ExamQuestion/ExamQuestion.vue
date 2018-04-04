<template>
  <div>
    <el-button type="primary" @click="total()">考试汇总</el-button>
    <div class="exam-question">
      <button class="mark-button" @click="mark(num)"></button>
      <div class="question">
        <div class="title">
          {{num}}、{{question.content}}
        </div>
        <el-radio-group v-model="radio" v-if="answerLength === 1">
          <el-radio label="A">A. {{question.a}}</el-radio>
          <el-radio label="B">B. {{question.b}}</el-radio>
          <el-radio label="C">C. {{question.c}}</el-radio>
          <el-radio label="D">D. {{question.d}}</el-radio>
        </el-radio-group>

        <el-checkbox-group v-model="checkbox" v-else>
          <el-checkbox label="A">A. {{question.a}}</el-checkbox>
          <el-checkbox label="B">B. {{question.b}}</el-checkbox>
          <el-checkbox label="C">C. {{question.c}}</el-checkbox>
          <el-checkbox label="D">D. {{question.d}}</el-checkbox>
        </el-checkbox-group>

      </div>
      <el-button type="primary" @click="previous()" :disabled="num === 1">上一题</el-button>
      <el-button type="primary" @click="next()" :disabled="num === max">下一题</el-button>
      <el-button type="primary" @click="submit()">交卷</el-button>

    </div>
  </div>
</template>

<script>
import $ from 'jquery'
var storage = window.localStorage
export default {
  name: 'ExamQuestion',
  data () {
    return {
      num: 0, // 题目编号
      max: 0,
      quesList: [], // 题目数组
      question: '', // 题目对象
      answerLength: 0, // 单选还是多选
      radio: '',
      checkbox: [],
      tempAnswer: [],
      first: true, // 是否第一次进入考试答题页面
      passAnswer: [],
      load_complete: 0,
      markQues: [] // 标注的题号
    }
  },
  created () {
    let that = this
    that.first = that.$route.params.first
    if (that.first === 'false') {
    }
    that.getQues()
  },
  watch: {
    radio () {
      let that = this
      if (that.quesList[that.num - 1].answer.length === 1) {
        that.tempAnswer[that.num - 1] = that.radio
      }
    },
    checkbox () {
      let that = this
      if (that.quesList[that.num - 1].answer.length > 1) {
        that.tempAnswer[that.num - 1] = that.checkbox
      }
    }
  },
  methods: {
    getQues () {
      let that = this
      $.ajax({
        type: 'GET',
        url: 'http://114.115.136.111:8080/exam/generate/problems',
        dataType: 'json',
        data: {
          examid: that.$route.params.examId,
          studentid: that.$route.params.studentId
        },
        success: function (req) {
          if (that.first === 'false') {
            that.getSave()
          }
          if (that.first === 'true') {
            that.quesList = req
            that.max = that.quesList.length
            that.num = that.$route.params.questionId
            that.question = that.quesList[that.num - 1]
            that.answerLength = that.question.answer.length
            that.checkMark(that.num)
            for (var i = 0; i < req.length; i++) {
              if (that.quesList[i].answer.length === 1) {
                that.tempAnswer.push('')
              } else {
                that.tempAnswer.push([])
              }
            }
          }
        },
        error: function () {
          console.log('失败！')
        }
      })
    },
    getSave () {
      let that = this
      $.ajax({
        type: 'GET',
        url: 'http://114.115.136.111:8080/exam/resume/problems',
        dataType: 'json',
        data: {
          examid: that.$route.params.examId,
          studentid: that.$route.params.studentId
        },
        success: function (req) {
          that.quesList = req
          that.max = that.quesList.length
          that.num = that.$route.params.questionId
          that.question = that.quesList[that.num - 1]
          that.answerLength = that.question.answer.length
          that.load_complete = 1
          that.getPass()
        },
        error: function () {
          console.log('失败！')
        }
      })
    },
    isInArray (arr, value) {
      if (arr.length === 0) {
        return -1
      }
      for (var i = 0; i < arr.length; i++) {
        if (value === arr[i]) {
          return i
        }
        if (value + '' === arr[i]) {
          return i
        }
      }
      return -1
    },
    mark (val) {
      let that = this
      var index = that.isInArray(that.markQues, val)
      if (index > -1) {
        that.markQues.splice(index, 1)
      } else {
        that.markQues.push(val)
      }
      $('.mark-button').toggleClass('el-icon-star-on')
      $('.mark-button').toggleClass('el-icon-star-off')
    },
    checkMark (val) {
      let that = this
      var index = that.isInArray(that.markQues, val)
      if (index > -1) {
        $('.mark-button').addClass('el-icon-star-on')
        $('.mark-button').removeClass('el-icon-star-off')
        return true
      } else {
        $('.mark-button').removeClass('el-icon-star-on')
        $('.mark-button').addClass('el-icon-star-off')
        return false
      }
    },
    getPass () {
      let that = this
      that.tempAnswer = storage.getItem('tempAnswer').split('~')
      that.markQues = storage.getItem('markQues').split('~')
      var id = that.$route.params.questionId
      that.checkMark(id)
      for (var n = 0; n < that.max; n++) {
        var length = that.quesList[n].answer.length
        if (length > 1) {
          that.tempAnswer[n] = that.tempAnswer[n].split(',')
        }
      }
      if (that.quesList[that.num - 1].answer.length > 1) {
        that.checkbox = that.tempAnswer[that.num - 1]
      }
      if (that.quesList[that.num - 1].answer.length === 1) {
        that.radio = that.tempAnswer[that.num - 1]
      }
      // eventBus.$on('answer', function (val) {
      //   that.tempAnswer = val.split('.')
      //   that.num = that.$route.params.questionId
      //   if (that.answerLength === 1) {
      //     alert('1')
      //     that.radio = that.tempAnswer[that.num - 1]
      //   }
      //   if (that.answerLength > 1) {
      //     alert('2')
      //     that.checkbox = that.tempAnswer[that.num - 1]
      //   }
      // })
      // eventBus.$on('markList', function (val) {
      // })
    },
    previous () {
      let that = this
      if (that.quesList[that.num - 1].answer.length === 1) { // 单选
        that.tempAnswer[that.num - 1] = that.radio
        if (that.quesList[that.num - 2].answer.length === 1) {
          that.radio = that.tempAnswer[that.num - 2]
        } else {
          that.checkbox = that.tempAnswer[that.num - 2]
        }
      }
      if (that.quesList[that.num - 1].answer.length > 1) { // 多选
        that.tempAnswer[that.num - 1] = that.checkbox

        if (that.quesList[that.num - 2].answer.length === 1) {
          that.radio = that.tempAnswer[that.num - 2]
        } else {
          that.checkbox = that.tempAnswer[that.num - 2]
        }
      }
      if (that.num > 1) {
        that.num--
      } else {
        that.num = 1
      }
      that.question = that.quesList[that.num - 1]
      that.answerLength = that.question.answer.length
      that.checkMark(that.num)
    },
    next () {
      let that = this
      if (that.quesList[that.num - 1].answer.length === 1) { // 单选
        that.tempAnswer[that.num - 1] = that.radio

        if (that.quesList[that.num].answer.length === 1) {
          that.radio = that.tempAnswer[that.num]
        } else {
          that.checkbox = that.tempAnswer[that.num]
        }
      }
      if (that.quesList[that.num - 1].answer.length > 1) { // 多选
        that.tempAnswer[that.num - 1] = that.checkbox

        if (that.quesList[that.num].answer.length === 1) {
          that.radio = that.tempAnswer[that.num]
        } else {
          that.checkbox = that.tempAnswer[that.num]
        }
      }
      if (that.num < that.max) {
        that.num++
      } else {
        that.num = that.max
      }
      that.question = that.quesList[that.num - 1]
      that.answerLength = that.question.answer.length
      that.checkMark(that.num)
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
          studentanswer: that.tempAnswer.join('~')
        },
        success: function (req) {
          alert('提交成功！')
          that.$router.push({path: '/student/exam/list/' + that.$route.params.studentId})
          console.log('成功')
        },
        error: function (req) {
          console.log('失败！')
        }
      })
    },
    total () {
      let that = this
      storage.setItem('tempAnswer', that.tempAnswer.join('~'))
      storage.setItem('markQues', that.markQues.join('~'))
      if (that.markQues.length === 0) {
        this.$router.push({path: '/student/exam/' + this.$route.params.examId + '/check/' + this.tempAnswer.join('.') + '/999'})
      } else {
        this.$router.push({path: '/student/exam/' + this.$route.params.examId + '/check/' + this.tempAnswer.join('.') + '/' + that.markQues.join('.')})
      }
    }
  }
}
</script>


<style scoped src="./ExamQuestion.css"></style>
