package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import dao.CustomerDAO;
import dao.CustomerDaoImpl;
import dao.OrderDAO;
import dao.OrderDaoImpl;
import dao.ProductDAO;
import dao.ProductDaoImpl;
import model.Customer;
import model.Order;
import model.Product;
import utils.UserInputUtil;
import java.util.Comparator;

public class OrderProcessingSystem {

	static OrderDAO orderDAO = new OrderDaoImpl();
	static CustomerDAO customerDAO = new CustomerDaoImpl();
	static ProductDAO productDAO = new ProductDaoImpl();

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		List<Customer> customers = new ArrayList<>();
		List<Order> orders = new ArrayList<>();
		List<Product> products = new ArrayList<>();

		String choice = "";
		do {
			getMenu();
			System.out.println("Enter your choice:");
			choice = scanner.nextLine();

			switch (choice) {
			case "1":
				Customer customer = new Customer();

				System.out.println("Enter customer name:");
				String customerName = UserInputUtil.validateString();
				customer.setCustomerName(customerName);

				System.out.println("Enter customer address:");
				String address = UserInputUtil.validateString();
				customer.setAddress(address);

				System.out.println("Enter customer phone:");
				int phone = UserInputUtil.validatePhone();
				customer.setPhone(phone);

				boolean check = customerDAO.addNewCustomer(customer);
				if (check) {
					System.out.println("Add new customer successfully !");
				} else {
					System.out.println("Unexpected Error !");
					System.out.println();
				}

				break;
			case "2":
				Customer customerToAddOrder = new Customer();
				Product productToAdd = new Product();
				Order newOrder = new Order();
				customers = customerDAO.getAllCustomers();
				products = productDAO.getAllProduct();
				System.out.println("---List of customers ---");
				customers.stream().sorted(Comparator.comparing(Customer::getCustomerId)).forEach(System.out::println);
				System.out.println();
				System.out.println("Please enter customer ID to add order:");
				int cusIdToAddOrder = UserInputUtil.validateNumber();
				customerToAddOrder = customerDAO.getCustomerByCustomerId(cusIdToAddOrder);
				if (customerToAddOrder == null) {
					System.out.println("No Customer Found !");
					System.out.println();
					break;
				} else {
					System.out.println("---List of products ---");
					products.stream().sorted(Comparator.comparing(Product::getProductId)).forEach(System.out::println);
					System.out.println("Please choose a product ID to add :");
					int productId = UserInputUtil.validateNumber();
					productToAdd = productDAO.getProductByProductId(productId);
					if (productToAdd == null) {
						System.out.println("No Product Found !");
						System.out.println();
						break;
					} else {
						System.out.println("Enter product amount:");
						int amount = UserInputUtil.validateNumber();
						newOrder.setAmount(amount);
						System.out.println("Enter order date: (dd/MM/yyy)");
						String orderDate = UserInputUtil.validateDate();
						newOrder.setOrderDate(orderDate);

						newOrder.setCustomerId(cusIdToAddOrder);
						newOrder.setCustomerName(customerToAddOrder.getCustomerName());
						newOrder.setProductId(productToAdd.getProductId());

						boolean checkCreateOrder = orderDAO.addNewOrder(newOrder);
						if (checkCreateOrder) {
							System.out.println("Add new order successfully !");
							System.out.println();
						} else {
							System.out.println("Unexpected Error !");
							System.out.println();
						}
					}
				}
				break;
			case "3":
				Customer customerEdit = new Customer();
				System.out.println("Enter customer ID to edit:");
				int cusIdToEdit = UserInputUtil.validateNumber();
				customerEdit = customerDAO.getCustomerByCustomerId(cusIdToEdit);
				if (customerEdit == null) {
					System.out.println("No Customer Found !");
					System.out.println();
					break;
				} else {
					customerEdit.setCustomerId(cusIdToEdit);
				}
				System.out.println("Enter new customer name:");
				String customerNameNew = UserInputUtil.validateString();
				customerEdit.setCustomerName(customerNameNew);

				System.out.println("Enter new customer address:");
				String addressNew = UserInputUtil.validateString();
				customerEdit.setAddress(addressNew);

				System.out.println("Enter new customer phone:");
				int phoneNew = UserInputUtil.validatePhone();
				customerEdit.setPhone(phoneNew);

				boolean checkEditCustomer = customerDAO.updateCustomer(customerEdit);
				if (checkEditCustomer) {
					boolean checkUpdateOrder = orderDAO.updateCustomerName(cusIdToEdit, customerNameNew);
					if (checkUpdateOrder) {
						System.out.println("Edit Customer successfully !");
						System.out.println();
					} else {
						System.out.println("Unexpected Error !");
						System.out.println();
					}
				} else {
					System.out.println("Unexpected Error !");
					System.out.println();
				}

				break;
			case "4":
				System.out.println("Enter customer ID to search for orders:");
				int cusId = UserInputUtil.validateNumber();
				orders = orderDAO.searchOrderByCustomerId(cusId);
				if (orders.isEmpty()) {
					System.out.println("No Orders found for this customer !");
					System.out.println();
				} else {
					orders.stream().sorted(Comparator.comparing(Order::getOrderId)).forEach(System.out::println);
					System.out.println();
				}
				break;
				
			case "5":
				System.exit(0);
				break;
			}
		} while (true);

	}

	public static void getMenu() {
		System.out.println("----- ORDER PROCESSING SYSTEM -----");
		System.out.println("1. Create a customer");
		System.out.println("2. Create one order for a specific customer");
		System.out.println("3. Edit a customer");
		System.out.println("4. Search order by customer Id");
		System.out.println("5. Exit");
	}

}
