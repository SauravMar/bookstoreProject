// Contains all the custom types we want to use for our application
export interface BookItem {
  bookId: number;
  title: string;
  author: string;
  price: number;
  isPublic: boolean;
  categoryId: number;
  description: string;
  rating: 7;
  isFeatured: boolean;
}

export interface CategoryItem {
  categoryId: number;
  name: string;
}
