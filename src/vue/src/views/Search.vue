<template>
  <div>
    <div class="header">
      <form action="">
        <h1>
          Find Your Note Here
          <div class="form-box">
            <div class="searchNote">
              <input type="text" class="search-field note"
                  placeholder="Notes.." v-model="searchNoteInput">
              <ul>
                  <li v-for="subject in queriedScienceSubjects" :key=subject>
                    {{ subject }}
                  </li>
              </ul>
            </div>
            <div class="searchClass">
              <input type="text" class="search-field class" v-model="searchClassInput"
              placeholder="Class">
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
            <button class="search-btn"
            type="button">Search</button>
          </div>
        </h1>
      </form>
    </div>
    <span class="border1"></span>
    <div class="result">
      <div class="searchTitle">Search Results</div>
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
export default {
  data: () => {
    return {
      notes: [
        { title: 'Unit Testing', course: 'Software Engineer', content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"},
        { title: 'Back Tracking', course: 'Design and Analysis Algorithm', 
          content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
        },
        { title: '0-1 Knapsack', course: 'Design and Analysis Algorithm'},
        { title: 'SQL', course: 'Database'}
      ],
      searchNoteInput: '',
      scienceSubjects: ['Physics', 'Chemistry', 'Biology', 'Math', 'Computer Science'],
      searchClassInput: '',
      classSubjects: ['Database', 'Sofware Engineer', 'Data Structure', 'Computer Architecture'],
      // searchProfessorInput: '',
      // professorubjects: ['Josh Damon', 'Donnal Smith', 'Henry Tran', 'Michael Tam'],
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
