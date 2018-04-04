<template>

  <el-menu class="nav-bar" @select="handleSelect" :router="true" :default-active="$route.path">


    <el-menu-item v-if="type==='teacher'" index="/teacher/question/1">试题管理</el-menu-item>
    <el-menu-item v-if="type==='teacher'" index="/teacher/exam/generate/1">生成考试</el-menu-item>

    <el-menu-item v-if="type==='student'" index="/student/exam/list/5">我的考试</el-menu-item>

  </el-menu>

</template>

<script>
  import { Menu, Submenu, MenuItem, MenuItemGroup } from 'element-ui'

  export default {
    components: {
      elMenu: Menu,
      elSubmenu: Submenu,
      elMenuItem: MenuItem,
      elMenuItemGroup: MenuItemGroup
    },
    props: {
      type: {
        type: String,
        default: ''
      }
    },
    data () {
      return {
      }
    },
    methods: {
      handleSelect (key, keyPath) {
        console.log(key, keyPath)
      }
    }
  }
</script>

<style scoped src="./NavBar.css"></style>

