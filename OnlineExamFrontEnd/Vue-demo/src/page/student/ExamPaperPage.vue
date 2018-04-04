<template>
  <div class="wrapper">
    <layout navBarType="student">

      <div class="card">
        <h1>试卷分析</h1>

        <exam-question></exam-question>
      </div>

    </layout>
  </div>
</template>

<script>
  import Layout from '../../components/Layout/Layout'
  import ExamQuestion from '../../components/ExamPaper/ExamPaper'

  export default {
    name: 'examPaperPage',
    components: {
      Layout,
      ExamQuestion
    },
    data () {
      return {}
    },
    computed: {},
    methods: {}
  }
</script>

