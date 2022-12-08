<script setup lang="ts">
import { reactive, Ref } from "vue";
import useVuelidate from "@vuelidate/core";
import {
  helpers,
  maxLength,
  minLength,
  required,
  email,
} from "@vuelidate/validators";
import { useCartStore } from "@/stores/CartStore";
import HeaderDropdown from "@/components/HeaderDropdown.vue";
import router from "@/router";
import { isCreditCard, isMobilePhone } from "@/utils";
import CheckoutFieldError from "@/components/CheckoutFieldError.vue";

const cartStore = useCartStore();
const cart = cartStore.cart;

const navigateBack = function () {
  let url = localStorage.getItem("path");
  if (url == null || !url.includes("category")) {
    url = "/category/Classics";
  }

  router.push(url);
};

const months: string[] = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

const form = reactive({
  name: "",
  address: "",
  phone: "",
  email: "",
  ccNumber: "",
  ccExpiryMonth: new Date().getMonth() + 1,
  ccExpiryYear: new Date().getFullYear(),
  checkoutStatus: "",
  checkoutMessage: "",
});

const rules = {
  name: {
    required: helpers.withMessage("Please provide a name.", required),
    minLength: helpers.withMessage(
      "Name must have at least 4 letters.",
      minLength(4)
    ),
    maxLength: helpers.withMessage(
      "Name can have at most 45 letters.",
      maxLength(45)
    ),
  },
  address: {
    required: helpers.withMessage("Please provide an address.", required),
    minLength: helpers.withMessage(
      "Address must have at least 4 letters.",
      minLength(4)
    ),
    maxLength: helpers.withMessage(
      "Address can have at most 45 letters.",
      maxLength(45)
    ),
  },
  phone: {
    required: helpers.withMessage(
      "Please provide a valid phone number",
      isMobilePhone
    ),
  },
  email: {
    required: helpers.withMessage(
      "Please provide a valid email address",
      required
    ),
    email: helpers.withMessage("Please provide a valid email address", email),
  },
  ccNumber: {
    required: helpers.withMessage(
      "Please provide a valid credit card number",
      isCreditCard
    ),
  },
  ccExpiryMonth: {
    required: helpers.withMessage("Please provide Expiration Month", required),
  },
  ccExpiryYear: {
    required: helpers.withMessage("Please provide Expiration Year", required),
  },
};

const $v = useVuelidate(rules, form);

async function submitOrder() {
  console.log("Submit order");
  console.log(form);
  const isFormCorrect = await $v.value.$validate();
  if (!isFormCorrect) {
    form.checkoutStatus = "ERROR";
  } else {
    form.checkoutStatus = "PENDING";
    await cartStore
      .placeOrder({
        name: form.name,
        address: form.address,
        phone: form.phone,
        email: form.email,
        ccNumber: form.ccNumber,
        ccExpiryMonth: form.ccExpiryMonth,
        ccExpiryYear: form.ccExpiryYear,
      })
      .then(() => {
        form.checkoutStatus = "OK";
        cartStore.clearCart();
        router.push({ name: "confirmation-view" });
      })
      .catch((reason) => {
        form.checkoutStatus = "SERVER_ERROR";
        form.checkoutMessage = reason;
        console.log("Error placing order", reason);
      });
  }
}

/* NOTE: For example yearFrom(0) == <current_year> */
function yearFrom(index: number) {
  return new Date().getFullYear() + index - 1;
}
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
  /*width: 100%;*/
  margin: 0 5%;
  height: var(--default-viewing-area-height);
  display: flex;
  flex-direction: row;
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

.cart-header {
  background-color: white;
  margin: 0 0 1% 0;
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
  padding: 5% 3%;
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
  font-size: 24px;
  font-weight: 200;
  height: 60px;
  margin-bottom: 5%;
  margin-top: 10%;
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
  padding: 5% 1%;
  margin-bottom: 15%;
}

.cart-table {
  background-color: white;
  border-radius: 2%;
  overflow-y: scroll;
  padding: 1em 0.5em;
  height: 85%;
}

.line-sep {
  display: block;
  height: 2px;
  background-color: gray;
  grid-column: 1 / -1;
  margin: 5% 0;
}

