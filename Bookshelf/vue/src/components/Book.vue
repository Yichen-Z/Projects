<template>
  <div class="bookCard">
    <h2 class="book-title">{{ book.title }}</h2>
    <h3 class="publication-year">{{ book.year }}</h3>
  </div>
</template>

<script>
export default {
  name: 'book',
  props: {
    book: Object
  }
}
</script>

<style>
.bookCard {
    border: 2px solid gray;
    border-radius: 10px;
    width: 300px;
    height: 200px;
    margin: 20px;
}
</style>