<script setup lang="ts">
import CategoryBookListItem from "@/components/CategoryBookListItem.vue";
import { useBookStore } from "@/stores/BookStore";

const bookStore = useBookStore();
</script>

<style scoped>
ul {
  display: flex;
  flex-wrap: wrap;
  padding: 1em;
  gap: 1em;
}
.category-book-list {
  overflow-y: scroll;
  min-height: var(--default-viewing-area-height);
  max-height: var(--default-viewing-area-height);
}

#book-boxes {
  display: flex;
  flex-wrap: wrap;
  padding: 1em 2em;
  gap: 2em 2em;
  justify-content: flex-start;
}
</style>

<template>
  <section class="category-book-list">
    <ul id="book-boxes">
      <template v-for="book in bookStore.bookList" :key="book.bookId">
        <category-book-list-item :book="book"></category-book-list-item>
      </template>
    </ul>
  </section>
</template>
