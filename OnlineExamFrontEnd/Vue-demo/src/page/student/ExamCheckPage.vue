<template>
  <div class="wrapper">
    <layout navBarType="student">

      <div class="card">
        <h1>考试汇总</h1>
        <exam-check></exam-check>
      </div>

    </layout>
  </div>
</template>

<script>
  import Layout from '../../components/Layout/Layout'
  import ExamCheck from '../../components/ExamCheck/ExamCheck'
  //  import { store } from '../main'
  //  import { mapActions } from 'vuex'

  export default {
    name: 'examCheckPage',
    components: {
      Layout,
      ExamCheck
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

