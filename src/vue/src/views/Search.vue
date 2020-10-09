<template>
  <div class="search">
    <form>
          <input type="searchBar">
          <button v-on:click="displayQuery">Search</button>
        </form>
  </div>
</template>

<script>
// @ is an alias to /src
// import Class from '@/components/Class.vue'

export default {
  name: 'Search'

}
</script>
