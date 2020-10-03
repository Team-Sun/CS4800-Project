<template>
  <div id="app">
    <div class="flex-header-container">
      <div id="logo">
        <router-link to="/"><img src="./assets/note-logo2.png" alt="Bronco Notes"></router-link>
      </div>
      <div id="nav">
        <router-link to="/">Home</router-link>
        <router-link to="/search">Search</router-link>
        <router-link to="/about">About</router-link>
        <!-- <router-link to="/login">Sign in</router-link> -->
      </div>
      <!-- Log in button -->
      <div>
        <b-dropdown id="login-form" right text="Log in" ref="dropdown" class="m-2">
          <b-dropdown-form>
            <b-form-group label="Email" label-for="dropdown-form-email" @submit.stop.prevent>
              <b-form-input
                id="email"
                size="sm"
                placeholder="email@example.com"
              ></b-form-input>
            </b-form-group>

            <b-form-group label="Password" label-for="dropdown-form-password">
              <b-form-input
                id="password"
                type="password"
                size="sm"
                placeholder="Password"
              ></b-form-input>
            </b-form-group>

            <b-form-checkbox class="mb-3">Remember me</b-form-checkbox>
            <b-button variant="primary" size="sm" @click="onClick">Sign In</b-button>
          </b-dropdown-form>
          <b-dropdown-divider></b-dropdown-divider>
          <b-dropdown-item-button>New around here? Sign up</b-dropdown-item-button>
          <b-dropdown-item-button>Forgot Password?</b-dropdown-item-button>
        </b-dropdown>
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
    margin-left: 80px;
    font-weight: 800;
    color: white;
    &.router-link-exact-active {
      color: #6495ED;
    }
  }
}
</style>
