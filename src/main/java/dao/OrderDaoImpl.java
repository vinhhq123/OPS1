package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import utils.DBUtils;

public class OrderDaoImpl implements OrderDAO {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet results = null;

	@Override
	public boolean addNewOrder(Order order) throws SQLException {
		boolean check = false;
		int results[] = null;
		String sql = "insert into Orders(customerId,customerName,productId,amount,orderDate) values(?,?,?,?,?)";
		try {
			connection = DBUtils.getInstance().getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			try {
				preparedStatement.setInt(1, order.getCustomerId());
				preparedStatement.setString(2, order.getCustomerName());
				preparedStatement.setInt(3, order.getProductId());
				preparedStatement.setFloat(4, order.getAmount());
				preparedStatement.setString(5, order.getOrderDate());

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
	public List<Order> searchOrderByCustomerId(int customerId) throws SQLException {
		List<Order> orders = new ArrayList<>();
		Order order = null;
		String sql = "select * from Orders where customerId = ?";
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				order = new Order();

				order.setOrderId(results.getInt("orderId"));
				order.setCustomerId(results.getInt("customerId"));
				order.setCustomerName(results.getString("customerName"));
				order.setProductId(results.getInt("productId"));
				order.setAmount(results.getFloat("amount"));
				DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
				String orderDate = dateFormat1.format(results.getDate("orderDate"));
				order.setOrderDate(orderDate);
				orders.add(order);
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
		return orders;
	}

	@Override
	public boolean updateCustomerName(int customerId, String newCustomerName) throws SQLException {
		// TODO Auto-generated method stub
		boolean check = false;
		int results[] = null;
		String sql = "update Orders set customerName = ?  where customerId = ?";
		try {
			connection = DBUtils.getInstance().getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);

			try {
				preparedStatement.setString(1, newCustomerName);
				preparedStatement.setInt(2, customerId);

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
