<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by title"
          v-model="title"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchTitle"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Notes List</h4>
      <ul class="list-group">
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
    </div>
    <div class="col-md-6">
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

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>
