package com.jdbcconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * The JdbcConnection class provides a utility for establishing a JDBC connection to an Oracle database.
 * It contains a method to retrieve a database connection using the JDBC driver.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 14 FEB 2024
 */
public class JdbcConnection {
	/**
     * JDBC URL for connecting to the Oracle database.
     */
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	/**
     * Username used for authenticating the database connection.
     */
    private static final String USER_NAME = "DATABASE";
    /**
     * Password used for authenticating the database connection.
     */
    private static final String PASSWORD = "DATABASE";

    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return con;
    }
}