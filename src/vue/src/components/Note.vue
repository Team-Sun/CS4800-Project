<template>
  <div v-if="currentNote" class="edit-form">
    <form>
      <div class="row row-0">
        <div class="col-7 note-col">
          <div class="col-7 text-left attribute-col">
              <div class="form-group">
                  <div class="form-row">
                      <div class="col">
                          <label for="title"> Title </label>
                      </div>
                      <div class="col">
                        <input type="text" class="form-control" id="title"
                          v-model="currentNote.title"
                        />
                      </div>
                  </div>
              </div>
          </div>
          <div class="row">
            <!-- <label for="description">Content</label> -->
            <textarea
              rows="12"
              columns="30"
              type="text"
              class="form-control"
              id="description"
              v-model="currentNote.description"
            />
          </div>
        </div>
      </div>

      <!-- <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ currentNote.published ? "Published" : "Pending" }}
      </div> -->
    </form>
      <div class="buttons">
      <button type="submit" class="but"
        v-if="currentNote.published"
        @click="updatePublished(false)"
      >
        UnPublish
      </button>
      <button v-else type="submit" class="but"
        @click="updatePublished(true)"
      >
        Publish
      </button>

      <button type="submit" class="but"
        @click="deleteNote"
      >
        Delete
      </button>

      <button type="submit" class="but"
        @click="updateNote"
      >
        Update
      </button>
      <p>{{ message }}</p>
    </div>
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
          this.$router.push({ name: 'Search' })
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
  background: rgba(42, 43, 42, 0.933);
  color: white;
  padding: 12px;
  padding-left: 200px;
  height: 1000px;
}
.col
{
    padding-top: 15px;
}
.buttons
{
  margin-left: -600px;
}
.but
{
  margin-left: 50px;
}

</style>
