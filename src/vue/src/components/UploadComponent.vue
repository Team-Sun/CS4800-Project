<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="title"> Class Title</label>
        <input
          type="text"
          class="form-control"
          id="title"
          required
          v-model="note.title"
          name="title"
        />
      </div>

      <div class="form-group">
        <label for="description">Description</label>
        <textarea rows="20"
          class="form-control"
          id="description"
          required
          v-model="note.description"
          name="description"
        />
        <!-- Copy/Paste Notes Here</textarea> -->
      </div>

      <button @click="saveNote" class="btn btn-success">Create Note</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newNote">Add</button>
    </div>
  </div>
</template>

<script>
import UserService from '../services/NoteDataService'

export default {
  name: 'add-note',
  data () {
    return {
      // Please use the note model I
      note: {
        id: null,
        title: '', // This didn't exist before.
        class: '',
        description: '',
        published: false
      },
      submitted: false
    }
  },
  methods: {
    saveNote () {
      // Please! why are you doing this instead of sending note directly.
      // var data = {
      //   class: this.note.class,
      //   description: this.note.description
      // }

      UserService.create(this.note)
        .then(response => {
          this.notes.id = response.notes.id
          console.log(response.data)
          this.submitted = true
        })
        .catch(e => {
          console.log(e)
        })
    },
    newNote () {
      this.submitted = false
      this.note = {}
    }
  }
}
</script>

<style>
.submit-form {
  max-width: 500px;
  margin: auto;
}

</style>
