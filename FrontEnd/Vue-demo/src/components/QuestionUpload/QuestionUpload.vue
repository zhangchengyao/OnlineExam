<template>
  <div>


    <div class="row">
      <span>试题上传格式</span>
      <div>
        <el-button size="small" type="primary" @click="export2Excel">下载上传格式</el-button>
      </div>
    </div>

    <!-- <div class="row">
      <span>试题上传</span>
      <div>
        <el-upload
          class="upload-demo"
          action="http://114.115.136.111:8080/exam/add/problem/problem.xlsx"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :on-error="handleError"
          :before-upload="handleBeforeUpload"
          :file-list="fileList">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </div>
    </div> -->

    <div class="row">
      <span>试题上传</span>
      <input type="file" @change="getFile($event)" />
      <el-button size="small" type="primary" @click="upload">上传</el-button>
    </div>

    <el-button type="primary" @click="confirm()">确认</el-button>
    <el-button @click="cancel()">返回</el-button>
  </div>
</template>

<script>
  import $ from 'jquery'
  export default {
    name: 'QuestionUpload',
    data () {
      return {
        filename: '',
        fileList: [],
        upath: '',
        tableData: []
      }
    },
    methods: {
      handleRemove (file, fileList) {
        console.log(file, fileList)
      },
      handlePreview (file) {
        console.log(file)
      },
      handleExceed (files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
      },
      handleError (err, file, fileList) {
        console.log(file.name)
        console.log(err)
      },
      handleBeforeUpload (file) {
        this.filename = file.name
      },
      upload () {
        alert('上传成功')
        console.log(this.upath)
        $.ajax({
          type: 'POST',
          url: 'http://114.115.136.111:8080/exam/add/problem',
          dataType: 'text',
          data: {
            examid: this.$route.params.examId,
            filename: this.upath
          },
          success: function (req) {
            console.log(req)
          },
          error: function () {
            console.log('失败！')
          }
        })
      },
      getFile (event) {
        this.upath = event.target.files[0].name
      },
      confirm () {
        // console.log(file.name)
        // this.$refs.upload.submit()
        this.$router.push({path: '/teacher/question/detail/' + this.$route.params.teacherId + '/' + this.$route.params.examId})
      },
      cancel () {
        this.$router.push({path: '/teacher/question/detail/' + this.$route.params.teacherId + '/' + this.$route.params.examId})
      },
      export2Excel () {
        require.ensure([], () => {
          const { export_json_to_excel } = require('../../vendor/Export2Excel')
          const tHeader = ['题目', '选项A', '选项B', '选项C', '选项D', '答案']
          const filterVal = ['content', 'A', 'B', 'C', 'D', 'answer']
          const list = this.tableData
          const data = this.formatJson(filterVal, list)
          export_json_to_excel(tHeader, data, 'problem')
        })
      },
      formatJson (filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]))
      }
    }
  }
</script>

<style scoped src="./QuestionUpload.css"></style>

