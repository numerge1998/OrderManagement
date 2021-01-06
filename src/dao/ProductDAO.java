package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;
import model.Product;

public class ProductDAO {

	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO product (idProduct,pret,stoc,nume)"
			+ " VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM product where idProduct = ?";
	private final static String findStatementAllString = "SELECT * FROM product";
	private final static String updateStatementString = "UPDATE product set stoc = ? where idProduct = ?";
	private final static String deleteStatementString = "DELETE from product where idProduct = ?";
	
	public static Product findById(int productId) {
		Product toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, productId);
			rs = findStatement.executeQuery();
			rs.next();

			int pret = rs.getInt("pret");
			int stoc = rs.getInt("stoc");
			String nume = rs.getString("nume");
			toReturn = new Product(productId, pret, stoc, nume);
			//System.out.println(toReturn.toString());
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	
	public static ArrayList<Object> findAllProd() {
		ArrayList<Object> toReturns = new ArrayList<Object>();
		//int i=0;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementAllString);
			//findStatement.setInt(1, clientId);
			rs = findStatement.executeQuery();
			while(rs.next()) {
			int id = rs.getInt("idProduct");
			int pret = rs.getInt("pret");
			int stoc = rs.getInt("stoc");
			//String email = rs.getString("email");
			String nume = rs.getString("nume");
			Object c = new Product(id, pret, stoc, nume);
			toReturns.add(c);
			//rs.next();
			}			
			//System.out.println(toReturn.toString());
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturns;
	}
	
	
	public static int insert(Product product,int id) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, id);
			insertStatement.setInt(2, product.getPret());
			insertStatement.setInt(3, product.getStoc());
			insertStatement.setString(4, product.getNume());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	public static void update(int id,int stoc) {
		Connection dbConnection = ConnectionFactory.getConnection();
		
		PreparedStatement updateStatement = null;
		
		
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setInt(1, stoc);
			updateStatement.setInt(2, id);
			updateStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	
	public static void delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		
		PreparedStatement deleteStatement = null;
		
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, id);
			
			deleteStatement.execute();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	public static void main(String[] args) {
	//Product client1=new Product(10,20,"bluza");
	//ProductDAO.update(1, 12, "tricou");
	ProductDAO.delete(121);
	//Product cl=ProductDAO.findById(1);
	//System.out.println(cl.toString());
	//int i=ProductDAO.insert(client1,1);

}
	
}
