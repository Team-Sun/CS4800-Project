<template>
     <div id="upload-component">
        <form class="form" @submit.prevent="updateNote" role="form">
            <div class="container input-container">
                <div class="row row-0">
                    <div class="col-7 note-col">
                        <div class="row">
                            <textarea
                            class="form-control"
                            rows="12" cols="50"
                            placeholder="Write Notes Here..."
                            v-model="note.content"
                            name="new-note"
                            :disabled="noteType != 0">
                            </textarea>
                        </div>
                        <div class="row">
                            <!--div class="file-field">
                                <div class="btn btn-primary btn-sm float-left">
                                    <span>Choose file</span>
                                    <input type="file">
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text" placeholder="Upload your file">
                            </div>
                        </div>-->
                            <input
                            class="form-control-file"
                            type="file"
                            name="file-note"
                            ref = "file"
                            id = "file"
                            v-on:change="noteFileChange()"
                            :disabled="noteType != 1"/>
                        </div>
                        <div class="row">
                            <select
                            class="form-group"
                            v-model="isUploadingNewNote">
                                <option value="0"> Create New Note </option>
                                <option value="1"> Upload Existing Note </option>
                        </select>
                        </div>
                    </div>
                    <!-- -->
                    <div class="col-5 text-left attribute-col">
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col">
                                    <label for="titleInput"> Title </label>
                                </div>
                                <div class="col">
                                    <input
                                    class="form-control"
                                    type="text"
                                    id="titleInput"
                                    placeholder="Note Title"
                                    v-model="note.title"
                                    v-validate="'required'">
                                    <!--mall v-if="errors.has('note.title')">
                                       Note title is required!
                                    </small>-->
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col">
                                    <label for="classInput"> Class </label>
                                </div>
                                <div class="col">
                                    <input
                                    class="form-control"
                                    type="text"
                                    id="classInput"
                                    placeholder="Class"
                                    v-model="note.course"
                                    v-validate="'required'">
                                    <!--small v-if="errors.has('note.course')">
                                       Note title is required!
                                    </small>-->
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col">
                                    <label for="profInput"> Professor </label>
                                </div>
                                <div class="col">
                                    <input
                                    class="form-control"
                                    type="text"
                                    id="profInput"
                                    placeholder="Professor"
                                    v-model="note.professor">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col">
                                    <label for="semesterInput"> Semester </label>
                                </div>
                                <div class="col">
                                    <input
                                    class="form-control"
                                    type="text"
                                    id="semesterInput"
                                    placeholder="Semester"
                                    v-model="note.semester">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col">
                                    <label> Description </label>
                                </div>
                                <div class="col">
                                    <input
                                    class="form-control"
                                    type="text"
                                    placeholder="Note Description"
                                    v-model="note.description">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row row-1">
                    <div class="col button-col">
                        <!-- This button is disabled if a valid note type hasn't been chosen,
                            the title or class inputs are empty,
                            or if noteType == 0 and the textarea is empty-->

                        <b-button type="submit" style="background: #0d3103;"
                        :disabled="(noteType == 3 || (noteType == 0 && note.content == '')) || note.title == '' || note.course == ''">
                             <!--   :disabled="errors.any() || noteType == 3 || note.title == '' || note.course == ''">-->
                        Update Note
                        </b-button>
                        <button class="btn btn-danger" @click="deleteNote"> Delete </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
import NoteService from '../services/note.service'

export default {
  name: 'EditPage',
  computed: {
    /* Note type is set when an option is selected.
    0 - A new note will be created in the textarea
    1 - An existing note file will be uploaded
    3 - The initial value, it disables both the textarea and the file input until a valid option is chosen
    */
    noteType () {
      return this.isUploadingNewNote
    }
  },
  data: function () {
    return {
      isUploadingNewNote: 3,
      note: this.getNote(this.$route.params.id2),
      message: ''
    }
  },
  methods: {
    getNote (id) {
      NoteService.get(id)
        .then(response => {
          this.note = response.data
          if (response.data.content) {
            this.isUploadingNewNote = 0
          } else {
            this.isUploadingNewNote = 1
          }
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },

    updateNote () {
      NoteService.update(this.note.id, this.note)
        .then(response => {
          console.log(response.data)
          this.message = 'The note was updated successfully!'
          this.$router.push({ name: 'IndividualNote', params: { id: this.note.id } })
        })
        .catch(e => {
          console.log(e)
        })
    },

    deleteNote () {
      NoteService.delete(this.note.id)
        .then(response => {
          console.log(response.data)
          this.$router.push({ name: 'Search' })
        })
        .catch(e => {
          console.log(e)
        })
    }
  }
}
</script>

<style>
.edit-form {
  color: rgba(42, 43, 42, 0.933);
    background-color: #7b7c7e;
    padding: 50px;
    height: 100%;
    min-height: 100vh;
    width: 100%;
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
