package dao;

import java.sql.SQLException;
import java.util.List;

import model.Customer;


public interface CustomerDAO {

	List<Customer> getAllCustomers() throws SQLException;
	
	Customer getCustomerByCustomerId(int customerId) throws SQLException;
	
	boolean updateCustomer(Customer customer) throws SQLException;
	
	boolean addNewCustomer(Customer customer) throws SQLException;
}
