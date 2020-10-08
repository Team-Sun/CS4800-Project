<template>
    <div id ="app">
      <div id ="navBar">
        <Header/>
      </div>
      <div id="wrapper">
        <ClassNotePanel/>
        <Upload/>
      </div>
    </div>
  </template>

<script>
import Header from './components/Header.vue'
import ClassNotePanel from './components/ClassAndNote.vue'
import Upload from './components/Upload.vue'
export default {
  name: 'App',
  data()
  {

  },
  components: {
  Header,
  ClassNotePanel,
  Upload


  }
}
</script>

<style>
#app {
  background:#1111;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  
}

#wrapper{
  display:grid;
  grid-template-columns: 30% 70%;
  grid-auto-rows: minmax(500px, auto);
}
</style>
