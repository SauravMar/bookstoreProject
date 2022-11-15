<script setup lang="ts">
import { ref, watch } from "vue";
import { useRoute } from "vue-router";
import { BookItem, CategoryItem } from "@/types";
import CategoryNav from "@/components/CategoryNav.vue";
import CategoryBookList from "@/components/CategoryBookList.vue";
import { apiUrl } from "@/services/ApiService";
import { useCategoryStore } from "@/stores/CategoryStore";
import { useBookStore } from "@/stores/BookStore";

const route = useRoute();
const bookStore = useBookStore();

let bookList = ref([] as BookItem[]);

watch(
  () => route.params.name,
  (newName) => {
    bookStore.fetchBooks(newName as string);
  },
  { immediate: true }
);
</script>

<style scoped>
.categories-nav-bar {
  height: 30px;
  background-color: var(--primary-color);
  padding: 0.2% 1% 0.2% 1%;
}
</style>

<template>
  <div>
    <div class="categories-nav-bar">
      <category-nav></category-nav>
    </div>
    <category-book-list :bookList="bookList"> </category-book-list>
  </div>
</template>
