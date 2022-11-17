package business.order;

import api.ApiException;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.customer.CustomerForm;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.Date;

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
		return phone.replaceAll("[() -]", "").length() != 10;
	}

	private boolean emailIsInvalid(String email) {
		return email.contains(" ") || email.length() - email.replace("@", "").length() != 1 || email.endsWith(".");
	}

	private boolean ccNumberIsInvalid(String ccNumber) {
		int numberOfDigits = ccNumber.replaceAll("[ -]", "").length();
		return (14 > numberOfDigits || numberOfDigits > 16);
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
			// TODO: complete the required validations
		});
	}

}
