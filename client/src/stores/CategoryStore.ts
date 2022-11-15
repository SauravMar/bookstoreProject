import { defineStore } from "pinia";
import { CategoryItem } from "@/types";
import { apiUrl } from "@/services/ApiService";

export const useCategoryStore = defineStore("CategoryStore", {
  state: () => ({
    categoryList: [] as CategoryItem[],
  }),
  actions: {
    async fetchCategories() {
      this.categoryList = await fetch(apiUrl + "categories").then((response) =>
        response.json()
      );
    },
  },
  // getters
});
