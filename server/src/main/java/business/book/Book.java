package business.book;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

	/*
	 * TODO: Create private fields corresponding to the fields in the
	 * book table of your database. Generate a constructor that
	 * uses those fields. Generate getter methods for those fields,
	 * and generate a toString method that uses those fields.
	 */
	private long bookId;
	private String title;
	private String author;
	private int price;
	private boolean isPublic;
	private boolean isFeatured;
	private long categoryId;
	private String description;
	private int rating;


	public Book(long bookId, String title, String author, int price, boolean isPublic, long categoryId, boolean isFeatured, String description, int rating) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
		this.isPublic = isPublic;
		this.isFeatured = isFeatured;
		this.categoryId = categoryId;
		this.description = description;
		this.rating = rating;
	}

	public long getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getPrice() {
		return price;
	}

	public boolean getIsPublic() {
		return isPublic;
	}

	public boolean getIsFeatured() {
		return isFeatured;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public String getDescription() {
		return description;
	}

	public int getRating() {
		return rating;
	}

	@Override
	public String toString() {
		return "Book{" +
				"bookId=" + bookId +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", price=" + price +
				", isPublic=" + isPublic +
				", isFeatured=" + isFeatured +
				", categoryId=" + categoryId +
				", description='" + description + '\'' +
				", rating=" + rating +
				'}';
	}
}
