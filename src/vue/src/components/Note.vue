<template>
  <div v-if="currentNote" class="edit-form">
    <h4>Note</h4>
    <form>
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" id="title"
          v-model="currentNote.title"
        />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <input type="text" class="form-control" id="description"
          v-model="currentNote.description"
        />
      </div>

      <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ currentNote.published ? "Published" : "Pending" }}
      </div>
    </form>

    <button class="badge badge-primary mr-2"
      v-if="currentNote.published"
      @click="updatePublished(false)"
    >
      UnPublish
    </button>
    <button v-else class="badge badge-primary mr-2"
      @click="updatePublished(true)"
    >
      Publish
    </button>

    <button class="badge badge-danger mr-2"
      @click="deleteNote"
    >
      Delete
    </button>

    <button type="submit" class="badge badge-success"
      @click="updateNote"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Note...</p>
  </div>
</template>

<script>
import NoteDataService from '../services/NoteDataService'

export default {
  name: 'note',
  data () {
    return {
      currentNote: null,
      message: ''
    }
  },
  methods: {
    getNote (id) {
      NoteDataService.get(id)
        .then(response => {
          this.currentNote = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },

    updatePublished (status) {
      var data = {
        id: this.currentNote.id,
        title: this.currentNote.title,
        description: this.currentNote.description,
        published: status
      }

      NoteDataService.update(this.currentNote.id, data)
        .then(response => {
          this.currentNote.published = status
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },

    updateNote () {
      NoteDataService.update(this.currentNote.id, this.currentNote)
        .then(response => {
          console.log(response.data)
          this.message = 'The note was updated successfully!'
        })
        .catch(e => {
          console.log(e)
        })
    },

    deleteNote () {
      NoteDataService.delete(this.currentNote.id)
        .then(response => {
          console.log(response.data)
          this.$router.push({ name: 'notes' })
        })
        .catch(e => {
          console.log(e)
        })
    }
  },
  mounted () {
    this.message = ''
    this.getNote(this.$route.params.id)
  }
}
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
