<template>
  <div id="app">
    <nav-bar></nav-bar>
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue';

export default {
  name: 'navbar',
  components: {
    NavBar
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

#router-view {
  padding: 20px 0px;
  margin: 0 auto;
}
</style>