.cart-sum-row {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%;
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

form {
  display: flex;
  flex-direction: column;
}

.id-info {
  display: flex;
  flex-direction: row;
}

.card-info {
  display: flex;
  flex-direction: column;
  padding: 1% 1% 2%;
}

.ind-object {
  padding-bottom: 0;
}

.error {
  color: red;
  text-align: left;
  font-family: auto;
  font-size: 12px;
  display: block;
  height: 2em;
}

.empty-error {
  font-family: auto;
  font-size: 12px;
  display: block;
  height: 2em;
}

.checkoutStatusBox {
  width: 90%;
  /*border-radius: 5px;*/
  padding: 5px;
  /*border: 1px solid var(--primary-color);*/
  margin: 0 0 1em 2em;
  text-align: center;
}

.error-status,
.okay-status,
.pending-status {
  background-color: red;
  border-radius: 10px;
  padding: 4px;
  color: white;
  font-family: auto;
  font-size: 15px;
}

.okay-status {
  background-color: green !important;
}

.pending-status {
  background-color: blue !important;
}

label {
  width: 25%;
}

.input-entry {
  width: 75%;
  color: #495057;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.select-entry {
  color: #495057;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  min-width: 10%;
  padding: 0.5% 1%;
}

.option {
  width: 100%;
  text-align: left;
  font-size: 18px;
  color: white;
  background-color: var(--primary-color);
}

.second-row {
  width: 60%;
  padding: 1% 1% 0% 5%;
}

.first-row {
  width: 40%;
  padding: 1% 1% 0 1%;
}

.button {
  font-size: 24px;
  height: 45px;
  margin: 1% 20%;
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

    <div class="outer-welcome-container">
      <div class="cart-container">
        <div class="cart-header">
          <div class="cart-banner">
            Checkout ({{ cartStore.count }}
            {{ cartStore.count > 1 ? "items" : "item" }})
          </div>
        </div>

        <div class="cart-table" v-if="!cart.empty">
          <form @submit.prevent="submitOrder">
            <div class="id-info">
              <div class="first-row">
                <div class="ind-object">
                  <div>
                    <label for="name">Name</label>
                    <input
                      type="text"
                      size="20"
                      id="name"
                      name="name"
                      v-model.lazy="$v.name.$model"
                      class="input-entry"
                    />
                  </div>
                  <template v-if="$v.name.$error">
                    <span
                      class="error"
                      v-for="error of $v.name.$errors"
                      :key="error.$uid"
                      >{{ error.$message }}</span
                    >
                  </template>
                  <template v-else>
                    <span class="empty-error">&nbsp;</span>
                  </template>
                </div>
                <div class="ind-object">
                  <div>
                    <label for="phone">Phone</label>
                    <input
                      type="text"
                      size="20"
                      id="phone"
                      name="phone"
                      v-model.lazy="$v.phone.$model"
                      class="input-entry"
                    />
                  </div>
                  <template v-if="$v.phone.$error">
                    <span
                      class="error"
                      v-for="error of $v.phone.$errors"
                      :key="error.$uid"
                      >{{ error.$message }}</span
                    >
                  </template>
                  <template v-else>
                    <span class="empty-error">&nbsp;</span>
                  </template>
                </div>
              </div>
              <div class="second-row">
                <div class="ind-object">
                  <div>
                    <label for="address">Address</label>
                    <input
                      type="text"
                      size="20"
                      id="address"
                      name="address"
                      v-model.lazy="$v.address.$model"
                      class="input-entry"
                    />
                  </div>
                  <template v-if="$v.address.$error">
                    <span
                      class="error"
                      v-for="error of $v.address.$errors"
                      :key="error.$uid"
                      >{{ error.$message }}</span
                    >
                  </template>
                  <template v-else>
                    <span class="empty-error">&nbsp;</span>
                  </template>
                </div>

                <div class="ind-object">
                  <div>
                    <label for="email">Email</label>
                    <input
                      type="text"
                      size="20"
                      id="email"
                      name="email"
                      v-model.lazy="$v.email.$model"
                      class="input-entry"
                    />
                  </div>
                  <template v-if="$v.email.$error">
                    <span
                      class="error"
                      v-for="error of $v.email.$errors"
                      :key="error.$uid"
                      >{{ error.$message }}</span
                    >
                  </template>
                  <template v-else>
                    <span class="empty-error">&nbsp;</span>
                  </template>
                </div>
              </div>
            </div>

            <div class="card-info">
              <div class="ind-object">
                <div>
                  <label for="ccNumber">Credit card</label>
                  <input
                    type="text"
                    size="20"
                    id="ccNumber"
                    name="ccNumber"
                    v-model.lazy="$v.ccNumber.$model"
                    class="input-entry"
                  />
                </div>
                <template v-if="$v.ccNumber.$error">
                  <span
                    class="error"
                    v-for="error of $v.ccNumber.$errors"
                    :key="error.$uid"
                    >{{ error.$message }}</span
                  >
                </template>
                <template v-else>
                  <span class="empty-error">&nbsp;</span>
                </template>
              </div>

              <div class="ind-object">
                <label>Expiration Date</label>
                <select v-model="$v.ccExpiryMonth.$model" class="select-entry">
                  <option
                    class="option"
                    v-for="(month, index) in months"
                    :key="index"
                    :value="index + 1"
                  >
                    {{ month }}
                  </option></select
                >&nbsp;
                <select v-model="$v.ccExpiryYear.$model" class="select-entry">
                  <option
                    class="option"
                    v-for="index in 15"
                    :key="index"
                    :value="yearFrom(index)"
                  >
                    {{ yearFrom(index) }}
                  </option>
                </select>
                <template v-if="$v.ccExpiryMonth.$error">
                  <span
                    class="error"
                    v-for="error of $v.ccExpiryMonth.$errors"
                    :key="error.$uid"
                    >{{ error.$message }}</span
                  >
                </template>
                <template v-else-if="$v.ccExpiryYear.$error">
                  <span
                    class="error"
                    v-for="error of $v.ccExpiryYear.$errors"
                    :key="error.$uid"
                    >{{ error.$message }}</span
                  >
                </template>
                <template v-else>
                  <span class="empty-error">&nbsp;</span>
                </template>
              </div>
            </div>

            <section
              v-show="form.checkoutStatus !== ''"
              class="checkoutStatusBox"
            >
              <div v-if="form.checkoutStatus === 'ERROR'" class="error-status">
                Error: Please fix the problems above and try again.
              </div>

              <div
                v-else-if="form.checkoutStatus === 'PENDING'"
                class="pending-status"
              >
                Processing...
              </div>

              <div v-else-if="form.checkoutStatus === 'OK'" class="okay-status">
                Order placed...
              </div>

              <div
                v-else-if="form.checkoutStatus === 'SERVER_ERROR'"
                class="error-status"
              >
                {{ form.checkoutMessage }}
              </div>

              <div v-else class="error-status">
                An unexpected error occurred, please try again.
              </div>
            </section>
          </form>
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
        <li class="line-sep"></li>
        <input
          type="submit"
          name="submit"
          class="checkout-button primary-button"
          :disabled="form.checkoutStatus === 'PENDING'"
          value="Place Your Order"
          @click="submitOrder()"
        />
      </div>
    </div>
  </div>
</template>
