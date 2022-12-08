package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DefaultOrderService implements OrderService {

	private BookDao bookDao;
	private OrderDao orderDao;
	private LineItemDao lineItemDao;
	private CustomerDao customerDao;
	private Random confirmationNumber;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setLineItemDao(LineItemDao lineItemDao) {
		this.lineItemDao = lineItemDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void setConfirmationNumber() {
		this.confirmationNumber = new Random();
	}

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		Order order = orderDao.findByOrderId(orderId);
		Customer customer = customerDao.findByCustomerId(order.getCustomerId());
		List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);

		List<Book> books = lineItems
				.stream()
				.map(lineItem -> bookDao.findByBookId(lineItem.getBookId()))
				.collect(Collectors.toList());

		return new OrderDetails(order, customer, lineItems, books);
	}

	@Override
	public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);

		try (Connection connection = JdbcUtils.getConnection()) {
			Date date = getDate(
					customerForm.getCcExpiryMonth(),
					customerForm.getCcExpiryYear());
			return performPlaceOrderTransaction(
					customerForm.getName(),
					customerForm.getAddress(),
					customerForm.getPhone(),
					customerForm.getEmail(),
					customerForm.getCcNumber(),
					date, cart, connection);
		} catch (SQLException e) {
			throw new BookstoreDbException("Error during close connection for customer order", e);
		}
	}

	private Date getDate(String monthString, String yearString) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.MONTH, Integer.parseInt(monthString) - 1);
		calendar.set(Calendar.YEAR, Integer.parseInt(yearString));
		Date date = calendar.getTime();
		return date; // TODO Implement this correctly
	}

	private long performPlaceOrderTransaction(
			String name, String address, String phone,
			String email, String ccNumber, Date date,
			ShoppingCart cart, Connection connection) {
		try {
			connection.setAutoCommit(false);
			long customerId = customerDao.create(
					connection, name, address, phone, email,
					ccNumber, date);
			long customerOrderId = orderDao.create(
					connection,
					cart.getComputedSubtotal() + cart.getSurcharge(),
					generateConfirmationNumber(), customerId);
			for (ShoppingCartItem item : cart.getItems()) {
				lineItemDao.create(connection, item.getBookId(), customerOrderId, item.getQuantity());
			}
			connection.commit();
			return customerOrderId;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BookstoreDbException("Failed to roll back transaction", e1);
			}
			return 0;
		}
	}

	private int generateConfirmationNumber() {
		return this.confirmationNumber.nextInt(999999999);
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
		if (ccExpiryMonth == null || ccExpiryMonth.isEmpty() || ccExpiryYear == null || ccExpiryYear.isEmpty()) {
			return true;
		} else if (Integer.parseInt(ccExpiryMonth) > 12 || Integer.parseInt(ccExpiryMonth) < 1) {
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
