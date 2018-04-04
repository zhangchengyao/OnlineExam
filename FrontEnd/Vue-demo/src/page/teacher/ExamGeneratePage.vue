<template>
  <div class="wrapper">
    <layout navBarType="teacher">

      <div class="card">
        <h1>生成考试</h1>

        <exam-generate></exam-generate>
      </div>

    </layout>
  </div>
</template>

<script>
  import Layout from '../../components/Layout/Layout'
  import ExamGenerate from '../../components/ExamGenerate/ExamGenerate'
  //  import { store } from '../main'
  //  import { mapActions } from 'vuex'

  export default {
    name: 'examGeneratePage',
    components: {
      Layout,
      ExamGenerate
    },
    data () {
      return {}
    },
    computed: {},
    methods: {
    }
//    beforeRouteEnter (to, from, next) {
//      store.dispatch('').then(next(true))
//    }
  }
</script>

