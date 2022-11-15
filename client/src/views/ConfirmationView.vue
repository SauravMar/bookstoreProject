<script setup lang="ts">
import HeaderDropdown from "@/components/HeaderDropdown.vue";
</script>
<style scoped>
.home-page {
  display: flex;
  width: 100%;
  min-height: var(--default-viewing-area-height);
  /* padding: 2% 0%; */
  flex-direction: column;
}

.outer-welcome-container {
  width: 100%;
  padding: 2% 0;
  height: var(--default-viewing-area-height);
}

.welcome-container {
  width: 100%;
  display: flex;
  justify-content: space-evenly;
  max-height: 50%;
}

.category-image-items {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 1em;
}

.category-image-items a {
  text-align: center;
  cursor: pointer;
}

.category-image-items div {
  padding: 0.5em 0;
  background: rgba(0, 0, 0, 0.5); /* last # is percent opacity */
  color: white;
  transform: translateY(-2.25em);
  margin-bottom: -2em;
}

.carousel-button {
  font-size: 1000%;
  padding: 8% 0% 0% 0%;
  color: var(--primary-color);
}

.carousel-button i {
  cursor: pointer;
}

.cta-container {
  padding: 1% 0% 0% 26%;
  /* background-color: var(--secondary-background-color); */
}

.categories-nav-bar {
  height: 30px;
  background-color: var(--primary-color);
  padding: 0.2% 1% 0.2% 1%;
}

nav {
  width: 100%;
}

.menu {
  display: flex;
  justify-content: space-between;
  align-items: end;
  list-style: none;
  max-height: 80%;
  height: 25px !important;
}

.menu li {
  width: 10%;
  transition: background-position-x 0.9s linear;
  text-align: center;
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

.cta-btn.a {
  text-decoration: none;
  color: white;
}
</style>

<template>
  <div class="home-page">
    <div class="categories-nav-bar">
      <nav>
        <ul class="menu align-center">
          <header-dropdown></header-dropdown>
          <li><a href="#!" class="home-nav-items">Offers & Deals</a></li>
          <li><a href="#!" class="home-nav-items">Our Bestsellers</a></li>
          <li><a href="#!" class="home-nav-items">New Releases</a></li>
          <li><a href="#!" class="home-nav-items">Today's Deals</a></li>
          <li><a href="#!" class="home-nav-items">More</a></li>
        </ul>
      </nav>
    </div>

    <div class="outer-welcome-container">Welcome to Confirmation Page!</div>
  </div>
</template>
