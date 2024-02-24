package com.leavecalculation;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.exception.LoginException;
import com.jdbcconnection.JdbcConnection;
import com.userdetails.Account;
import com.userdetails.Employee;
/**
 * The LeaveBalance class provides functionality to display leave balances for employees.
 * It includes methods to view overall leave balance, sick leave balance, and casual leave balance.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 14 FEB 2024
 */

public class LeaveBalance {
	
	static JdbcConnection jc=new JdbcConnection();
    static Scanner sc = new Scanner(System.in);
    /**
     * Displays leave balance options and processes user choices.
     *
     * @throws ClassNotFoundException Thrown when the required class is not found.
     * @throws LoginException 
     */
	public static void displayLeaveBalance(String userName,String password) throws ClassNotFoundException, LoginException {
		System.out.println();
		System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------|");
		System.out.println("|                                                VIEW LEAVE BALANCE                                                                   |");
		System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------|");
		System.out.println();
		System.out.println("|------------------------------------------------------------------|");
		System.out.println("|                       1.OVERALL LEAVE BALANCE                    |");
		System.out.println("|                       2.SICK LEAVE BALANCE                       |");
		System.out.println("|                       3.CASUAL LEAVE BALANCE                     |");
		System.out.println("|                       4.MOVE TO PREVIOUS                         |");
		System.out.println("|                       5.EXIT                                     |");
		System.out.println("|------------------------------------------------------------------|");
		
		boolean flag = true;
		System.out.println("Enter Employee ID : ");
        int id = sc.nextInt();
		
		while(flag) {
			System.out.print("ENTER YOUR CHOICE : ");
			int choice = sc.nextInt();
		
			switch(choice) {
			case 1:
				overallLeaveBalance(id);
				break;
			case 2:
				int sick_Leave_Balance = sickLeaveBalance(id);
				System.out.println("Employee ID: " + id);
                System.out.println("Sick Leave Balance: " + sick_Leave_Balance);
				break;
			case 3:
				int casual_Leave_Balance = casualLeaveBalance(id);
				System.out.println("Employee ID: " + id);
                System.out.println("Casual Leave Balance: " + casual_Leave_Balance);
				break;
			case 4:
				Account acc= new Account(userName,password);
				Employee emp = new Employee(acc);
				emp.employeeOperations();
				flag=false;
				break;
			case 5:
				flag=false;
				System.exit(0);
				break;
			}
				
		}
		}
	/**
     * Displays the overall leave balance for an employee.
     *
     * @param id The employee ID.
     * @throws ClassNotFoundException Thrown when the required class is not found.
     */
	
	public static void overallLeaveBalance(int id) throws ClassNotFoundException {
	    try (Connection conn = JdbcConnection.getDBConnection()) {
	        String sql = "SELECT causal_leave_balance, sick_leave_balance FROM DATABASE.leave_balance WHERE emp_id = ?";
	        try (PreparedStatement statement = conn.prepareStatement(sql)) {
	            statement.setInt(1, id);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    int casualLeaveBalance = resultSet.getInt("causal_leave_balance");
	                    int sickLeaveBalance = resultSet.getInt("sick_leave_balance");
	                    System.out.println("Employee ID: " + id);
	                    System.out.println("Casual Leave Balance: " + casualLeaveBalance);
	                    System.out.println("Sick Leave Balance: " + sickLeaveBalance);
	                } else {
	                    System.out.println("Employee with ID " + id + " not found.");
	                }
	            }
	        }
	    } catch (SQLException e) {
	    	  System.out.println("There is no balance of leave");
	    }
	}
	/**
     * Retrieves the sick leave balance for an employee.
     *
     * @param id The employee ID.
     * @return The sick leave balance.
     * @throws ClassNotFoundException Thrown when the required class is not found.
     */
	public static int sickLeaveBalance(int id) throws ClassNotFoundException {
		try (Connection conn = JdbcConnection.getDBConnection()) {
            
            String sql = "SELECT sick_leave_balance FROM DATABASE.leave_balance WHERE emp_id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int sickLeaveBalance = resultSet.getInt("sick_leave_balance");
                        return sickLeaveBalance;
                     } else {
                        System.out.println("Employee with ID " + id + " not found.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("There is no balance of leave");
        }
		return 0;
	}
	/**
     * Retrieves the casual leave balance for an employee.
     *
     * @param id The employee ID.
     * @return The casual leave balance.
     * @throws ClassNotFoundException Thrown when the required class is not found.
     */
	public static int casualLeaveBalance(int id) throws ClassNotFoundException {
	    try (Connection conn = JdbcConnection.getDBConnection()) {
	        String sql = "SELECT casual_leave_balance FROM DATABASE.leave_balance WHERE emp_id = ?";
	        try (PreparedStatement statement = conn.prepareStatement(sql)) {
	            statement.setInt(1, id);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    int casualLeaveBalance = resultSet.getInt("casual_leave_balance");
	                    return casualLeaveBalance;
	                } else {
	                    System.out.println("Employee with ID " + id + " not found.");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("wrong date to apply");
	    }
	    return 0;
	}

}