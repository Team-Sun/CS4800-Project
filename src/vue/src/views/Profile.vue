<template>
  <div id="profile-page">
    <div class="container">
      <header class="">
        <img
          id="profile-img"
          src="@/assets/user-icon.png"
          class="profile-img-card"
        />
        <h3>
          Hello, <strong>{{currentUser.username}}</strong>
        </h3>
      </header>
      <p>
        <strong>Email:</strong>
        {{currentUser.email}}
      </p>
      <div class="container text-left">
        <strong>Notes</strong>
           <div class="accordion" role="tablist">
            <b-card no-body class="mb-0 mt-0 p-2"
            v-for="(note, index) in notes"
            :key="index"
            >
              <b-card-header header-tag="header" class="p-1" role="tab">
                <b-button block v-b-toggle="'accordion-'+index" variant="info">{{ note.title }}</b-button>
              </b-card-header>
              <b-collapse :id="'accordion-'+index" visible accordion="my-accordion" role="tabpanel">
                <b-card-body>
                  <b-card-text class="p-0 mt-0 mb-0">Professor: {{ note.professor }}</b-card-text>
                  <b-card-text class="p-0 mt-0 mb-0">Course: {{ note.course }}</b-card-text>
                  <b-card-text class="p-0 mt-0 mb-0">Semester: {{ note.semester }}</b-card-text>
                  <b-card-text class="p-0 mt-0 mb-0">Description: {{ note.description }}</b-card-text>
                  <b-button :href="'/individualNote/'+note.id" class="float-right" variant="primary" squared>→</b-button>

                </b-card-body>
              </b-collapse>
            </b-card>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import NoteService from '../services/note.service'
export default {
  name: 'Profile',
  data () {
    return {
      notes: NoteService.getAll()
    }
  },
  methods: {
    retrieveNotes () {
      NoteService.getAll()
        .then(response => {
          this.notes = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    }
  },
  computed: {
    currentUser () {
      return this.$store.state.auth.user
    }
  },
  mounted () {
    if (!this.currentUser) {
      this.$router.push('/login')
    }
    this.retrieveNotes()
  }
}

</script>
<style >
#profile-page
{
  background: #7b7c7e;
  height: 100%;
  min-height: 100vh;
  width: 100%;
}
</style>
