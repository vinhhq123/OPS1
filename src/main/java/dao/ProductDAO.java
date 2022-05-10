package dao;

import java.sql.SQLException;
import java.util.List;

import model.Product;


public interface ProductDAO {

	List<Product> getAllProduct() throws SQLException;
	
	boolean updateProductQuantity(int amount,int productId) throws SQLException;
	
	Product getProductByProductId(int productId) throws SQLException;
}
