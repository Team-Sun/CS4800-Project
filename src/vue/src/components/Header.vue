<template>
  <div class="flex-header-container">
      <div id="nav">
        <router-link to="/">
          <img src="../assets/note-logo2.png" height="60" alt="Bronco Notes">
        </router-link>
        <router-link to="/">
          <font-awesome-icon icon="home" />Home
        </router-link>
        <router-link to="/about">
          <font-awesome-icon icon="address-card" />About
        </router-link>

        <div v-if="!currentUser">
          <router-link to="/register">
            <font-awesome-icon icon="user-plus" />Sign Up
          </router-link>
          <router-link to="/login">
            <font-awesome-icon icon="sign-in-alt" />Login
          </router-link>
        </div>

        <div v-if="currentUser">
          <router-link to="/search">
            <font-awesome-icon icon="search-plus" />Search
          </router-link>
          <router-link to="/uploadPage">
            <font-awesome-icon icon="upload" />Upload
          </router-link>
          <router-link to="/profile">
            <font-awesome-icon icon="user" />
            {{ currentUser.username }}
          </router-link>
          <!-- <router-link to="/notes">
            Notes
          </router-link> -->
          <a href @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" />LogOut
          </a>
        </div>

        <!-- Temporary -->
        <!-- <router-link v-if="showAdminBoard" to="/admin" class="nav-link">Admin</router-link>
        <router-link v-if="showModeratorBoard" to="/mod" class="nav-link">Moderator</router-link>
        <router-link v-if="currentUser" to="/user" class="nav-link">User</router-link> -->

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
  },
  computed: {
    currentUser () {
      return this.$store.state.auth.user
    },
    showAdminBoard () {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN')
      }
      return false
    },
    showModeratorBoard () {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_MODERATOR')
      }
      return false
    }
  },
  methods: {
    logOut () {
      this.$store.dispatch('auth/logout')
      this.$router.push('/login')
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
  justify-content: space-around;
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
