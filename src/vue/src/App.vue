<template>
  <div id="app">
    <Header/>
    <router-view/>
    <PDFViewer/>
  </div>
</template>
<script>
import Header from './components/Header.vue'
import PDFViewer from './components/PDFViewer.vue'

export default {
  name: 'App',
  components: {
    Header,
    PDFViewer
  }
}
</script>

<style lang="scss">
#app {
  font-family: sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.flex-header-container {
  display: flex;
  background-color: #0d3103;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}
// Probably don't need it
#logo img {
  display: block;
  height: auto;
  width: auto;
  max-height: 60px;
}

#nav {
  a {
    margin-left: 80px;
    font-weight: 800;
    color: white;
    &.router-link-exact-active {
      color: #6495ED;
    }
  }
}
</style>
