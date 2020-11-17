<template>
  <div class="about-page">
    <div class="team-section">
      <h2>Team Sun - CS4800 Cal Poly Pomona</h2>
      <span class="border0"></span>
      <div class="ps">
        <a href="#p1"><img src="@/assets/mai_profile.jpeg" alt="" width="240" height="280"></a>
        <a href="#p2"><img src="@/assets/p1.jpeg" alt="" width="240" height="280"></a>
        <a href="#p3"><img src="@/assets/andrew.jpg" alt="" width="240" height="280"></a>
        <a href="#p4"><img src="@/assets/JasonHo.jpg" alt="" width="240" height="280"></a>
        <a href="#p5"><img src="@/assets/jonathan.jpg" alt="" width="240" height="280"></a>
      </div>
      <div class="section" id="p1">
          <span class="name">Mai Luu</span>
          <span class="border"></span>
          <p>
            Frontend Developer
          </p>
          <div class="link">
            <a href="https://github.com/ximui23">GitHub</a>
            <br>
          </div>
      </div>
      <div class="section" id="p2">
          <span class="name">Nishara Hysmith</span>
          <span class="border"></span>
          <p>
              Frontend Developer
          </p>
          <div class="link">
            <a href="https://github.com/nhysmith">GitHub</a>
          </div>
      </div>
      <div class="section" id="p3">
          <span class="name">Andrew Day</span>
          <span class="border"></span>
          <p>
              Fullstack Developer
          </p>
          <div class="link">
            <a href="https://github.com/Andr3wD">GitHub</a>
          </div>
      </div>
      <div class="section" id="p4">
          <span class="name">Jason Ho</span>
          <span class="border"></span>
          <p>
              Backend Developer
          </p>
          <div class="link">
            <a href="https://github.com/jho012299">GitHub</a>
          </div>
      </div>
      <div class="section" id="p5">
        <span class="name">Jonathan Chang</span>
        <span class="border"></span>
        <p>
            Fullstack Developer
        </p>
        <div class="link">
            <a href="https://github.com/jonathan1118">GitHub</a>
        </div>
      </div>
    </div>
    <div class="mission">
      <p>Our Goals</p>
      <span class="border2"></span>
      <h1>
          As a students, we understand the potential of sharing notes among classmates and we
          have not seen any web application that focus particularly in note sharing.
          Thus, we decide to create the Note Sharing Application for Cal Poly Pomona students to
          search for notes, upload notes, and share notes among courses in Computer Science.
          <br>
          <br>
          Thanks for visiting our website, and feel free to leave any comments or feedbacks, we appreaciate it.
      </h1>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
*{
  margin: 0;
  padding: 0;
  font-family: sans-serif;
}
/* Thanks to https://stackoverflow.com/questions/30752049/fill-screen-horizontal-and-vertical-with-padding */
.about-page{
  height:100vh;
  width:auto;
  background-color:  #7b7c7e;
}
.team-section{
    height: 570px;
    overflow: hidden;
    text-align: center;
    background: #0d3103;
    padding: 40px;
}
.team-section h2{
    text-transform: uppercase;
    margin-bottom: 20px;
    color: #7b7c7e;
    font-size: 40px;
}
.border0{
    display: block;
    margin: auto;
    width: 500px;
    height: 2px;
    background:  #7b7c7e;
    margin-bottom: 40px;
}
.border{
    display: block;
    margin: auto;
    width: 160px;
    height: 3px;
    background: gold;
    margin-bottom: 20px;
}
.border2{
    display: block;
    margin: auto;
    width: 160px;
    height: 3px;
    background: green;
    margin-bottom: 30px;
}
.ps{
    margin-bottom: 40px;
}
.ps a{
    display: inline-block;
    margin: 0 30px;
    width: 160px;
    height: 160px;
    overflow: hidden;
    border-radius: 50%;
}
.ps a img{
    width: 100%;
    height: 100%;
    background-position: center;
    filter: grayscale(100%);
    transition: 0.4s all;
}
.ps a:hover > img {
    filter: none;
}
.section{
  width: 600px;
  margin: auto;
  font-size: 20px;
  color:  #7b7c7e;
  text-align: justify;
  height: 0;
  overflow: hidden;
}
.section p{
  text-align: center;
  margin-bottom: 20px;
}
.section:target{
    height: auto;
}
.section a{
  text-align: center;
  color:  #7b7c7e;
}
.link{
  text-align: center;
}
.name{
    display: block;
    margin-bottom: 30px;
    text-align: center;
    text-transform: uppercase;
    font-size: 28px;
}
.mission{
    overflow: hidden;
    text-align: center;
    background:  #7b7c7e;
    padding: 40px;
    height: min-content;
}
.mission p{
    text-transform: uppercase;
    font-size: 36px;
    color:  #0d3103;
    margin-bottom: 10px;
}
.mission h1{
    font-size: 22px;
    color:  #0d3103;
    font-family: sans-serif;
}
</style>
