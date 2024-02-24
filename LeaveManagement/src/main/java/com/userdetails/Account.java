package com.userdetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.exception.LoginException;
import com.jdbcconnection.JdbcConnection;
/**
 * The  Account class represents a user account with a username and password.
 * It provides methods for authentication and accessing account information.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 16 FEB 2024
 */
public class Account {
    private String user;
    private String pass;
    public Account(String user,String pass) {
    	this.user=user;
    	this.pass=pass;
    }

 public String getuserName() {
		return user;
	}

	public void setuserName(String user) {
		this.user = user;
	}

	public String getPassword() {
		return pass;
	}

	public void setPassword(String pass) {
		this.pass = pass;
	}
	/**
     * Authenticates the account by verifying the username, password, and role.
     *
     * @param role The role associated with the account (e.g., "Admin", "HR", "Employee", "Manager").
     * @return {@code true} if the authentication is successful, {@code false} otherwise.
     * @throws LoginException         If there is an issue with the login process.
     * @throws ClassNotFoundException If there is an issue with the class loading.
     */
public  boolean authenticate(String role)throws LoginException, ClassNotFoundException {
        try (Connection connection= JdbcConnection.getDBConnection()) {
            if (isUserValid(connection,user,pass,role)) {
                System.out.println("Login successful!");
                if (role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("HR") ||
                        role.equalsIgnoreCase("Employee") || role.equalsIgnoreCase("Manager")) {
                        return true;
                    }
                
            } 
           return false;
        } catch (SQLException e){
            System.out.println("Invalid username or password");
        }
        return false;
    }
 
 private static boolean isUserValid(Connection connection, String user, String pass, String role) throws SQLException, LoginException {
     String tableName = getTableName(role);

     String query = "SELECT * FROM " + tableName + " WHERE username = ? AND password = ?";
     try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
         preparedStatement.setString(1, user);
         preparedStatement.setString(2, pass);
         ResultSet resultSet = preparedStatement.executeQuery();
         
         if (!resultSet.next()) {
             return false;
         }
     }
	return true;
 }

 private static String getTableName(String role) {
     if (role.equalsIgnoreCase("Employee") || role.equalsIgnoreCase("Manager")) {
         return "employee";
     } else {
         return "admin";
     }
 }
}
