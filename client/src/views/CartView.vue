<script setup lang="ts">
import CartTable from "@/components/CartTable.vue";
import { useCartStore } from "@/stores/CartStore";
import HeaderDropdown from "@/components/HeaderDropdown.vue";

const cartStore = useCartStore();
const clearCart = function () {
  cartStore.cart.clear();
};
</script>
<style scoped>
.home-page {
  display: flex;
  width: 100%;
  min-height: var(--default-viewing-area-height);
  /* padding: 2% 0%; */
  flex-direction: column;
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

    <div class="cart-table">
      <cart-table></cart-table>
    </div>
  </div>
</template>
