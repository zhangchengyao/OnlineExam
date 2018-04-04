<template>
  <div class="wrapper">
    <layout :hasNavBar=false :hasSignOut=false>
      <sign-in-form></sign-in-form>
    </layout>
  </div>
</template>

<script>
  import Layout from '../../components/Layout/Layout'
  import SignInForm from '../../components/Auth/SignInForm'

  export default {
    name: 'loginPage',
    components: {
      Layout,
      SignInForm
    },
    data () {
      return {}
    },
    computed: {},
    methods: {}
  }
</script>

<style scoped>

</style>
