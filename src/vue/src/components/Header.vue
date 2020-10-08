<template>
      <div id ="header">
        <h1> Welcome to Bronco Notes</h1>
        <form>
          <input type="search">
          <button v-on:click="displayQuery">Search</button>
        </form>

        <nav>
          <div class="menu-items"><a href="#">Home</a></div>
          <div class="menu-items"><a href="#">Upload</a></div>
          <div class="menu-items"><a href="#">About</a></div>
          <div class="menu-items"><a href="#">Login</a></div>
          <div class="menu-items"><a href="#">Sign Up</a></div>
         
        </nav>

      </div>
  </template>

<script>

export default {
  name: 'Header',
  setup()
  {
    function displayQuery()
    {

    }

    return{
    displayQuery,
    }
  }
}
</script>

<style>
#header {
  background:#006400;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: white;
  margin-top: 60px;
}
nav{
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
}
nav .menu-items{
  color: white;
  padding: 10px 20px;
  text-align: center;
}

</style>
