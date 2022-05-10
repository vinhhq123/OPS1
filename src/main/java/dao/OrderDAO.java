package dao;

import java.sql.SQLException;
import java.util.List;

import model.Order;


public interface OrderDAO {
	
	boolean addNewOrder(Order order) throws SQLException;

	List<Order> searchOrderByCustomerId(int customerId) throws SQLException;
	
	boolean updateCustomerName(int customerId , String newCustomerName) throws SQLException;
}
