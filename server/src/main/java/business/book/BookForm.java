package business.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class BookForm {

    private long bookId;
    private int price;
    private long categoryId;

    // This is important - it is used during JSON deserialization.
    public BookForm() {
    }

    public BookForm(long bookId, int price, int points, Long categoryId) {
        this.bookId = bookId;
        this.price = price;
        this.categoryId = categoryId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "business.book.BookForm[book_id=" + bookId + "]";
    }

}
