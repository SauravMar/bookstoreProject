<script setup lang="ts">
import { useCategoryStore } from "@/stores/CategoryStore";
import { useRoute } from "vue-router";

const categoryStore = useCategoryStore();
const route = useRoute();
const path = route.params.name;
</script>

<style scoped>
.category-buttons {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  text-align: center;
  background-color: var(--primary-color);
  height: 25px;
}

.button.selected-category-button {
  background-color: var(--primary-color-dark);
  border-radius: 5px;
}

.button.unselected-category-button,
.button.unselected-category-button:visited {
  background-color: var(--primary-color);
}

.button.unselected-category-button:hover,
.button.unselected-category-button:active {
  background-color: var(--primary-color);
}

.cat-nav-items {
  padding: 1% 0% 3% 0% !important;
  width: 100%;
  font-size: 13px;
  text-decoration: none;
  transition: all 0.45s;
  letter-spacing: var(--default-font-spacing);
}

.category-nav {
  max-height: 80%;
  height: 25px !important;
}

.menu {
  display: flex;
  justify-content: space-between;
  align-items: end;
  list-style: none;
  height: 25px !important;
}

.router-link-exact-active {
  color: var(--primary-color-dark);
  background-color: white !important;
  font-size: 16px;
  padding: 0 10px !important;
  border-radius: 10px 0;
}
</style>

<template>
  <nav class="category-nav">
    <ul class="menu category-buttons align-center">
      <li
        v-for="category in categoryStore.categoryList"
        :key="category.categoryId"
      >
        <router-link
          :to="'../category/' + category.name"
          class="button unselected-category-button cat-nav-items"
        >
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </nav>
</template>
