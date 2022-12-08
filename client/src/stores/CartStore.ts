import { defineStore } from "pinia";
import { ShoppingCart } from "@/models/ShoppingCart";
import { BookItem, CustomerForm, OrderDetails } from "@/types";
import { apiUrl } from "@/services/ApiService";
import { useOrderDetailsStore } from "@/stores/OrderDetailsStore";

const CART_STORAGE_KEY = "ShoppingCart";

export const useCartStore = defineStore("CartStore", {
  state: () => {
    const initCart = new ShoppingCart();
    const cartString = localStorage.getItem(CART_STORAGE_KEY);

    if (cartString != null) {
      const cartFromStorage = JSON.parse(cartString) as ShoppingCart;
      Object.assign(initCart, cartFromStorage);
    }
    return {
      cart: initCart,
    };
  },

  getters: {
    count(): number {
      return this.cart.numberOfItems;
    },
  },

  actions: {
    clearCart() {
      this.cart.clear();
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.cart));
    },
    addToCart(book: BookItem) {
      this.cart.addBook(book);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.cart));
    },
    updateBookQuantity(book: BookItem, quantity: number) {
      this.cart.update(book, quantity);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.cart));
    },
    async placeOrder(customerForm: CustomerForm) {
      const order = { cart: this.cart, customerForm: customerForm };
      const url = apiUrl + "orders";

      const orderDetails = await fetch(url, {
        mode: "cors",
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
          "Content-Type": "application/json",
        },
        redirect: "follow",
        referrer: "client",
        method: "POST", // or 'PUT'
        body: JSON.stringify(order),
      })
        .then((response) => response.json())
        .catch((err) => err);

      if (orderDetails != null && !("error" in orderDetails)) {
        const orderDetailsStore = useOrderDetailsStore();
        orderDetailsStore.clearOrderDetails();
        orderDetailsStore.setOrderDetails(orderDetails);
      } else {
        if ("error" in orderDetails) {
          throw orderDetails["error"];
        }
      }
    },
  },
});
