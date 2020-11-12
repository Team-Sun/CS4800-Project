<template>
  <div class="individualnote">
    <br>
    <div class="note-info" v-for="note in singleNotes" :key="note">
      <div class="box">
        <h2 class="noteTitle">
          Title: {{ note.title }}
        </h2>
        <p class="course">
          Course: {{ note.course }}
        </p>
        <p class="professorName">
          Professor: {{ note.professor }}
        </p>
        <p class="semester">
          Semester: {{ note.semester }}
        </p>
        <p class="description">
          Description: {{ note.description }}
        </p>
        <p class="file">
          File: {{ note.file }}
        </p>
        <div class="noteContent">
          Content: {{ note.content }}
        </div>
      </div>
    </div>
    <br>
    <span class="border"></span>
    <div class="pdf-display">
      <p>PDF Displayer</p>
      <iframe height="500" width="50%" src="https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"></iframe>
    </div>
  </div>

</template>

<script>
export default {
  name: 'IndividualNote',
  data () {
    return {
      singleNotes: [
        {
          title: 'Unit Testing',
          course: 'Software Engineer',
          content: 'A unit is the smallest testable part of any software. It usually has one or a few inputs and usually a single output. In procedural programming, a unit may be an individual program, function, procedure, etc. In object-oriented programming, the smallest unit is a method, which may belong to a base / super class, abstract class or derived / child class. (Some treat a module of an application as a unit. This is to be discouraged as there will probably be many individual units within that module.) Unit testing frameworks, drivers, stubs, and mock / fake objects are used to assist in unit testing.',
          professor: 'Adam Smith',
          semester: 'Spring 2019',
          file: 'note.pdf',
          description: 'A Unit Testing Note'
        }
      ]
    }
  }
}
</script>
<style>
.individualnote{
  background-color: #0d3103;
}
.box{
  background-color: #0d3103;
}
.noteTitle{
  font-size: 30px;
  text-align: center;
  color: beige;
  font-weight: 700;
}
.course{
  text-align: left;
  font-size: 20px;
  color: beige;
}
.professorName{
  text-align: left;
  font-size: 20px;
  color: beige;
}
.description{
  text-align: left;
  font-size: 20px;
  color: beige;
}
.semester{
  text-align: left;
  font-size: 20px;
  color: beige;
}
.file{
  text-align: left;
  font-size: 20px;
  color: beige;
}
.noteContent{
  text-align: left;
  font-size: 18px;
  color: beige;
}
.border{
  display: block;
  margin: auto;
  width: 500px;
  height: 2px;
  color: darkslategrey;
  margin-bottom: 40px;
}
.pdf-display p{
  font-size: 30px;
  color: beige;
  font-weight: bold;
  margin-bottom: 20px;
}
</style>
