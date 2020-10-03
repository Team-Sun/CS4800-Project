<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <Homepage msg="This is a Homepage"/>
  </div>
</template>

<script>
// @ is an alias to /src
import Homepage from '@/components/Homepage.vue'

export default {
  name: 'Home',
  components: {
    Homepage
  }
}
</script>
