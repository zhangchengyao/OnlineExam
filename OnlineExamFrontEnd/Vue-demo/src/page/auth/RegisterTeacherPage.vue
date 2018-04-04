<template>
  <div class="wrapper">
    <layout :hasNavBar=false :hasSignOut=false>
      <register-teacher-form></register-teacher-form>
    </layout>
  </div>
</template>

<script>
  import Layout from '../../components/Layout/Layout'
  import RegisterTeacherForm from '../../components/Auth/RegisterTeacherForm'

  export default {
    name: 'registerPage',
    components: {
      Layout,
      RegisterTeacherForm
    },
    data () {
      return {}
    },
    computed: {},
    methods: {
    }
  }
</script>

<style scoped>

</style>
