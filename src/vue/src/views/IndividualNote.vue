<template>
  <div class="individualnote">
    <br>
      <h2 class="noteTitle">
        {{ singleNote.title }}
      </h2>
      <div class="container">
          <!--p class="description" v-if="singleNote.description">
            Description: {{ singleNote.description }}
          </p>-->
        <div id="note-attributes">
          <div class="row">
            <div class="col">
              Class: {{ singleNote.course }}
            </div>
        </div>
        <div class="row">
          <div class="col">
          Professor:  {{ singleNote.professor }}
          </div>
        </div>
        <div class="row">
          <div class="col">
          Semester: {{ singleNote.semester }}
          </div>
        </div>
        <div class="row">
            <div class="col">
             Description: {{ singleNote.description }}
            </div>
          </div>
        <div class="row">
          <div class="col">
            Author: <a :href="'/profile/'+singleNote.owner.id"> {{ singleNote.owner.username }}</a>
          </div>
        </div>
      </div>
          <!--div class="row">-->
            <!-- Thanks to https://stackoverflow.com/questions/38941074/how-can-i-ensure-a-bootstrap-column-expands-to-fit-the-width-of-its-content-when -->
            <!--p class="col-sm-auto course">
              Course<br>{{ singleNote.course }}
            </p>
            <p class="col-sm-auto professorName">
              Professor<br>{{ singleNote.professor }}
            </p>
            <p class="col-sm-auto semester">
              Semester<br>{{ singleNote.semester }}
            </p>
            <p class="col-sm-auto semester">
              Author<br><a :href="'/profile/'+singleNote.owner.id">{{ singleNote.owner.username }}</a>
            </p>
          </div>-->
          <div class="card">
            <div id="content">
              <div class="card-body" v-if="singleNote.content">
                {{ singleNote.content }}
              </div>
              <div class="embed-responsive embed-responsive-4by3" v-if="!singleNote.content">
                <iframe class="embed-responsive-item" :src="'/api/file/'+this.$route.params.id"></iframe>
              </div>
            </div>
          </div>
          <b-button :href="'/editPage/'+singleNote.id" class="float-right" v-if="isOwner" style="background: #0d3103;">Edit</b-button>

          <!--div class="row-fluid justify-content-center">
            <div class="box bg-light justify-content-center" v-if="singleNote.content">
              <div class="noteContent">
                {{ singleNote.content }}
              </div>
            </div>
            <div class="embed-responsive embed-responsive-4by3" v-if="!singleNote.content">
              <iframe class="embed-responsive-item" :src="'localhost:8080/api/file/'+this.$route.params.id"></iframe>
            </div>
            <b-button :href="'/editPage/'+singleNote.id" class="float-right" variant="primary" squared v-if="isOwner">Edit</b-button>
          </div>-->
      </div>
  </div>
</template>

<script>

import NoteService from '../services/note.service'

export default {
  name: 'IndividualNote',
  data () {
    return {
      singleNote: this.getNote(this.$route.params.id)
    }
  },
  methods: {
    getNote (id) {
      NoteService.get(id)
        .then(response => {
          this.singleNote = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    }

  },
  computed: {
    isOwner () {
      if (this.$store.state.auth.user !== null && this.$store.state.auth.user.username === this.singleNote.owner.username) {
        return true
      } else {
        return false
      }
    }
  }
}
</script>
<style>
.description{
  text-align: left;
  font-size: 20px;
  color:  #0d3103;
}
.individualnote{
  background: #cacbcc;
  height: 100%;
  min-height: 100vh;
  width: 100%;
}
.editButton{
  text-align: center;
  font-size: 20px;
  color:  #0d3103;
}
.box{
  background-color:  #0d3103;
}
.noteTitle{
  font-size: 30px;
  text-align: center;
  color:  #0d3103;
  font-weight: 700;
}
.course{
  text-align: center;
  font-size: 20px;
  color:  #0d3103;
}
.professorName{
  text-align: center;
  font-size: 20px;
  color:  #0d3103;
}
.description{
  text-align: left;
  color:  #0d3103;
  font-size: 20px;
}
.semester{
  text-align: center;
  font-size: 20px;
  color:  #0d3103;
}
.file{
  text-align: left;
  font-size: 20px;
  color:  #0d3103;
}
.noteContent{
  text-align: left;
  font-size: 18px;
  color:  #0d3103;
}
.pdf-display p{
  font-size: 30px;
  color:  #0d3103;
  font-weight: bold;
  margin-bottom: 20px;
}
#note-attributes
{
  text-align: left;
  font-size: 20px;
  color:  #0d3103;
  padding: 10px;
}
#content
{
  min-width: 200px;
  min-height: 200px;
}
</style>
