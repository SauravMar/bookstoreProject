package business.order;

import api.ApiException;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.customer.CustomerForm;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultOrderService implements OrderService {

	private BookDao bookDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		// NOTE: THIS METHOD PROVIDED NEXT PROJECT
		return null;
	}

	@Override
	public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);

		// NOTE: MORE CODE PROVIDED NEXT PROJECT

		return -1;
	}


	private void validateCustomer(CustomerForm customerForm) {

		if (nameIsInvalid(customerForm.getName())) {
			throw new ApiException.InvalidParameter("Please provide a valid name");
		}

		if (nameIsInvalid(customerForm.getAddress())) {
			throw new ApiException.InvalidParameter("Please provide a valid address");
		}

		if (phoneIsInvalid(customerForm.getPhone())) {
			throw new ApiException.InvalidParameter("Please provide a valid phone number");
		}

		if (emailIsInvalid(customerForm.getEmail())) {
			throw new ApiException.InvalidParameter("Please provide a valid email address");
		}

		if (ccNumberIsInvalid(customerForm.getCcNumber())) {
			throw new ApiException.InvalidParameter("Please provide a valid credit card number");
		}

		if (expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
			throw new ApiException.InvalidParameter("Please provide a valid expiry date");

		}
	}

	private boolean nameIsInvalid(String name) {
		return name == null || name.length() > 45 || name.length() < 4;
	}

	private boolean phoneIsInvalid(String phone) {
		try {
			String pattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";
			Pattern regexPattern = Pattern.compile(pattern);
			Matcher matcher = regexPattern.matcher(phone);

			return !matcher.matches() || phone.replaceAll("[() -]", "").length() != 10;
		} catch (Exception err) {
			return true;
		}
	}

	private boolean emailIsInvalid(String email) {
		try {
			String pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
			Pattern regexPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
			Matcher matcher = regexPattern.matcher(email);

			return !matcher.matches() || email.contains(" ") || email.length() -
					email.replace("@", "").length() != 1 || email.endsWith(".");
		} catch (Exception err) {
			return true;
		}

	}

	private boolean ccNumberIsInvalid(String ccNumber) {
		try {
			ccNumber = ccNumber.replaceAll("[ -]", "");
			int numberOfDigits = ccNumber.length();

			String pattern = "^[0-9]{14,16}$";
			Pattern regexPattern = Pattern.compile(pattern);
			Matcher matcher = regexPattern.matcher(ccNumber);

			return !matcher.matches();
		} catch (Exception err) {
			return true;
		}
	}

	private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {
		if (Integer.parseInt(ccExpiryMonth) > 12 || Integer.parseInt(ccExpiryMonth) < 1) {
			return true;
		}

		YearMonth currentYearMonth = YearMonth.now();
		YearMonth ccExpiry = YearMonth.of(Integer.parseInt(ccExpiryYear), Integer.parseInt(ccExpiryMonth));

		return ccExpiry.compareTo(currentYearMonth) < 0;

	}

	private void validateCart(ShoppingCart cart) {

		if (cart.getItems().size() <= 0) {
			throw new ApiException.InvalidParameter("Cart is empty.");
		}

		cart.getItems().forEach(item -> {
			if (item.getQuantity() < 0 || item.getQuantity() > 99) {
				throw new ApiException.InvalidParameter("Invalid quantity");
			}

			Book databaseBook = bookDao.findByBookId(item.getBookId());
			if (databaseBook == null) {
				throw new ApiException.InvalidParameter("Invalid book");
			} else if (item.getBookForm().getPrice() != databaseBook.getPrice()) {
				throw new ApiException.InvalidParameter("Invalid price of book");
			} else if (item.getBookForm().getCategoryId() != databaseBook.getCategoryId()) {
				throw new ApiException.InvalidParameter("Invalid category of book");
			}
		});
	}

}
