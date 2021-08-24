/**
 * 
 */

package iSMSThread.jdbc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author gorba
 *
 */
public class JDBC {
	
	String Driver = "com.mysql.jdbc.Driver";
	
	String path;
	
	String user;
	
	String password;
	
	Connection connection = null;
	
	Statement statement = null;
	
	String dbname;
	
	public JDBC(String pwd, String user, String chemin) {
		password = pwd;
		this.user = user;
		path = chemin;
		try {
			Class.forName(Driver); // chargement du pilote
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException:   ");
			System.err.println(e.getMessage());
		}
		try {
			connection = (Connection) DriverManager.getConnection(path, this.user, password);
			statement = (Statement) connection.createStatement();
		} catch (SQLException ex) {
			Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/*
	 * Pour les requ�tes Selections
	 */
	public ResultSet executeQuery(String sql) {
		
		try {
			
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException ex) {
			Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	/*
	 * Pour les requ�tes de modifications
	 */
	public int executeUpdate(String sql) {
		
		try {
			
			Statement statement = (Statement) connection.createStatement();
			int rs = statement.executeUpdate(sql);
			return rs;
		} catch (SQLException ex) {
			Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
		}
		return -1;// Update n'a pas réussi
		
	}
}
