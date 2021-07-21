<template>
  <div class="details">
      <h2 class="title">{{ book.title }} ({{ book.year }})</h2>
      <h2>Author(s)</h2>
      <h3>Tags</h3>
  </div>
</template>

<script>
export default {
    name: "book-details",
    props: {
        book: Object
    }
}
</script>

<style>

</style>