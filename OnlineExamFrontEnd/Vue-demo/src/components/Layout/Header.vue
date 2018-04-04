<template>
  <div class="header">

    <div class="left">
      在线考试管理系统
    </div>

    <div class="right">
      <button v-if="hasSignOut" class="sign-out" @click="handleClickSignOut">注销</button>
    </div>

  </div>
</template>

<script>
  import { Button, Message } from 'element-ui'
  import { router } from '../../main'

  export default {
    components: {
      elButton: Button
    },
    props: {
      hasSignOut: {
        type: Boolean,
        default: true
      }
    },
    methods: {
      handleClickSignOut () {
        Message.success('再见！')
        router.push({ path: '/student/login' })
      }
    }
  }
</script>

<style scoped src="./Header.css"></style>
