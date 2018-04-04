<template>
  <div class="wrapper">
    <layout navBarType="teacher">

      <div class="card">
        <h1>试题管理 - 上传试题</h1>

        <question-upload></question-upload>
      </div>

    </layout>
  </div>
</template>

<script>
  import Layout from '../../components/Layout/Layout'
  import QuestionUpload from '../../components/QuestionUpload/QuestionUpload'
  //  import { store } from '../main'
  //  import { mapActions } from 'vuex'

  export default {
    name: 'questionUploadPage',
    components: {
      Layout,
      QuestionUpload
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

