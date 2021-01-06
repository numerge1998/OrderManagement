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
import model.Order;

public class OrderDAO {

	protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO command (idOrder,idClient,idProduct,cantitate)"
			+ " VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM command where idOrder = ?";
	private final static String findStatementAllString = "SELECT * FROM command ";
	private final static String updateStatementString = "UPDATE command set cantitate = ? where idOrder = ?";
	private final static String deleteStatementString = "DELETE from command where idOrder = ?";
	
	public static Order findById(int orderId) {
		Order toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, orderId);
			rs = findStatement.executeQuery();
			rs.next();

			int idClient = rs.getInt("idClient");
			int idProduct = rs.getInt("idProduct");
			int cantitate = rs.getInt("cantitate");
			toReturn = new Order(orderId, idClient, idProduct, cantitate);
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
	
	
	public static ArrayList<Object> findAllOrd() {
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
			int id = rs.getInt("idOrder");
			int idc = rs.getInt("idClient");
			int idp = rs.getInt("idProduct");
			int cant = rs.getInt("cantitate");
			Object c = new Order(id, idc, idp, cant);
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
	
	
	public static int insert(Order order,int id) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, id);
			insertStatement.setInt(2, order.getIdClient());
			insertStatement.setInt(3, order.getIdProduct());
			insertStatement.setInt(4, order.getCantitate());
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
	
	public static void update(int id,int cantitate) {
		Connection dbConnection = ConnectionFactory.getConnection();
		
		PreparedStatement updateStatement = null;
		
		
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setInt(1, cantitate);
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
	//Client client1=new Client("Pavel","Alex",10);
	//OrderDAO.update(1, 20);
	//OrderDAO.delete(2);
	//Order cl=OrderDAO.findById(1);
	//System.out.println(cl.toString());
	//int i=ClientDAO.insert(client1,100);
	//Order order = new Order(2,3,10);
	//OrderDAO.insert(order, 1);
	
}
	
}
