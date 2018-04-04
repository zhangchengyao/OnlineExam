<template>
  <div>
    <el-table :data="list">
      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column label="考试名称" prop="name"></el-table-column>
      <el-table-column label="考试时间">
        <template slot-scope="scope">
          <span>{{scope.row.start_time + ' 至 ' + scope.row.end_time}}</span>
        </template>
      </el-table-column>
      <el-table-column label="试题数量" prop="number"></el-table-column>
      <el-table-column label="试题分值" prop="value"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <a class="delete" @click="check(scope.row)">查看</a>
          <a class="delete" @click="del(scope.row)">删除</a>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
import $ from 'jquery'
export default {
  name: 'QuestionList',
  data () {
    return {
      list: [{
        name: '',
        start_time: '',
        end_time: '',
        number: '',
        value: ''
      }]
    }
  },
  created () {
    let that = this
    that.getExamList()
  },
  methods: {
    getExamList () {
      let that = this
      $.ajax({
        type: 'GET',
        url: 'http://114.115.136.111:8080/exam/show/teacher',
        dataType: 'json',
        data: {
          teacherid: that.$route.params.teacherId
        },
        success: function (req) {
          console.log(req)
          that.list = req
        },
        error: function (req) {
          // console.log(req)
        }
      })
    },
    check (val) {
      this.$router.push({path: '/teacher/question/detail/' + this.$route.params.teacherId + '/' + val.id})
    },
    del (val) {
      let that = this
      $.ajax({
        type: 'POST',
        url: 'http://114.115.136.111:8080/exam/delete/exam',
        dataType: 'text',
        data: {
          examid: val.id
        },
        success: function (req) {
          that.getExamList()
        },
        error: function (req) {
          console.log(req)
        }
      })
    }
  }
}
</script>

<style scoped src="./QuestionList.css"></style>
