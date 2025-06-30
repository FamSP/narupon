package th.ac.npru.se.ntk.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ProductSingleton is a singleton class that manages a single instance of a product.
 * It provides methods for setting product details, showing product info,
 * and interacting with a database to insert or retrieve product information.
 * 
 * Implements {@link Manageable} interface.
 * 
 * Database table assumed:
 * - Table name: product
 * - Columns: p_id (VARCHAR), p_name (VARCHAR), p_price (INT)
 * 
 * @author 
 */
public class ProductSingleton implements Managable {

	private String product_id;
	private String product_name;
	private int product_price;

	/** Static instance for Singleton */
	private static ProductSingleton instance = null;

	/** 
	 * Private constructor to prevent instantiation from outside the class. 
	 */
	private ProductSingleton() {
	}

	/**
	 * Returns the single instance of ProductSingleton.
	 * If no instance exists, it will be created.
	 * 
	 * @return instance of ProductSingleton
	 */
	public static ProductSingleton getInstance() {
		if (instance == null) {
			instance = new ProductSingleton();
		}
		return instance;
	}

	/**
	 * Displays product details to the console.
	 */
	public void showProducut() {
		System.out.println("Product ID: " + this.product_id);
		System.out.println("Product Name: " + this.product_name);
		System.out.println("Product Price: " + this.product_price);
	}

	/**
	 * Sets the product's details.
	 *
	 * @param p_id    Product ID
	 * @param p_name  Product Name
	 * @param p_price Product Price
	 */
	public void setProduct(String p_id, String p_name, int p_price) {
		product_id = p_id;
		product_name = p_name;
		product_price = p_price;
	}

	/**
	 * Returns the product ID.
	 * 
	 * @return product ID
	 */
	public String getProductId() {
		return product_id;
	}

	/**
	 * Returns the product name.
	 * 
	 * @return product name
	 */
	public String getProductName() {
		return product_name;
	}

	/**
	 * Returns the product price.
	 * 
	 * @return product price
	 */
	public int getProductPrice() {
		return product_price;
	}

	/**
	 * Inserts the current product details into the database.
	 * 
	 * @param pid    Product ID (not used in current implementation)
	 * @param pname  Product Name (not used in current implementation)
	 * @param pprice Product Price (not used in current implementation)
	 */
	public void insertProduct(String pid, String pname, int pprice) {
		Connection myConn = null;
		Statement myStmt = null;

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "");

			System.out.println("Database connection successful!\n");

			// 2. Create a statement
			myStmt = myConn.createStatement();

			// 3. Execute SQL query
			String sql = "INSERT INTO Product VALUES ('" + this.product_id + "','" + this.product_name + "'," + this.product_price + ")";
			myStmt.executeUpdate(sql);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * Retrieves product name from the database using a given product ID.
	 * 
	 * @param pid Product ID
	 * @return Product name (from class field due to bug)
	 */
	public String getProductName(String pid) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String productName = "";
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "");
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from product where p_id='" + pid + "'");

			while (myRs.next()) {
				productName = myRs.getString("p_name");
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		// **BUG**: It should return `productName`, not the class's getProductName().
		return productName;
	}

	/**
	 * Deletes a product by ID (not implemented).
	 * 
	 * @param id Product ID
	 * @return always returns false
	 */
	@Override
	public boolean deleteById(int id) {
		// TODO: Implement product deletion logic
		return false;
	}
}
