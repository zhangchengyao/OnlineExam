<template>
  <div class="wrapper">
    <layout navBarType="teacher">

      <div class="card">
        <h1>试题管理</h1>

        <question-list></question-list>

      </div>

    </layout>
  </div>
</template>

<script>
  import Layout from '../../components/Layout/Layout'
  import QuestionList from '../../components/QuestionList/QuestionList'
//  import { store } from '../main'
//  import { mapActions } from 'vuex'

  export default {
    name: 'questionListPage',
    components: {
      Layout,
      QuestionList
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

<style scoped>
  .add-button {
    float: right;
    margin-bottom: 20px;
  }
</style>

