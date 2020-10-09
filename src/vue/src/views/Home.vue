<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <h1>Welcome to Bronco Notes!</h1>
    <UploadComponent/>
  </div>
</template>

<script>
// @ is an alias to /src
import UploadComponent from '@/components/UploadComponent.vue'

export default {
  name: 'Home',
  components: {
    UploadComponent
  }
}
</script>
