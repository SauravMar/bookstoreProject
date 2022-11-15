<script setup lang="ts">
import { useCategoryStore } from "@/stores/CategoryStore";

const categoryStore = useCategoryStore();
</script>

<style scoped>
.header-dropdown {
  position: relative;
  letter-spacing: 2px;
}

.header-dropdown ul {
  background-color: white;
  color: black;
  display: none;
}

.header-dropdown li {
  padding: 4% 5%;
  width: 100%;
  text-align: left;
  font-size: 13px;
  opacity: 0;
}

.header-dropdown a {
  color: white;
  text-decoration: none;
}

.header-dropdown li:hover {
  background: var(--primary-color-dark);
}

.header-dropdown:hover ul {
  z-index: 999999;
  width: 150%;
  display: block;
  position: absolute;
  min-width: 10%;
  cursor: pointer;
  font-size: 14px;
  background-color: var(--primary-color);
}

.header-dropdown button ul li {
  letter-spacing: 2px;
}

.home-nav-items {
  font-size: 13px;
  text-decoration: none;
  transition: all 0.45s;
  background-color: var(--primary-color);
  border: 1px solid var(--primary-color);
  /* margin-top: 0.25em; */
  color: white;
  letter-spacing: var(--default-font-spacing);
}

.home-nav-items:hover {
  background: var(--primary-color);
  color: white;
}

.dropdown-item0 {
  transform-origin: top center;
  animation: translateX 300ms 100ms ease-in-out forwards;
}

.dropdown-item1 {
  transform-origin: top center;
  animation: translateX 300ms 200ms ease-in-out forwards;
}

.dropdown-item2 {
  transform-origin: top center;
  animation: translateX 300ms 300ms ease-in-out forwards;
}

.dropdown-item3 {
  transform-origin: top center;
  animation: translateX 300ms 400ms ease-in-out forwards;
}

.dropdown-item4 {
  transform-origin: top center;
  animation: translateX 300ms 500ms ease-in-out forwards;
}

.dropdown-item5 {
  transform-origin: top center;
  animation: translateX 300ms 600ms ease-in-out forwards;
}

.dropdown-item6 {
  transform-origin: top center;
  animation: translateX 300ms 700ms ease-in-out forwards;
}

.dropdown-item7 {
  transform-origin: top center;
  animation: translateX 300ms 800ms ease-in-out forwards;
}

.dropdown-menu {
  margin: 0 !important;
}

@keyframes translateX {
  0% {
    opacity: 0;
    transform: translateX(60px);
  }

  80% {
    transform: translateX(-5px);
  }

  100% {
    opacity: 1;
    transform: translateX(0px);
  }
}
</style>

<template>
  <div class="header-dropdown">
    <button class="home-nav-items">
      Categories&nbsp;<i class="fa fa-angle-down"></i>
    </button>
    <ul class="dropdown-menu">
      <li
        v-for="(category, index) in categoryStore.categoryList"
        :key="category.categoryId"
        :class="`dropdown-item${index}`"
      >
        <router-link :to="'../category/' + category.name">
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </div>
</template>
