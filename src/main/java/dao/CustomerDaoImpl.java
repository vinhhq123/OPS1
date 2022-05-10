package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Order;
import utils.DBUtils;

public class CustomerDaoImpl implements CustomerDAO {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private CallableStatement caStatement = null;
	private ResultSet results = null;

	public List<Customer> getAllCustomers() throws SQLException {
		List<Customer> customers = new ArrayList<>();
		Customer customer = null;
		String sql = "select * from Customer";
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				customer = new Customer();

				customer.setCustomerId(results.getInt("customerId"));
				customer.setCustomerName(results.getString("customerName"));
				customer.setAddress(results.getString("address"));
				customer.setPhone(results.getInt("phone"));
				customers.add(customer);
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customers;
	}

	public Customer getCustomerByCustomerId(int customerId) throws SQLException {
		Customer customer = null;
		String sql = "select * from Customer where customerId = ?";
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			results = preparedStatement.executeQuery();
			if (results.next()) {
				customer = new Customer();

				customer.setCustomerId(results.getInt("customerId"));
				customer.setCustomerName(results.getString("customerName"));
				customer.setAddress(results.getString("address"));
				customer.setPhone(results.getInt("phone"));
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customer;
	}

	public boolean updateCustomer(Customer items) throws SQLException {
		// TODO Auto-generated method stub
		boolean check = false;
		int results[] = null;
		String sql = "update Customer set customerName = ? , address = ? , phone = ? where customerId = ?";
		try {
			connection = DBUtils.getInstance().getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);

			try {
				preparedStatement.setString(1, items.getCustomerName());
				preparedStatement.setString(2, items.getAddress());
				preparedStatement.setInt(3, items.getPhone());
				preparedStatement.setInt(4, items.getCustomerId());

				preparedStatement.addBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			results = preparedStatement.executeBatch();
			connection.commit();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (results.length > 0) {
			check = true;
		}
		return check;
	}

	@Override
	public boolean addNewCustomer(Customer customer) throws SQLException {
		boolean check = false;
		int results[] = null;
		String sql = "insert into Customer(customerName,address,phone) values(?,?,?)";
		try {
			connection = DBUtils.getInstance().getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			try {
				preparedStatement.setString(1, customer.getCustomerName());
				preparedStatement.setString(2, customer.getAddress());
				preparedStatement.setInt(3, customer.getPhone());

				preparedStatement.addBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			results = preparedStatement.executeBatch();
			connection.commit();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (results.length > 0) {
			check = true;
		}
		return check;
	}
}
