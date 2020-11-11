<template>
  <div id="individualnote">
    <h1>TEST</h1>
    <div>
      <iframe height="500" width="50%" src="/api/note/file/${id}"></iframe>
    </div>
  </div>

</template>

<script>
export default {
  name: 'IndividualNote',
  data () {
    return {
    }
  }
}
</script>
