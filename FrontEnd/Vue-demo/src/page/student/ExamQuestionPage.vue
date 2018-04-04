<template>
  <div class="wrapper">
    <layout navBarType="student">

      <div class="card">
        <h1>进入考试</h1>

        <exam-question></exam-question>
      </div>

    </layout>
  </div>
</template>

<script>
  import Layout from '../../components/Layout/Layout'
  import ExamQuestion from '../../components/ExamQuestion/ExamQuestion'
  //  import { store } from '../main'
  //  import { mapActions } from 'vuex'

  export default {
    name: 'examListPage',
    components: {
      Layout,
      ExamQuestion
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

