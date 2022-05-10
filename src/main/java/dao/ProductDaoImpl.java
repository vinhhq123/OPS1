package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Product;
import utils.DBUtils;

public class ProductDaoImpl implements ProductDAO {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private CallableStatement caStatement = null;
	private ResultSet results = null;

	@Override
	public List<Product> getAllProduct() throws SQLException {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<>();
		Product product = null;
		String sql = "select * from Product";
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				product = new Product();

				product.setProductId(results.getInt("productId"));
				product.setProductPrice(results.getFloat("productPrice"));
				product.setProductType(results.getString("productType"));
				products.add(product);
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
		return products;
	}

	@Override
	public boolean updateProductQuantity(int amount, int productId) throws SQLException {
		// TODO Auto-generated method stub
		boolean check = false;
		int results[] = null;
		String sql = "update Product set amount = ?  where productId = ?";
		try {
			connection = DBUtils.getInstance().getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);

			try {
				preparedStatement.setInt(1, amount);
				preparedStatement.setInt(2, productId);

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
	public Product getProductByProductId(int productId) throws SQLException {
		// TODO Auto-generated method stub
		Product product = null;
		String sql = "select * from Product where productId = ?";
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			results = preparedStatement.executeQuery();
			if (results.next()) {
				product = new Product();

				product.setProductId(results.getInt("productId"));
				product.setProductPrice(results.getFloat("productPrice"));
				product.setProductType(results.getString("productType"));
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
		return product;
	}

}
