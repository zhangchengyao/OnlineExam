<template>
  <div>
    <el-table :data="exam">
      <el-table-column label="考试名称" prop="name"></el-table-column>
      <el-table-column label="时间">
        <template slot-scope="scope">
          {{scope.row.start_time}}  至  {{scope.row.end_time}}
        </template>
      </el-table-column>
      <el-table-column label="分数" prop="value">
        <template slot-scope="scope">
          <span v-if="scope.row.value === -1">--</span>
          <span v-else>{{scope.row.value}}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="status">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 0">未开始</span>
          <span v-if="scope.row.status === 1">进行中</span>
          <span v-if="scope.row.status === 2">已结束</span>
          <span v-if="scope.row.status === 3">已弃考</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <!-- <span v-if="try_to_enter === 0"> -->
            <button class="info" v-if="scope.row.status === 1" @click="trial(scope.row)">进入考试</button>
            <button class="info" v-if="scope.row.status === 2 || scope.row.status === 3" @click="check(scope.row)">查看考试</button>
          <!-- </span> -->
          <!-- <span v-if="try_to_enter === 1 && click_enter_row === scope.row">
            <el-input v-model="verify_code"></el-input>
            <el-button type="primary" size="tiny" @click="verify()">确认</el-button>
          </span> -->
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      class="pagination"
      layout="prev, pager, next"
      :total="total"
      :page-size="limit"
    >
    </el-pagination>


    <el-dialog
      title="请输入考试验证码"
      :visible.sync="dialogVisible"
      :exam.sync="enter_exam"
      size="tiny"
      >
      <el-input v-model="verify_code"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="verify()">确 定</el-button>
      </span>
    </el-dialog>
  </div>

</template>

<script>
import $ from 'jquery'
export default {
  name: 'studentExam',
  data () {
    return {
      exam: [{
        name: '',
        start_time: '',
        end_time: '',
        value: '',
        status: 0
      }],
      total: 0,
      limit: 10,
      dialogVisible: false,
      enter_exam: {},
      try_to_enter: 0,
      verify_code: ''
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
        url: 'http://114.115.136.111:8080/exam/show/student',
        dataType: 'json',
        data: {
          studentid: that.$route.params.studentId
        },
        success: function (req) {
          that.exam = req
          that.total = that.exam.length
        },
        error: function () {
          alert('查询失败！')
        }
      })
    },
    trial (exam) {
      let that = this
      that.enter_exam = exam
      that.dialogVisible = true
    },
    verify () {
      let that = this
      that.dialogVisible = false
      $.ajax({
        type: 'GET',
        url: 'http://114.115.136.111:8080/exam/get/exam/code',
        dataType: 'text',
        data: {
          studentid: that.$route.params.studentId,
          examid: that.enter_exam.id
        },
        success: function (req) {
          console.log(req)
          if (that.verify_code === req) {
            that.enter(that.enter_exam)
          } else {
            alert('验证码错误！')
          }
        },
        error: function () {
          console.log('查询失败！')
        }
      })
    },
    enter (exam) {
      this.$router.push({path: '/student/exam/' + this.$route.params.studentId + '/' + exam.id + '/question/1/true'})
    },
    check (exam) {
      this.$router.push({path: '/student/exam/' + this.$route.params.studentId + '/list/' + exam.id + '/info'})
    }
  }
}
</script>
<style scoped>
  .info{color:#548bb7;}
</style>
