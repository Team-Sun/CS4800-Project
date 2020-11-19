<template>
  <div id="home">
    <div class="jumbotron">
      <h1>Best College Notes</h1>
      <p>An easy way to organize, find and share notes!</p>
      <div id="button">
        <b-button :href="'/register'" style="background: #0d3103;"> Sign up</b-button>
        <b-button :href="'/login'" style="background: #0d3103;"> Log in </b-button>
      </div>
    </div>
   <div class="container text-left">
        <h2>Recent Notes</h2>
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
</template>

<script>
// @ is an alias to /src
// import UploadComponent from '@/components/UploadComponent.vue'
import NoteService from '../services/note.service'
export default {
  name: 'Home',
  components: {

  },
  data () {
    return {
      notes: NoteService.getPage(1)
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
    this.retrieveNotes()
  }
}
</script>

<style>
#home{
  padding: 10px;
  background: #7b7c7e;
  height: 100%;
  min-height: 100vh;
  width: 100%;
  color: #0d3103;
}
#home h1{
  color: #0d3103;
  text-align: center;
  font-size: 60px;
}
#home h2{
  color: #0d3103;
  font-size: 30px;
}
.jumbotron
{
  background-color: transparent !important;
  color:#0d3103;
  text-align: center;
  font-size: 30px;
}
.carousel-inner{
  height: 100px;
  color: #0d3103;
}
.carousel-caption{
  top: 50%;
}
#accordion-button
{
  background-color:  #0d3103;
}
</style>
