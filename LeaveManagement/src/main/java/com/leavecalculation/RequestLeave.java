package com.leavecalculation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.driverclass.DriverClass;
import com.exception.LoginException;
import com.jdbcconnection.JdbcConnection;
/**
 * The RequestLeave class manages the process of applying, canceling, and modifying leave requests.
 * It interacts with the database to handle leave-related operations.
 *
 * @author Roshini Kathirvel (Expleo)
 * @since 14 FEB 2024
 */
public class RequestLeave {
	static JdbcConnection jc=new JdbcConnection();
	
	static Scanner sc = new Scanner(System.in);
	
	
	public static void applyLeave(String username) throws ClassNotFoundException, LoginException {
		boolean flag = true;
		
		while(flag) {
			System.out.print("Enter Your Choice : ");
			int choice = sc.nextInt();
		
			
			switch(choice) {
			case 1:
				casualLeaveRequest(username);
				break;
			case 2:
				sickLeaveRequest(username);
				break;
			case 3:
				DriverClass.loginPage();
            	break;
			case 4:
				flag=false;
				 System.exit(0);
				break;
			}
		}
	}
	
	/**
     * Processes the leave application for sick leave.
     *
     * @throws ClassNotFoundException Thrown when the required class is not found.
     */
private static  void sickLeaveRequest(String username) throws ClassNotFoundException {
		System.out.println("SICK LEAVE REQUEST");
		
		int emp_id=fetchEmployeeId(username);
        sc.nextLine();
		
		int sick_Leave_Balance = LeaveBalance.sickLeaveBalance(emp_id);
		System.out.println("NOW YOU ENTERED INTO LEAVE REQUEST APPLICATION");
		System.out.println();
		
		String startDateString = validateDateInput("Start Date (YYYY-MM-DD): ");
        Date startDate = Date.valueOf(startDateString);

        String endDateString = validateDateInput("End Date (YYYY-MM-DD): ");
        Date endDate = Date.valueOf(endDateString);
        sc.nextLine();
        LocalDate start = LocalDate.parse(startDateString);
        LocalDate end = LocalDate.parse(endDateString);

        long calculatedDays = ChronoUnit.DAYS.between(start, end);
        String cal = "SELECT sick_leave_balance FROM DATABASE.leave_balance WHERE emp_id=?";
        int casualLeave = 0;

        try (Connection conn = JdbcConnection.getDBConnection()) {
            // Prepare the SQL statement
            String sql = cal;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,emp_id);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the result from the query
            if (resultSet.next()) {
                casualLeave = resultSet.getInt("casual_leave");
                System.out.println("Remaining Leave: " + casualLeave);
            } else {
                System.out.println("Employee not found or error in retrieving leave details.");
            }
        } catch (SQLException e) {
        	 System.out.println("Your leave days are more as your balanced days");
        }

        // Continue with your logic using casualLeave
        if (calculatedDays > casualLeave) {
            System.out.println("Entered with your balance days");
        } else {
            System.out.println("Enter correct days");
        }

        System.out.print("Reason: ");
        String reason = sc.nextLine();

        try (Connection conn = JdbcConnection.getDBConnection()) {
            String sql = "INSERT INTO DATABASE.leave_details (emp_id, leave_type, start_date, end_date, reason) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setInt(1,emp_id);
            statement.setString(2, "SICK");
            statement.setDate(3, startDate);
            statement.setDate(4, endDate);
            statement.setString(5, reason);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Sick Leave request submitted successfully!");
            } else {
                System.out.println("Failed to submit leave request.");
            }
        } catch (SQLException e) {
        	System.out.println("Wrong leave request ID or choice");
        }
		
	}
