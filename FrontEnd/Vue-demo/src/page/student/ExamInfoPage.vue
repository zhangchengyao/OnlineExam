<template>
  <div class="wrapper">
    <layout navBarType="student">

      <div class="card">
        <h1>查看考试</h1>

        <exam-info></exam-info>
      </div>

    </layout>
  </div>
</template>

<script>
  import Layout from '../../components/Layout/Layout'
  import ExamInfo from '../../components/ExamInfo/ExamInfo'
  //  import { store } from '../main'
  //  import { mapActions } from 'vuex'

  export default {
    name: 'examInfoPage',
    components: {
      Layout,
      ExamInfo
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
