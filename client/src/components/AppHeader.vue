<script setup lang="ts">
import { useCartStore } from "@/stores/CartStore";
import { useRoute } from "vue-router";
import router from "@/router/index";

const cartStore = useCartStore();
const route = useRoute();

const navigateToCart = function () {
  localStorage.setItem("path", route.path);
  router.push("/cart");
};
</script>

<style scoped>
header {
  /* background: var(--secondary-background-color); */
  display: flex;
  padding: 1%;
  gap: 1em;
  /* border-bottom: 4px solid var(--primary-color); */
}

.text-logo {
  font-family: var(--title-font-family);
  color: var(--primary-color);
  font-size: 26px;
  font-weight: bolder;
  text-decoration: none;
}

@media (max-width: 600px) {
  header {
    flex-direction: column;
  }
}

.title-and-search-bar {
  text-align: center;
  min-width: 50%;
  max-width: 50%;
  font-family: var(--default-font-family);
}

.header-dropdown-and-cart {
  text-align: right;
  display: flex;
  justify-content: end;
  padding: 0% 1% 0% 15%;
  min-width: 24%;
  max-width: 25%;
  font-family: var(--default-font-family);
}

.bookstore-logo {
  display: flex;
  min-width: 23%;
  max-width: 23%;
  align-items: center;
}

.logo-child {
  flex: 1;
}

.logo-child:first-child {
  margin-right: 0;
}

.logo {
  max-width: 35%;
  min-width: 35%;
}

.search {
  width: 100%;
  position: relative;
  display: flex;
  padding: 2% 10% 2% 10%;
  font-family: var(--default-font-family);
}

.search-bar {
  width: 100%;
  height: 35px !important;
  border: 1.7px solid var(--primary-color);
  border-left: none;
  padding: 0% 0% 0% 3%;
  border-radius: 0 10px 10px 0;
  outline: none;
  color: var(--secondary-background-color);
  font-size: 15px;
  font-family: var(--default-font-family);
}

.search-bar:focus {
  color: #00b4cc;
}

.search-button {
  width: 40px;
  height: 35px;
  border: 1px solid var(--primary-color);
  background: var(--primary-color);
  text-align: center;
  color: var(--secondary-background-color);
  border-radius: 10px 0 0 10px;
  cursor: pointer;
  font-size: 20px;
}

.cart-button {
  padding: 0% 20%;
  display: flex;
}

.cart-button i {
  font-size: 21px;
  padding: 6px 0 0 4px;
}

.cart-button,
.login-button {
  border: 1px solid white;
  letter-spacing: var(--default-font-spacing);
  font-size: 14px;
  font-weight: normal;
}

.loggedin-button {
  padding: 0 8%;
}

.links {
  font-size: 10px;
  font-weight: 700;
  font-family: var(--default-font-family);
}

#lblCartCount {
  font-weight: normal;
  background: var(--primary-color-dark);
  color: var(--neutral-color);
  padding: 2px 6px;
  vertical-align: top;
  margin-left: -10px;
  margin-top: 2px;
  font-family: var(--default-font-family);
  border: 1px solid white;
}

.badge {
  padding-left: 9px;
  padding-right: 9px;
  -webkit-border-radius: 9px;
  -moz-border-radius: 9px;
  border-radius: 9px;
}

.label-warning[href],
.badge-warning[href] {
  background-color: #c67605;
}

.cart-route a {
  text-decoration: none !important;
}

.line-sep {
  display: block;
  height: 2px;
  background-color: gray;
  grid-column: 1 / -1;
}
</style>

<template>
  <header>
    <section class="bookstore-logo">
      <div class="logo-child logo">
        <router-link to="/">
          <img
            src="../assets/images/site/logo14.png"
            alt="Another Bookstore Logo"
            width="150"
            height="auto"
          />
        </router-link>
      </div>
      <div class="logo-child">
        <router-link to="/" class="text-logo">
          <h1 class="text-logo">BookMeUp</h1>
        </router-link>
      </div>
    </section>

    <section class="title-and-search-bar align-center">
      <!-- <h1 class="text-logo">Another Bookstore</h1> -->
      <form style="width: 100%">
        <div class="search">
          <button type="submit" class="search-button">
            <i class="fa fa-search"></i>
          </button>
          <input
            type="text"
            class="search-bar"
            placeholder="Search by Title, Author, Keyword or ISBN"
          /><br />
        </div>
      </form>
    </section>

    <section class="header-dropdown-and-cart align-center">
      <button
        class="primary-button login-button"
        v-if="$route.name === 'home-view'"
      >
        Login
      </button>
      <button
        class="primary-button login-button loggedin-button"
        v-if="$route.name !== 'home-view'"
      >
        <span style="font-size: 10px">Hello,&nbsp;</span>
        <span style="font-size: 12px">Anon!</span>
      </button>

      <div class="cart-route">
        <!-- <router-link to="/cart"> -->
        <button class="primary-button cart-button" @click="navigateToCart()">
          <i class="fa fa-shopping-cart"></i>
          <span class="badge badge-warning" id="lblCartCount">{{
            cartStore.count
          }}</span>
        </button>
        <!-- </router-link> -->
      </div>
    </section>

    <li class="line-sep"></li>
  </header>
</template>
