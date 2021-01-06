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

public class ClientDAO {

	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO client (idClient,Nume,Prenume,Varsta)"
			+ " VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM client where idClient = ?";
	private final static String findStatementAllString = "SELECT * FROM client";
	private final static String updateStatementString = "UPDATE client set Nume = ?, Prenume = ?, Varsta = ? where idClient = ?";
	private final static String deleteStatementString = "DELETE from client where idClient = ?";
	
	public static Client findById(int clientId) {
		Client toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, clientId);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("Nume");
			String prenume = rs.getString("Prenume");
			//String email = rs.getString("email");
			int varsta = rs.getInt("Varsta");
			toReturn = new Client(clientId, name, prenume, varsta);
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
	
	public static ArrayList<Object> findAll() {
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
			int id = rs.getInt("idClient");
			String name = rs.getString("Nume");
			String prenume = rs.getString("Prenume");
			//String email = rs.getString("email");
			int varsta = rs.getInt("Varsta");
			Object c = new Client(id, name, prenume, varsta);
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
	
	
	public static int insert(Client client,int id) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, id);
			insertStatement.setString(2, client.getNume());
			insertStatement.setString(3, client.getPrenume());
			insertStatement.setInt(4, client.getVarsta());
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
	
	public static void update(int id,String nume,String prenume,int varsta) {
		Connection dbConnection = ConnectionFactory.getConnection();
		
		PreparedStatement updateStatement = null;
		
		
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setString(1, nume);
			updateStatement.setString(2, prenume);
			updateStatement.setInt(3, varsta);
			updateStatement.setInt(4, id);
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
	//Client client1=new Client("Pop","Alin",20);
	//ClientDAO.update(100, "Oana", "Crisan", 25);
	//ClientDAO.delete(2);
	//Client cl=ClientDAO.findById(100);
	//System.out.println(cl.toString());
	//int i=ClientDAO.insert(client1,1);

}
	
}
