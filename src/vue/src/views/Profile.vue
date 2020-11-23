<template>
  <div id="profile-page">
    <div class="container">
      <header class="">
        <img
          id="profile-img"
          src="@/assets/user-icon.png"
          class="profile-img-card"
        />
        <h3 v-if="owner">
          Hello, <strong>{{currentUser.username}}</strong>
        </h3>
        <h3 v-if="!owner">
          <strong>{{currentUser.username}}</strong>
        </h3>
      </header>
      <!--p>
        <strong>Email:</strong>
        {{currentUser.email}}
      </p>-->
      <div>
        <b-button v-if="owner" v-b-toggle.collapse-1 style="background: #0d3103;">View Profile Info</b-button>
        <b-collapse id="collapse-1" class="mt-2">
          <b-card>
            <!--p class="card-text">Collapse contents Here</p>-->
              <div class="row">
                <div class="col">
                 <label>Username:</label>
                  {{currentUser.username}}
                </div>
              </div>
              <div class="row">
                <div class="col">
                  <label>Email:</label>
                  {{currentUser.email}}
                </div>
              </div>
            <b-button v-b-toggle.collapse-1-inner size="sm">Edit Profile</b-button>
            <b-collapse id="collapse-1-inner" class="mt-2">
              <!--b-card>Hello!</b-card>-->
              <div id="edit-profile">
                <div class="form-group row">
                  <label for="inputUsername3" class="col-sm-2 col-form-label">Username:</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputUsername3" placeholder="Username" v-model="newUsername">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="inputEmail3" class="col-sm-2 col-form-label">Email:</label>
                  <div class="col-sm-10">
                    <input type="email" class="form-control" id="inputEmail3" placeholder="Email" v-model="newEmail">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
                  <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="Password" v-model="newPassword">
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-sm-12">
                    <button @click="deleteUser" type="submit" class="btn btn-danger float-right">Delete Account</button>
                    <button @click="updateUser" type="submit" class="btn btn-primary float-right">Submit</button>
                  </div>
                </div>
              </div>
            </b-collapse>
          </b-card>
        </b-collapse>
      </div>
      <div class="container text-left">
        <strong>Notes:</strong>
           <div class="accordion" role="tablist">
            <b-card no-body class="mb-0 mt-0 p-2"
            v-for="(note, index) in notes"
            :key="index"
            >
              <b-card-header header-tag="header" class="p-1" role="tab">
                <b-button block v-b-toggle="'accordion-'+index" variant="info" style="background: #0d3103;">{{ note.title }}</b-button>
              </b-card-header>
              <b-collapse :id="'accordion-'+index" visible accordion="my-accordion" role="tabpanel">
                <b-card-body>
                  <b-card-text class="p-0 mt-0 mb-0">Professor: {{ note.professor }}</b-card-text>
                  <b-card-text class="p-0 mt-0 mb-0">Course: {{ note.course }}</b-card-text>
                  <b-card-text class="p-0 mt-0 mb-0">Semester: {{ note.semester }}</b-card-text>
                  <b-card-text class="p-0 mt-0 mb-0">Description: {{ note.description }}</b-card-text>
                  <b-button :href="'/individualNote/'+note.id" class="float-right" style="background: #0d3103;">→</b-button>

                </b-card-body>
              </b-collapse>
            </b-card>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserService from '../services/user.service'
import NoteService from '../services/note.service'

export default {
  name: 'Profile',
  data () {
    return {
      notes: '',
      newPassword: '',
      newUsername: '',
      newEmail: '',
      owner: false,
      currentUser: null
    }
  },
  methods: {
    retrieveNotes (selfid) {
      console.log(this.$route.params.id)
      if (this.$route.params.id !== undefined) {
        NoteService.getByUser(this.$route.params.id)
          .then(response => {
            this.notes = response.data
            console.log(response.data)
          })
          .catch(e => {
            console.log(e)
          })
      } else {
        NoteService.getByUser(this.currentUser.hexId)
          .then(response => {
            this.notes = response.data
            console.log(response.data)
          })
          .catch(e => {
            console.log(e)
          })
      }
    },
    updateUser () {
      UserService.updateUser(this.currentUser.hexId, { username: this.newUsername, email: this.newEmail, password: this.newPassword })
    },
    deleteUser () {
      // TODO handle error?

      UserService.deleteUser(this.currentUser.hexId).then(
        test => {
          this.$store.dispatch('auth/clearAuth').then(
            () => {
              this.$router.push('/')
            })
        })
    }
  },
  mounted () {
    if (!this.$store.state.auth.user) {
      this.$router.push('/login')
    }
    if (this.$route.params.id === undefined) {
      this.owner = true
      this.currentUser = this.$store.state.auth.user
    } else {
      UserService.getPublicContent(this.$route.params.id).then(
        result => {
          this.currentUser = result.data
        })
    }
    this.retrieveNotes()
  }
}

</script>
<style >
#profile-page
{
  background: #cacbcc;
  height: 100%;
  min-height: 100vh;
  width: 100%;
}
#edit-profile
{
}
</style>
