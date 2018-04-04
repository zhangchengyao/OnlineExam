<template>
  <div>
    <el-button type="primary" @click="goBack()">返回</el-button>
    <div class="exam-question">
      <div class="exam-title">{{name}}考试分析</div>
      <div class="exam-mark">考试得分：{{mark}}</div>
      <div class="question" v-for="(item,index) in problems" :key="index">
        <div class="title">
          {{index + 1}}、{{item.content}}
        </div>
        <div>分值：{{value}}</div>
        <div class="true-answer" :class="{'false': item.answer != studentAnswers[index]}">正确答案：{{item.answer}}</div>
        <div>选择答案：{{studentAnswers[index]}}</div>
        <el-radio-group v-model="choice[index]" v-if="item.answer.length === 1"  :disabled="true">
          <el-radio label="A">A. {{item.a}}</el-radio>
          <el-radio label="B">B. {{item.b}}</el-radio>
          <el-radio label="C">C. {{item.c}}</el-radio>
          <el-radio label="D">D. {{item.d}}</el-radio>
        </el-radio-group>
        <el-checkbox-group v-model="choice[index]" v-else :disabled="true">
          <el-checkbox label="A">A. {{item.a}}</el-checkbox>
          <el-checkbox label="B">B. {{item.b}}</el-checkbox>
          <el-checkbox label="C">C. {{item.c}}</el-checkbox>
          <el-checkbox label="D">D. {{item.d}}</el-checkbox>
        </el-checkbox-group>

      </div>
      

    </div>
  </div>
</template>

<script>
import $ from 'jquery'
// import eventBus from '../../bus.js'
export default {
  name: 'ExamQuestion',
  data () {
    return {
      examData: [],
      name: '',
      problems: [],
      mark: 0,
      value: 0,
      studentAnswers: [],
      choice: []
    }
  },
  created () {
    let that = this
    that.getInfo()
  },
  watch: {
  },
  methods: {
    getInfo () {
      let that = this
      $.ajax({
        type: 'GET',
        url: 'http://114.115.136.111:8080/exam/show/exam/detail',
        dataType: 'json',
        data: {
          examid: that.$route.params.examId,
          studentid: that.$route.params.studentId
        },
        success: function (req) {
          console.log(req)
          that.examData = req
          that.name = req.name
          that.problems = req.problems
          that.mark = req.mark
          that.value = req.value
          that.studentAnswers = req.studentAnswers.split('~')
          var answers = req.studentAnswers.split('~')
          for (var i = 0; i < answers.length; i++) {
            console.log(answers[i])
            if (that.problems[i].answer.length > 1) {
              var array = answers[i].split(',')
              that.choice.push(array)
            }
            if (that.problems[i].answer.length === 1) {
              that.choice.push(answers[i])
            }
          }
        },
        error: function (req) {
          console.log('失败')
        }
      })
    },
    goBack () {
      this.$router.push({path: '/student/exam/' + this.$route.params.studentId + '/list/' + this.$route.params.examId + '/info'})
    }
  }
}
</script>


<style scoped src="./ExamPaper.css"></style>
