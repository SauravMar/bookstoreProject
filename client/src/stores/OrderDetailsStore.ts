import { defineStore } from "pinia";
import { OrderDetails } from "@/types";

const ORDER_DETAIL_STORAGE_KEY = "orderDetail";

export const useOrderDetailsStore = defineStore("OrderDetailStore", {
  state: () => {
    let orderDetails = {} as OrderDetails;
    const initOrderString: string | null = sessionStorage.getItem(
      ORDER_DETAIL_STORAGE_KEY
    );
    if (initOrderString !== null) {
      const orderFromStorage = JSON.parse(initOrderString) as OrderDetails;
      orderDetails = Object.assign({}, orderFromStorage);
    }
    return {
      orderDetails: orderDetails,
    };
  },
  actions: {
    clearOrderDetails() {
      this.orderDetails = {} as OrderDetails;
    },
    hasOrderDetails(): boolean {
      return sessionStorage.getItem(ORDER_DETAIL_STORAGE_KEY) != null;
    },
    setOrderDetails(orderDetails: OrderDetails) {
      this.orderDetails = orderDetails;
      sessionStorage.setItem(
        ORDER_DETAIL_STORAGE_KEY,
        JSON.stringify(orderDetails)
      );
    },
  },
});
