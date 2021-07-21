<template>
  <div id="main">
      <h1>Books</h1>
      <div id="read-books">
          <book v-bind:book="book"/>
      </div>
  </div>
</template>

<script>
import Book from "@/components/Book.vue";
import bookService from "@/services/BookService";

export default {
    name: "read-list",
    components: {
        Book
    },
    created() {
        bookService.list().then((response) => {
            this.$store.commit('SET_BOOKLIST', response.data);
        }).catch((e) => {
            console.error(e + ": No book data");
        });
    }
}
</script>

<style>
#read-books {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
}
</style>