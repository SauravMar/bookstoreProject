<script setup lang="ts">
import { defineProps } from "vue";
import { BookItem } from "@/types";
import { useCartStore } from "@/stores/CartStore";

const props = defineProps<{
  book: BookItem;
}>();
const cartStore = useCartStore();
</script>
<style scoped>
.book-box {
  display: flex;
  flex-direction: column;
  background-color: var(--secondary-background-color);
  padding: 0.7em 0.7em 0 0.7em;
  gap: 0.2em;
  width: 150px;
  border: 1px solid var(--primary-color-dark);
  border-radius: 5px;
}

.book-title {
  font-weight: bold;
  font-size: 12px;
  letter-spacing: var(--default-font-spacing);
}

.book-author {
  font-style: italic;
  font-size: 10px;
  letter-spacing: 1px;
}

.book-cover {
  height: 190px;
}

.book-price {
  font-size: 11px;
  font-weight: bolder;
}

.book-info {
  min-height: 88%;
}

.readnow {
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-95%, -130%);
  background-color: var(--primary-color);
  /* color: var(--default-text-color); */
  font-size: 12px;
  cursor: pointer;
  padding: 2px 7px;
  border: 1px solid var(--secondary-background-color);
}

.book-image a btn:hover {
  background-color: black;
}

.cart {
  border-radius: 5px;
  background-color: var(--primary-color);
  font-size: 12px;
  max-height: 100px;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 75%;
}

.cart:hover {
  color: var(--primary-color);
  background-color: var(--primary-background-color);
  border: 1px solid var(--primary-color-dark);
}

.book-actions {
  display: flex;
}

.cart-text {
  padding: 0 5px;
}
</style>

<template>
  <li class="book-box">
    <div class="book-info">
      <div class="book-image">
        <img
          :src="require('@/assets/images/books/' + book.description)"
          :alt="book.title"
          class="book-cover"
        />
        <button v-if="book.isPublic === true" class="button readnow">
          Read Now
        </button>
        <button
          v-if="book.isPublic === false"
          class="button readnow"
          style="visibility: hidden !important;'"
        >
          Read Now
        </button>
      </div>
      <div class="book-title">{{ book.title }}</div>
      <div class="book-author">by {{ book.author }}</div>
      <div class="book-price">${{ (book.price / 100).toFixed(2) }}</div>
    </div>
    <div class="book-actions">
      <button class="button cart" @click="cartStore.addToCart(book)">
        <i class="fa fa-shopping-cart"></i>
        <span class="cart-text">Add to Cart</span>
      </button>
    </div>
  </li>
</template>
