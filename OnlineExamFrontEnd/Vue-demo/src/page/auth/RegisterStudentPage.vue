<template>
  <div class="wrapper">
    <layout :hasNavBar=false :hasSignOut=false>
      <register-student-form></register-student-form>
    </layout>
  </div>
</template>

<script>
  import Layout from '../../components/Layout/Layout'
  import RegisterStudentForm from '../../components/Auth/RegisterStudentForm'

  export default {
    name: 'registerPage',
    components: {
      Layout,
      RegisterStudentForm
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
