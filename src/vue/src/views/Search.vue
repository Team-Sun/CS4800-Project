<template>
  <div>
    <div class="header">
      <form action="">
        <h1>
          Find Your Note Here
          <div class="form-box">
            <div class="searchNote">
              <!--the v-model populates the noteModel object for the later post-request -->
              <input type="text" class="search-field note" placeholder="Notes.." v-model="noteModel.name">
              <ul>
                <li v-for="subject in queriedScienceSubjects" :key=subject>
                  {{ subject }}
                </li>
              </ul>
            </div>
            <div class="searchClass">
              <!--the v-model populates the noteModel object for the later post-request -->
              <input type="text" class="search-field class" v-model="noteModel.className" placeholder="Class">
              <ul>
                <li v-for="subject in queriedClass" :key=subject>
                  {{ subject }}
                </li>
              </ul>
            </div>
            <!-- <div class="searchProfessor">
              <input type="text" class="search-field professor" v-model="searchProfessorInput"
              placeholder="Professor">
              <ul>
                <li v-for="subject in queriedProfessor" :key=subject>
                  {{ subject }}
                </li>
              </ul>
            </div> -->
            <button v-on:click="search" class="search-btn" type="button">Search</button>
          </div>
        </h1>
      </form>
    </div>
    <span class="border1"></span>
    <div class="result">
      <div class="searchTitle">Search Results</div>
      <br>
      <div class="search-result" v-for="note in notes" :key="note">
        <div class="box">
          <p class="noteTitle">
            Title: {{ note.title }}  --  ||  --  Course: {{ note.course }}
          </p>
          <div class="noteContent">
            {{ note.content }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

import Note from '../models/note'
// TODO use a module instead, which can take care of errors and caching the response.
import noteService from '../services/note.service'

export default {
  name: 'Search',
  data: () => {
    return {
      notes: [
        { title: 'Unit Testing', course: 'Software Engineer', content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum" },
        {
          title: 'Back Tracking',
          course: 'Design and Analysis Algorithm',
          content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
        },
        { title: '0-1 Knapsack', course: 'Design and Analysis Algorithm' },
        { title: 'SQL', course: 'Database' }
      ],
      searchNoteInput: '',
      scienceSubjects: ['Physics', 'Chemistry', 'Biology', 'Math', 'Computer Science'],
      searchClassInput: '',
      classSubjects: ['Database', 'Sofware Engineer', 'Data Structure', 'Computer Architecture'],
      // searchProfessorInput: '',
      // professorubjects: ['Josh Damon', 'Donnal Smith', 'Henry Tran', 'Michael Tam'],
      noteModel: new Note()
    }
  },
  methods: {
    // Thanks to https://vuejs.org/v2/guide/events.html#Method-Event-Handlers
    search: function () {
      noteService.search(this.noteModel).then(result => {
        this.notes = result
        console.log(this.notes)
      })
    }
  }
}
</script>
<style scope>
body{
    margin: 0;
    padding: 0;
}
.header{
    height: 380px;
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
    background-color: beige;
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
    background: darkgreen;
}
.searchTitle{
    color: darkgreen;
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
