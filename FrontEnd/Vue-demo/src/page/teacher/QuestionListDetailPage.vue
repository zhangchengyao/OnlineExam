<template>
  <div class="wrapper">
    <layout navBarType="teacher">

      <div class="card">
        <h1>试题管理 - 试题列表</h1>

        <el-button class="add-button" type="primary" icon="el-icon-plus" @click="upload">上传试题</el-button>

        <question-list-detail></question-list-detail>

      </div>

    </layout>
  </div>
</template>

<script>
  import Layout from '../../components/Layout/Layout'
  import QuestionListDetail from '../../components/QuestionListDetail/QuestionListDetail'
//  import { store } from '../main'
//  import { mapActions } from 'vuex'

  export default {
    name: 'questionListDetailPage',
    components: {
      Layout,
      QuestionListDetail
    },
    data () {
      return {}
    },
    computed: {},
    methods: {
      upload () {
        this.$router.push({path: '/teacher/question/upload/' + this.$route.params.teacherId + '/' + this.$route.params.examId})
      }
    }
  //    beforeRouteEnter (to, from, next) {
  //      store.dispatch('').then(next(true))
  //    }
  }
</script>

<style scoped>
  .add-button {
    float: right;
    margin-bottom: 20px;
  }
</style>

