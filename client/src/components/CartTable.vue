<script setup lang="ts">
import { useCartStore } from "@/stores/CartStore";
import router from "@/router/index";

const cartStore = useCartStore();
import { BookItem } from "@/types";
const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.jpeg`;
};

const updateCart = function (book: BookItem, quantity: number) {
  cartStore.cart.update(book, quantity);
  cartStore.updateBookQuantity(book, quantity);
};

const clearCart = function () {
  cartStore.clearCart();
};

const navigateBack = function () {
  let url = localStorage.getItem("path");
  if (url == null || !url.includes("category")) {
    url = "/category/Classics";
  }

  router.push(url);
};
</script>

<style scoped>
.cart-table-body {
  display: flex;
  flex-direction: row;
  margin: 0 5%;
  height: var(--default-viewing-area-height);
}

.cart-header {
  background-color: white;
  margin: 0% 0 1% 0;
  padding: 1%;
  border-radius: 2%;
  height: 15%;
  display: flex;
  flex-direction: row;
}

.cart-banner {
  vertical-align: middle;
  font-size: x-large;
  font-weight: 600;
  padding: 1% 1%;
  width: 65%;
}

.cart-container {
  width: 70%;
  margin: 0.75em 1em 1.25em 1em;
}

.summary-container {
  background-color: white;
  margin: 0.75em 1em 1.25em 1em;
  padding: 3%;
  border-radius: 2%;
  width: 30%;
}

.continue-button {
  width: 100% !important;
  font-size: 18px;
  font-weight: 200;
  height: 60px;
}

.checkout-button {
  width: 100% !important;
  font-size: 18px;
  font-weight: 200;
  height: 60px;
}

.clear-button {
  width: 100% !important;
  font-size: 18px;
  margin: 1%;
  height: 35px;
  background-color: var(--primary-background-color);
  color: var(--primary-color);
  border: solid var(--primary-background-color);
}

.cart-text {
  height: 40%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 5% 1% 2% 1%;
}

.cart-table {
  margin: 0 auto;
  background-color: white;
  border-radius: 2%;
  height: 75%;
  overflow-y: scroll;
}

ul {
  display: contents;
}

ul > li {
  display: contents;
}

.cart-row {
  padding: 1%;
}

.table-heading {
  background-color: #333;
  color: white;
}

.table-heading > * {
  background-color: #333;
  padding: 0.5em;
}

.heading-book {
  grid-column: 1 / 3;
}

.heading-price {
  grid-column: 3 / 5;
  text-align: right;
}

.heading-subtotal {
  text-align: right;
  grid-column: -2 / -1;
}

.cart-book-image {
  margin-left: 10%;
  width: 40%;
  height: auto;
}

.cart-book-image > * {
  margin-left: auto;
  margin-right: 0;
}

.cart-book-title {
  position: relative;
  top: 25px;
}

img {
  display: block;
  width: 100px;
  height: auto;
}

/* TODO The width above is for book images that are normally 200px wide. Change this (if necessary) */
/* TODO to accommodate your own book images. the rule of thumb is that the image here should be about */
/* TODO half the size of the book images as they appear on your category page. */

.cart-book-price {
  text-align: right;
  /*padding-left: 1em;*/
  /*padding-right: 1em;*/
  position: relative;
  top: 25px;
}

.cart-book-quantity {
  background-color: var(--primary-background-color);
  border-radius: 30px;
  display: flex;
  justify-content: space-between;
  padding: 5px 1px 0px 5px;
  border: 1px solid var(--primary-color-dark);
  margin-left: -1%;
  width: 50%;
}

.cart-book-subtotal {
  text-align: right;
  /*padding-left: 1em;*/
  /*padding-right: 1em;*/
  position: relative;
  top: 25px;
}

/* Row separators in the table */

.line-sep {
  display: block;
  height: 2px;
  background-color: gray;
  grid-column: 1 / -1;
  margin: 5% 0;
}

/* Increment/decrement buttons */

/* TODO Consider using icon buttons for your increment and decrement buttons. */
/* TODO Modify the CSS here to suit your own design. */
/* TODO In particular, you may or may not have custom properties */
/* TODO primary-color and primary-color-dark defined in your global CSS file. */
.icon-button {
  border: 1px solid var(--primary-color-dark);
  cursor: pointer;
  border-radius: 50px;
  font-size: 1rem;
  background-color: white;
  max-width: 25px;
  max-height: 25px;
  padding-left: 5px;
}

.inc-button {
  color: var(--primary-color-dark);
  margin-right: 0.25em;
}

.inc-button:hover {
  color: var(--primary-color);
}

.dec-button {
  color: #ccc;
}

.dec-button:hover {
  color: var(--primary-color);
}

select {
  background-color: var(--primary-color);
  color: white;
  border: 2px solid var(--primary-color-dark);
  border-radius: 3px;
}

.cart-sum-row {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%;
}

.quantity-block {
  display: flex;
  flex-direction: column;
}

.emp {
  padding: 10%;
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow-y: hidden;
}

.emp a {
  margin-top: 2%;
  width: 70%;
}

/* .table td th {
   text-align: center;   
} */
</style>

<template>
  <div class="cart-table-body">
    <div class="cart-container">
      <div class="cart-header">
        <div class="cart-banner">
          Shopping Cart ({{ cartStore.count }}
          {{ cartStore.count > 1 ? "items" : "item" }})
        </div>
      </div>
      <div class="cart-table" v-if="cartStore.count > 0">
        <table class="table">
          <thead class="thead-dark">
            <tr>
              <th scope="col" class="heading-book"></th>
              <th scope="col" class="heading-title">Title</th>
              <th scope="col" class="heading-price">Price</th>
              <th scope="col" class="heading-subtotal">Amount</th>
            </tr>
          </thead>
          <tbody>
            <template
              v-for="item in cartStore.cart.items"
              :key="item.book.bookId"
            >
              <tr class="cart-row">
                <th scope="row">
                  <div class="cart-book-image">
                    <img
                      :src="
                        require('@/assets/images/books/' +
                          item.book.description)
                      "
                      :alt="item.book.title"
                      width="100px"
                      height="auto"
                    />
                  </div>
                </th>

                <td>
                  <div class="quantity-block">
                    <span>{{ item.book.title }}</span>
                    <div class="cart-book-quantity">
                      <button
                        class="dec-button icon-button"
                        @click="updateCart(item.book, item.quantity - 1)"
                      >
                        <i class="fa fa-minus"></i>
                      </button>
                      <span class="quantity">{{ item.quantity }}</span>
                      <button
                        class="inc-button icon-button"
                        @click="updateCart(item.book, item.quantity + 1)"
                      >
                        <i class="fa fa-plus"></i>
                      </button>
                    </div>
                  </div>
                </td>

                <td>
                  <div class="cart-book-price">
                    ${{ (item.book.price / 100).toFixed(2) }}
                  </div>
                </td>

                <td>
                  <div class="cart-book-subtotal">
                    ${{ ((item.quantity * item.book.price) / 100).toFixed(2) }}
                  </div>
                </td>
              </tr>
            </template>
          </tbody>
        </table>
      </div>
      <div class="cart-table emp" v-else>
        <div style="font-size: large; font-weight: bolder">
          Your Cart is Empty
        </div>
        <span style="font-size: large; font-weight: bolder"
          >Time to start shopping!</span
        >
        &nbsp;
        <button
          class="primary-button non-cta-btn continue-button"
          @click="navigateBack()"
        >
          Back to Shopping!
        </button>
      </div>
    </div>

    <div class="summary-container" v-if="cartStore.count > 0">
      <button
        class="primary-button continue-button non-cta-btn"
        @click="navigateBack()"
      >
        Back to Shopping!
      </button>
      <li class="line-sep"></li>
      <div class="cart-text">
        <div class="cart-sum-row">
          <span style="font-size: small; font-weight: 600"
            >Subtotal ({{ cartStore.count }}
            {{ cartStore.count > 1 ? "items" : "item" }}):</span
          >
          <span style="font-size: medium; font-weight: 800"
            >$ {{ (cartStore.cart.subtotal / 100).toFixed(2) }}</span
          >
        </div>
        <div class="cart-sum-row">
          <span style="font-size: small; font-weight: 600">Surcharge:</span>
          <span style="font-size: medium; font-weight: 800"
            >$ {{ (cartStore.cart.surcharge / 100).toFixed(2) }}</span
          >
        </div>
        <div class="cart-sum-row">
          <li class="line-sep"></li>
        </div>
        <div class="cart-sum-row">
          <span style="font-size: medium; font-weight: 600">Total:</span>
          <span style="font-size: large; font-weight: 800"
            >$ {{ (cartStore.cart.total / 100).toFixed(2) }}</span
          >
        </div>
      </div>
      <router-link to="/checkout" tag="span">
        <button class="primary-button checkout-button">
          Proceed to Checkout
        </button>
      </router-link>
      <li class="line-sep"></li>
      <button class="primary-button clear-button" @click="clearCart()">
        Empty Cart
      </button>
    </div>
  </div>
</template>