/**
 * Processes the leave application for casual leave.
 *
 * @throws ClassNotFoundException Thrown when the required class is not found.
 */
	private static void casualLeaveRequest(String username) throws ClassNotFoundException {
		System.out.println("CASUAL LEAVE REQUEST");
		int emp_id=fetchEmployeeId(username);
        sc.nextLine();
        
        // Prompt the user to enter details for the leave request
		System.out.println();
		System.out.println("NOW WE ENTER INTO LEAVE REQUEST APPLICATION");
		System.out.println();

		String startDateString = validateDateInput("Start Date (YYYY-MM-DD): ");
        Date startDate = Date.valueOf(startDateString);

        String endDateString = validateDateInput("End Date (YYYY-MM-DD): ");
        Date endDate = Date.valueOf(endDateString);
        sc.nextLine();
        LocalDate start = LocalDate.parse(startDateString);
        LocalDate end = LocalDate.parse(endDateString);

        long calculatedDays = ChronoUnit.DAYS.between(start, end);
        System.out.println("calculatedDays: " + calculatedDays);

        String cal = "SELECT causal_leave_balance FROM DATABASE.leave_balance WHERE emp_id=?";
        int casualLeave = 0;

        try (Connection conn = JdbcConnection.getDBConnection()) {
            // Prepare the SQL statement
            String sql = cal;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,emp_id);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the result from the query
            if (resultSet.next()) {
                casualLeave = resultSet.getInt("casual_leave");
                System.out.println("Remaining Leave: " + casualLeave);
            } else {
                System.out.println("Employee not found or error in retrieving leave details.");
            }
        } catch (SQLException e) {
        	 System.out.println("");
        }

        // Continue with your logic using casualLeave
        if (calculatedDays > casualLeave) {
            System.out.println("proceed with your reason");
        } else {
            System.out.println("Enter with your balance days");
        }

        
        System.out.print("Reason: ");
        String reason = sc.nextLine();

        try (Connection conn = JdbcConnection.getDBConnection()) {
            String sql = "INSERT INTO DATABASE.leave_details (emp_id, leave_type, start_date, end_date, reason) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setInt(1,emp_id);
            statement.setString(2, "CASUAL");
            statement.setDate(3, startDate);
            statement.setDate(4, endDate);
            statement.setString(5, reason);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Leave request submitted successfully!");
            } else {
                System.out.println("Failed to submit leave request.");
            }
        } catch (SQLException e) {
        	System.out.println("Wrong leave request ID or choice");
        }
		
	}
	/**
     * Displays the leave balance for the user.
     *
     * @throws ClassNotFoundException Thrown when the required class is not found.
	 * @throws LoginException 
     */
	public static void displayLeaveBalance() throws ClassNotFoundException, LoginException {
		LeaveBalance.displayLeaveBalance(null, null);
	}
	/**
     * Cancels a leave request based on user input.
     *
     * @throws ClassNotFoundException Thrown when the required class is not found.
     */
	public static void cancelLeaveRequest() throws ClassNotFoundException {
		System.out.println();
		System.out.println("CANCELLING LEAVE REQUEST");
		System.out.println();
		try (Connection conn = JdbcConnection.getDBConnection()) {
            System.out.print("Enter Leave ID to cancel: ");
            int leaveId = sc.nextInt();
            String sql = "DELETE FROM DATABASE.leave_details WHERE leave_id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, leaveId);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Leave request with ID " + leaveId + " cancelled successfully.");
                } else {
                    System.out.println("Leave request with ID " + leaveId + " not found.");
                }
            }
        } catch (SQLException e) {
        	System.out.println("Wrong leave request ID or choice");
        }
	}
	/**
     * Validates date input format and ensures it is the current date or a future date.
     *
     * @param message The message prompting the user to enter a date.
     * @return A validated date string.
     */
	 public static String validateDateInput(String message) {
	        boolean isValidDate = false;
	        LocalDate currentDate = LocalDate.now();
	        String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
	        LocalDate inputDate = null;

	        do {
	            try {
	                System.out.println(message);
	                String inputDateString = sc.next();
	                if (!inputDateString.matches(dateRegex)) {
	                    throw new InputMismatchException("You may have entered an incorrect date format. Please ensure it follows the correct format (YYYY-MM-DD).");
	                }

	                inputDate = LocalDate.parse(inputDateString);
	                if (inputDate.isEqual(currentDate)||inputDate.isAfter(currentDate)) {
	                    isValidDate = true;
	                } else {
	                    throw new InputMismatchException("Please enter the current date or a future date.");
	                }
	            } catch (InputMismatchException | java.time.format.DateTimeParseException e) {
	                sc.nextLine(); // Consume invalid input
	                System.out.println("Invalid date. Please enter a valid date.");
	            }
	        } while (!isValidDate);

	        return inputDate.toString();
	    }
	 /**
	     * Modifies a leave request by canceling the existing one and applying a new leave request.
	     *
	     * @throws ClassNotFoundException Thrown when the required class is not found.
	 * @throws LoginException 
	     */
	public static void modifyLeaveRequest(String username) throws ClassNotFoundException, LoginException {
		
		System.out.println();
		System.out.println("MODIFYING LEAVE REQUEST");
		RequestLeave.cancelLeaveRequest();
		RequestLeave.applyLeave(username);
		
	}
	/**
     * Views the status of a leave application for a specific employee.
     *
     * @param empId The employee ID to check for leave application status.
     * @throws ClassNotFoundException Thrown when the required class is not found.
     */
	    public static void viewStatus(int empId) throws ClassNotFoundException {
	        System.out.println("Showing your status of the application");

	        try (Connection conn = JdbcConnection.getDBConnection()) {
	            String sql = "SELECT status FROM DATABASE.leave_details WHERE emp_id = ?";
	            try (PreparedStatement statement = conn.prepareStatement(sql)) {
	                statement.setInt(1, empId);
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    if (resultSet.next()) {
	                        String status = resultSet.getString("status");
	                        System.out.println("Your leave application status: " + status);
	                    } else {
	                        System.out.println("No leave application found for Employee ID " + empId);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	        	System.out.println("Wrong leave request ID or choice");
	        }
	    }
	    public static int fetchEmployeeId(String username) throws ClassNotFoundException {
	        int empId = -1; 

	        try {
	            
	        	Connection connection = JdbcConnection.getDBConnection();

	            String query = "SELECT emp_id FROM DATABASE.employee WHERE username = ?";
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setString(1, username);

	            
	            ResultSet resultSet = statement.executeQuery();

	            
	            if (resultSet.next()) {
	                empId = resultSet.getInt("emp_id");
	            }
	            
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	        return empId;
	    }

	}
