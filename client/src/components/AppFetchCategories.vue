<script setup lang="ts">
import { provide } from "vue";
import { CategoryItem } from "@/types";
import { apiUrl } from "@/services/ApiService";
import { useCategoryStore } from "@/stores/CategoryStore";
import AppHeader from "@/components/AppHeader.vue";
import AppFooter from "@/components/AppFooter.vue";

const categoryStore = useCategoryStore();
categoryStore.fetchCategories();
</script>

<style scoped></style>

<template>
  <div id="app-fetch-categories">
    <app-header></app-header>
    <router-view></router-view>
    <app-footer></app-footer>
  </div>
</template>
