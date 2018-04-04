<template>
  <div class="wrapper">
    <layout navBarType="student">

      <div class="card">
        <h1>我的考试</h1>

        <exam-list></exam-list>

      </div>

    </layout>
  </div>
</template>

<script>
  import Layout from '../../components/Layout/Layout'
  import ExamList from '../../components/ExamList/ExamList'
  //  import { store } from '../main'
  //  import { mapActions } from 'vuex'

  export default {
    name: 'examListPage',
    components: {
      Layout,
      ExamList
    },
    data () {
      return {}
    },
    computed: {},
    methods: {}
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

