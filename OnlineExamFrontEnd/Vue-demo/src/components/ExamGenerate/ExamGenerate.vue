<template>
  <div>
    <el-form ref="form" :model="form" :rules="rules" label-position="left" label-width="100px">
      <el-form-item label="考试名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入考试名称" style="width: 400px"></el-input>
      </el-form-item>
      <el-form-item label="考试时间" prop="time">
          <el-date-picker
            v-model="form.start"
            type="datetime"
            placeholder="选择开始时间">
          </el-date-picker>
           <el-date-picker
            v-model="form.end"
            type="datetime"
            placeholder="选择结束时间">
          </el-date-picker>
      </el-form-item>
      <el-form-item label="试题数量" prop="num">
        <el-input-number v-model="form.num" :min="1" :max="1000"></el-input-number>
      </el-form-item>
      <el-form-item label="试题分值" prop="point">
        <el-input-number v-model="form.point" :min="1" :max="10"></el-input-number>
      </el-form-item>
      <el-form-item label="考试人员">
        <el-radio label="1" v-model="form.way">上传名单</el-radio>
        <el-radio label="2" v-model="form.way">选择分组</el-radio>
      </el-form-item>
      <el-form-item label="上传名单" v-if="form.way === '1'">
        <input type="file" @change="getFile($event)" />
        <!-- <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload> -->
      </el-form-item>
      <el-form-item label="选择分组" v-else>
        <el-radio label="1" v-model="form.group">无</el-radio>
        <el-radio label="2" v-model="form.group">年级名</el-radio>
        <el-radio label="3" v-model="form.group">班级名</el-radio>
      </el-form-item>
      <el-form-item label="选择年级" v-if = "form.group !== '1'">
        <el-radio label="1" v-model="form.grade">一年级</el-radio>
        <el-radio label="2" v-model="form.grade">二年级</el-radio>
        <el-radio label="3" v-model="form.grade">三年级</el-radio>
      </el-form-item>
      <el-form-item label="选择班级" v-if = "form.group === '3'">
        <el-radio label="1" v-model="form.class">一班</el-radio>
        <el-radio label="2" v-model="form.class">二班</el-radio>
        <el-radio label="3" v-model="form.class">三班</el-radio>
        <el-radio label="4" v-model="form.class">四班</el-radio>
        <el-radio label="5" v-model="form.class">五班</el-radio>
        <el-radio label="6" v-model="form.class">六班</el-radio>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">确 认</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import $ from 'jquery'

export default {
  name: 'ExamGenerate',
  data () {
    return {
      form: {
        name: '',
        start: '',
        end: '',
        num: 1,
        point: 1,
        group: '1',
        way: '1', // 上传人员方式 1是自己上传 2是选择分组
        grade: '',
        class: '',
        upath: ''
      },
      rules: {
        name: [
          {required: true, message: '考试名称不能为空', trigger: 'blur'}
        ],
        start: [
          {type: 'string', required: true, message: '请选择开始时间', trigger: 'change'}
        ],
        end: [
          {type: 'string', required: true, message: '请选择结束时间', trigger: 'change'}
        ],
        num: [
          {type: 'number', required: true, trigger: 'change'}
        ],
        point: [
          {type: 'number', required: true, trigger: 'change'}
        ]
      }
    }
  },
  methods: {
    handleChange () {},
    submitForm (form) {
      let that = this
      var startTime = that.dateFormat('yyyy-MM-dd hh:mm:ss', this.form.start)
      var endTime = that.dateFormat('yyyy-MM-dd hh:mm:ss', this.form.end)
      if (that.form.way === '1') {
        // 上传名单
        $.ajax({
          type: 'POST',
          url: 'http://114.115.136.111:8080/exam/generate/exam/file',
          dataType: 'json',
          data: {
            teacherid: that.$route.params.teacherId,
            name: that.form.name,
            number: that.form.num,
            value: that.form.point,
            start: startTime,
            end: endTime,
            filename: that.upath
          },
          success: function (req) {
            alert('生成成功！')
            that.$router.push({path: '/teacher/question/' + that.$route.params.teacherId})
          },
          error: function (req) {
            alert('生成失败')
            console.log(req)
          }
        })
      }
      if (that.form.way === '2') {
        $.ajax({
          type: 'POST',
          url: 'http://114.115.136.111:8080/exam/generate/exam',
          dataType: 'json',
          data: {
            teacherid: that.$route.params.teacherId,
            name: that.form.name,
            number: that.form.num,
            value: that.form.point,
            start: startTime,
            end: endTime,
            grade: that.form.grade,
            class: that.form.class
          },
          success: function (req) {
            alert('生成成功！')
            that.$router.push({path: '/teacher/question/' + that.$route.params.teacherId})
          },
          error: function (req) {
            alert('生成失败')
            console.log(req)
          }
        })
      }
    },
    dateFormat (fmt, dated) {
      let date = new Date(dated)
      let o = {
        'Y+': date.getFullYear(),
        'M+': date.getMonth() + 1, // 月份
        'd+': date.getDate(), // 日
        'h+': date.getHours(), // 小时
        'm+': date.getMinutes(), // 分
        's+': date.getSeconds(), // 秒
        'q+': Math.floor((date.getMonth() + 3) / 3), // 季度
        'S': date.getMilliseconds() // 毫秒
      }
      if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
      }
      for (let k in o) {
        if (new RegExp('(' + k + ')').test(fmt)) {
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
        }
      }
      return fmt
    },
    getFile (event) {
      this.upath = event.target.files[0].name
    }
  }
}
</script>
