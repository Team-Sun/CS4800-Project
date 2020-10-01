<template>
  <div id="app">
    <div class="flex-header-container">
      <div id="logo">
        <router-link to="/"><img src="./assets/note-logo2.png" alt="Bronco Notes"></router-link>
      </div>
      <div id="nav">
        <router-link to="/">Home</router-link> |
        <router-link to="/about">About</router-link> |
        <router-link to="/donations">Donations</router-link>
        <router-link to="/login">Sign in</router-link>
      </div>
    </div>
    <router-view/>
  </div>
</template>

<style lang="scss">
#app {
  font-family: "Comic Sans MS", cursive, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.flex-header-container {
  display: flex;
  background-color: rgba(0, 0, 0, 0.8);
  flex-direction: row;
  align-items: center;
  justify-content: space-between;

}
#logo img {
  display: block;
  height: auto;
  width: auto;
  max-height: 60px;
}
#nav {
  padding: 25px;
  a {
    margin-left: 120px;
    font-weight: 800;
    color: white;
    &.router-link-exact-active {
      color: #6495ED;
    }
  }
}
</style>
