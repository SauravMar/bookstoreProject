<script setup lang="ts">
import { watch } from "vue";
import CategoryNav from "@/components/CategoryNav.vue";
import CategoryBookList from "@/components/CategoryBookList.vue";
import { useRoute } from "vue-router";
import { useBookStore } from "@/stores/BookStore";
import { useCategoryStore } from "@/stores/CategoryStore";
import router from "@/router/index";

const route = useRoute();
const bookStore = useBookStore();
const categoryStore = useCategoryStore();

watch(
  () => route.params.name,
  (newName) => {
    // categoryStore.setSelectedCategoryName(newName as string);
    bookStore
      .fetchBooks(newName as string)
      .then((res) => {
        console.log("then", res);
      })
      .catch((err) => {
        console.log("catch", err);
        router.push("/not-found");
      });
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
    <category-book-list></category-book-list>
  </div>
</template>
