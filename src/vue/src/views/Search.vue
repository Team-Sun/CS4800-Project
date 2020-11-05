<template>
  <div>
    <div class="header">
      <form action="">
        <h1>
          Find Your Note Here
            <div class="searchNote">
              <input type="text" class="search-field note" placeholder="Search by title" v-model="title">

            <button @click="searchTitle" class="search-btn" type="button">Search</button>
          </div>
        </h1>
      </form>
    </div>
    <span class="border1"></span>
    <div class="result">
      <div class="searchTitle">Note List</div>
      <div class="search-result">
        <div class="box">
          <ul class="noteTitle">
            <li class="list-group-item"
              :class="{ active: index == currentIndex }"
              v-for="(note, index) in  notes"
              :key="index"
              @click="setActiveNote(note, index)"
            >
              {{ note.title }}
            </li>
          </ul>

          <button class="m-3 btn btn-sm btn-danger" @click="removeAllNotes">
            Remove All
          </button>

          <div class="noteContent">
            <div v-if="currentNote">
              <h4>Note</h4>
              <div>
                <label><strong>Title:</strong></label> {{ currentNote.title }}
              </div>
              <div>
                <label><strong>Description:</strong></label> {{ currentNote.description }}
              </div>
              <div>
                <label><strong>Status:</strong></label> {{ currentNote.published ? "Published" : "Pending" }}
              </div>

              <a class="badge badge-warning"
                :href="'/notes/' + currentNote.id"
              >
                Edit
              </a>
            </div>
            <div v-else>
              <br />
              <p>Please click on a Note...</p>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>
<script>

import NoteDataService from '../services/NoteDataService'

export default {
  name: 'notes-list',
  data () {
    return {
      notes: [],
      currentNote: null,
      currentIndex: -1,
      title: ''
    }
  },
  methods: {
    retrieveNotes () {
      NoteDataService.getAll()
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

    removeAllNotes () {
      NoteDataService.deleteAll()
        .then(response => {
          console.log(response.data)
          this.refreshList()
        })
        .catch(e => {
          console.log(e)
        })
    },

    searchTitle () {
      NoteDataService.findByTitle(this.title)
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
}
.header{
    height: 280px;
    background-image: linear-gradient(180deg, gold, lemonchiffon);
    background-size: cover;
    background-position: center;
    display: flex;
    justify-content: center;
    align-items: top;
    color: lemonchiffon;
}
h1{
    color: darkgreen;
    font-size: 50px;
    font-family: serif;
    letter-spacing: 2px;
}
.search-field{
    height: 40px;
    padding: 10px;
    border: none;
    border-radius: 25px;
    outline: none;
    font-size: 25px;
    margin-right: 10px;
    margin-top: 50px;
}
.note{
    width: 500px;
}
.class{
    width: 200px;
    padding-right: 10px;
}
.professor{
    width: 200px;
}
.search-btn{
    height: 60px;
    width: 150px;
    background: darkgreen;
    border: none;
    color: white;
    font-size: 20px;
    border-radius: 25px;
}
.search-btn:hover{
    background: grey;
    cursor: pointer;
}
.result{
    background-color: lemonchiffon;
    padding: 30px;
    height: auto;
}
.box{
    background-color:white;
    max-width: 1000px;
    align-content: center;
    margin: auto;
    height: auto;
}
.border1{
    display: block;
    width: 1200px;
    height: 3px;
    margin: auto;
    background: green;
}
.searchTitle{
    color: darkgreen;
    font-size: 50px;
    font-family: serif;
}
.noteTitle{
    font-size: 20px;
    font-weight: 500;
    padding: 10px;
    text-align: left;
}
.noteCourse{
    font-size: 20px;
    font-weight: 500;
    padding: 10px;
    text-align: right;
}
.noteContent{
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
