<template>
  <div>
    <el-table :data="list">
      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column label="考试题目" prop="content"></el-table-column>
      <el-table-column label="考试选项">
        <template slot-scope="scope">
          <div>选项A：{{scope.row.a}}</div>
          <div>选项B：{{scope.row.b}}</div>
          <div>选项C：{{scope.row.c}}</div>
          <div>选项D：{{scope.row.d}}</div>
        </template>
      </el-table-column>
      <el-table-column label="考试答案" prop="answer"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <a href="javascript:;" class="delete" @click="del(scope.row)">删除</a>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
import $ from 'jquery'
export default {
  name: 'QuestionListDetail',
  data () {
    return {
      list: [{
        content: '',
        a: '',
        b: '',
        c: '',
        d: '',
        answer: ''
      }]
    }
  },
  created () {
    let that = this
    that.getQuesList()
  },
  methods: {
    getQuesList () {
      let that = this
      console.log('getQuesList')
      $.ajax({
        type: 'GET',
        url: 'http://114.115.136.111:8080/exam/generate/allproblems',
        dataType: 'json',
        data: {
          examid: that.$route.params.examId
        },
        success: function (req) {
          console.log(req)
          that.list = req
        },
        error: function (req) {
          console.log(req)
        }
      })
    },
    del (ques) {
      let that = this
      $.ajax({
        type: 'GET',
        url: 'http://114.115.136.111:8080/exam/delete/problem',
        dataType: 'text',
        data: {
          problemid: ques.id
        },
        success: function () {
          that.getQuesList()
        },
        error: function (req) {
          alert('删除失败')
        }
      })
    }
  }
}
</script>

<style scoped src="./QuestionListDetail.css"></style>
