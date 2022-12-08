<script setup lang="ts">
import { useCartStore } from "@/stores/CartStore";

const cartStore = useCartStore();
import { asDollarsAndCents } from "@/utils";

import { useOrderDetailsStore } from "@/stores/OrderDetailsStore";
import { BookItem, OrderDetails } from "@/types";
import { computed } from "vue";

const orderDetailsStore = useOrderDetailsStore();
const orderDetails: OrderDetails = orderDetailsStore.orderDetails;
console.log(orderDetails);
console.log(cartStore);

// A helper function - optional to use
const bookAt = function (index: number): BookItem {
  return orderDetails.books[index];
};

const orderDate = computed(function () {
  let date = new Date(orderDetails.order.dateCreated);
  return date.toLocaleTimeString();
});

const ccExpDate = computed(function (): Date {
  return new Date(orderDetails.customer.ccExpDate);
});

const ccExpYear = computed(function (): number {
  return ccExpDate.value.getFullYear();
});

const ccExpMonth = computed(function (): string {
  let month = ccExpDate.value.getMonth() + 1;
  let result = "" + month;
  if (month < 10) {
    result = "0" + month;
  }
  return result;
});

let ccNumber = orderDetails.customer.ccNumber;
let maskedCC = "";

if (ccNumber.length > 4) {
  let mask_length = ccNumber.length - 4;
  maskedCC = new Array(mask_length).join("*") + ccNumber.slice(-4);
}
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
  height: 20%;
  display: flex;
  flex-direction: row;
}

.cart-banner {
  vertical-align: middle;
  font-size: 20px;
  font-weight: 600;
  padding: 1% 1%;
  /* width: 65%; */
  display: flex;
  flex-direction: column;
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
  height: 65%;
  overflow-y: auto;
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

.order-details {
  display: flex;
  flex-direction: column;
}

.orderDetailRow {
  font-size: 15px;
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
          <span
            >Order Confirmation #:
            {{ orderDetails.order.confirmationNumber }}</span
          >
          <span>Order Timestamp: {{ orderDate }}</span>
        </div>
      </div>
      <div class="cart-table">
        <table class="table">
          <thead class="thead-dark">
            <tr>
              <th scope="col" class="heading-title">Title</th>
              <th scope="col" class="heading-quantity">Quantity</th>
              <th scope="col" class="heading-price">Price</th>
              <th scope="col" class="heading-subtotal">Amount</th>
            </tr>
          </thead>
          <tbody>
            <template
              v-for="(item, index) in orderDetails.lineItems"
              :key="item.bookId"
            >
              <tr class="cart-row">
                <td>
                  <div>{{ bookAt(index).title }}</div>
                </td>

                <td>
                  <div>{{ item.quantity }}</div>
                </td>

                <td>
                  <div>${{ (bookAt(index).price / 100).toFixed(2) }}</div>
                </td>

                <td>
                  <div>
                    ${{
                      ((item.quantity * bookAt(index).price) / 100).toFixed(2)
                    }}
                  </div>
                </td>
              </tr>
            </template>
          </tbody>
        </table>
      </div>
    </div>

    <div class="summary-container">
      <div class="order-details">
        <span style="font-size: medium; font-weight: 600"
          >Customer Information:</span
        >
        <span class="orderDetailRow">{{
          orderDetails.customer.customerName
        }}</span>
        <span class="orderDetailRow">{{ orderDetails.customer.address }}</span>
        <span class="orderDetailRow">{{ orderDetails.customer.email }}</span>
        <span class="orderDetailRow">{{ orderDetails.customer.phone }}</span>
        <span class="orderDetailRow"
          >{{ maskedCC }} ({{ ccExpMonth }}-{{ ccExpYear }})</span
        >
        <div class="cart-sum-row">
          <li class="line-sep"></li>
        </div>
      </div>
      <li class="line-sep"></li>
      <div class="cart-text">
        <div class="cart-sum-row">
          <span style="font-size: small; font-weight: 600">Subtotal:</span>
          <span style="font-size: medium; font-weight: 800"
            >$
            {{
              (
                (orderDetails.order.amount - cartStore.cart.surcharge) /
                100
              ).toFixed(2)
            }}</span
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
            >$ {{ (orderDetails.order.amount / 100).toFixed(2) }}</span
          >
        </div>
      </div>
      <li class="line-sep"></li>
    </div>
  </div>
</template>
