<template>
  <div class="layout">

    <btw-logo :class="['logo']"></btw-logo>
    <btw-header :class="['header']" :hasSignOut="hasSignOut"></btw-header>
    <btw-nav-bar v-if="hasNavBar" :type="navBarType" :class="['nav-bar']"></btw-nav-bar>

    <main :class="[hasNavBar ? '' : 'full']">
      <slot></slot>
    </main>

  </div>
</template>

<script>
  import Logo from './Logo'
  import Header from './Header'
  import NavBar from './NavBar'

  export default {
    components: {
      btwLogo: Logo,
      btwHeader: Header,
      btwNavBar: NavBar
    },
    props: {
      hasNavBar: {
        type: Boolean,
        default: true
      },
      hasSignOut: {
        type: Boolean,
        default: true
      },
      navBarType: {
        type: String,
        default: ''
      }
    },
    data () {
      return {}
    }
  }
</script>

<style scoped src="./Layout.css"></style>
