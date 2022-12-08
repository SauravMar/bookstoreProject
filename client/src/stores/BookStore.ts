import { defineStore } from "pinia";
import { BookItem, CategoryItem } from "@/types";
import { apiUrl } from "@/services/ApiService";
import { useCategoryStore } from "@/stores/CategoryStore";
import { useRoute } from "vue-router";

export const useBookStore = defineStore("BookStore", {
  state: () => ({
    bookList: [] as BookItem[],
  }),
  actions: {
    async fetchBooks(categoryName: string) {
      const categoryStore = useCategoryStore();
      await categoryStore.fetchCategories();
      let selectedCategoryName = categoryName;

      const selectedCategory = categoryStore.categoryList?.find(
        (category) => category.name === categoryName
      );
      console.log(selectedCategory, categoryStore.categoryList);
      if (selectedCategory == null) {
        throw Error("asdsad");
      } else {
        selectedCategoryName = selectedCategory.name;
        const url =
          apiUrl + "categories/name/" + selectedCategoryName + "/books/";
        this.bookList = await fetch(url).then((response) => response.json());
      }
    },
  },
  // getters
});
