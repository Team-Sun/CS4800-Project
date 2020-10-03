<template lang="html">
  <div class="class-menu">
    <!-- <h1>{{ msg }}</h1> -->
    <b-button
      id="dropdown"
      :class="visible ? null : 'collapsed'"
      :aria-expanded="visible ? 'true' : 'false'"
      aria-controls="collapse-4"
      @click="visible = !visible"
    >
      Class
    </b-button>
    <b-collapse id="collapse-4" v-model="visible" class="mt-2">
      <b-card>CS 4800</b-card>
      <b-card>CS 3110</b-card>
      <b-card>CS 3310</b-card>
    </b-collapse>
  </div>
</template>

<script>
export default {
  name: 'Class',
  props: {
    msg: String
  },
  data () {
    return {
      visible: true
    }
  }
}
</script>

<style lang="css" scoped>
.class-menu {
  display: flex;
  flex-direction: column;
  text-align: center;
}
</style>
