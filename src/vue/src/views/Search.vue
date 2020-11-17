<template>
  <div>
    <div class="header">
      <h1>
        Find Your Note Here
          <div class="searchNote">
            <input type="text" class="search-field note" placeholder="Search by title" v-model="title">

          <button @click="searchTitle" class="search-btn" type="button">Search</button>
        </div>
      </h1>
    </div>
    <span class="border1"></span>
    <div class="result">
      <div class="searchTitle">Search Results</div>
      <br>
      <div class="search-result">
        <div class="box text-left">
          <b-list-group>
            <b-list-group-item v-for="(note, index) in notes"
            :key="index">
            Title: {{note.title}}
            <br>
            Professor: {{ note.professor }}
            <br>
            Course: {{ note.course }}
            <br>
            Semester: {{ note.semester }}
            <br>
            Description: {{ note.description }}
            <b-button :href="'/individualNote/'+note.id" class="float-right" variant="primary" squared>→</b-button>
            </b-list-group-item>
          </b-list-group>

          <!--div class="accordion" role="tablist">
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
          </div>-->

        </div>
      </div>
    </div>
  </div>
</template>
<script>

import NoteService from '../services/note.service'

export default {
  name: 'notes-list',
  data () {
    return {
      notes: NoteService.getPage(1),
      currentNote: null,
      currentIndex: -1,
      title: ''
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
    },

    refreshList () {
      this.retrieveNotes()
      this.currentNote = null
      this.currentIndex = -1
    },

    setActiveNote (note, index) {
      this.currentNote = note
      this.currentIndex = index
    },

    // removeAllNotes () {
    //   NoteService.deleteAll()
    //     .then(response => {
    //       console.log(response.data)
    //       this.refreshList()
    //     })
    //     .catch(e => {
    //       console.log(e)
    //     })
    // },

    searchTitle () {
      NoteService.findByTitle(this.title)
        .then(response => {
          this.notes = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    }
  },
  mounted () {
    this.retrieveNotes()
  }
}

</script>

<style scope>
body{
    margin: 0;
    padding: 0;
    height: 100vh;
}

.header{
    height: 200px;
    background-color: #0d3103;
    background-size: cover;
    background-position: center;
    display: flex;
    justify-content: center;
    align-items: top;
    color: lemonchiffon;
}
h1{
    margin-top: 10px;
    color: lemonchiffon;
    font-size: 50px;
    font-family: serif;
    letter-spacing: 2px;
    padding-top: 20px;
}
.search-field{
    height: 40px;
    padding: 10px;
    border: none;
    border-radius: 25px;
    outline: none;
    font-size: 25px;
    margin-right: 10px;
    margin-top: 30px;
}
.note{
    width: 500px;
}
.class{
    width: 200px;
    padding-right: 10px;
    margin-bottom: 20px;
}
.professor{
    width: 200px;
}
.search-btn{
    height: 50px;
    width: 150px;
    background: beige;
    border: none;
    color: darkgoldenrod;
    font-size: 25px;
    border-radius: 25px;
}
.search-btn:hover{
    background: grey;
    cursor: pointer;
}
.result{
    background-color: #7b7c7e;
    padding: 30px;
    height: auto;
}
.box{
    background-color:beige;
    max-width: 1000px;
    align-content: center;
    margin: auto;
    height: auto;
}
.border1{
    display: block;
    width: auto;
    height: 3px;
    margin: auto;
    background:  #0d3103;
}
.searchTitle{
    color:  #0d3103;
    font-size: 50px;
    font-family: serif;
}
.search-result .box{
  background-color: beige;
}
.noteTitle{
    font-size: 20px;
    font-weight: 500;
    padding: 10px;
    text-align: left;
}
.list-group-item{
    color:black;
}
.noteCourse{
    font-size: 20px;
    font-weight: 500;
    padding: 10px;
    text-align: right;
}
div.noteContent{
    color: black;
    font-size: 20px;
    font-weight: 300;
    text-align: left;
    padding: 5px;
}
.searchClass{
    font-size: 15px;
}
.searchNote{
   font-size: 15px;
}
</style>
