<template>
  <div class="flex-header-container">
      <div id="nav">
        <router-link to="/">Home</router-link>
        <router-link to="/search">Search</router-link>
        <router-link to="/about">About</router-link>
        <router-link to="/uploadPage">Upload</router-link>
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
  </template>

<script>

export default {
  name: 'Header',
  setup () {
    function displayQuery () {

    }

    return {
      displayQuery
    }
  }
}
</script>

<style>
#flex-header-container {
  display: flex;
  background-color: #0d3103;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}

#nav{
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  padding: 10px 20px;
}

#nav.menu-items{
  padding: 10px 20px;
  text-align: center;
  display: flex;
}

</style>
