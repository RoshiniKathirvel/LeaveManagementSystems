package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jdbcconnection.JdbcConnection;
/**
 * The SystemManagement class provides methods for managing employee records,
 * including adding, viewing, updating, and deleting records in the employee database.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 16 FEB 2024
 */
public class SystemManagement {
	static Scanner sc= new Scanner(System.in);
	/**
     * Adds employee records to the database, prompting the user for details such as ID,
     * first name, last name, department ID, designation ID, email, username, password, and contact number.
     *
     * @throws ClassNotFoundException If there is an issue with the class loading.
     */
	public void addRecords() throws ClassNotFoundException {
		try (Connection conn = JdbcConnection.getDBConnection()) {
			
            System.out.println("Enter your details");

            System.out.print("Enter the ID: ");
            int emp_id= sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.print("Enter your First Name: ");
            String fname = sc.nextLine();

            System.out.print("Enter your Last Name: ");
            String lname = sc.nextLine();

            System.out.print("Enter your Department ID: ");
            int deptId = sc.nextInt();

            System.out.print("Enter your Designation ID: ");
            int designationId = sc.nextInt();
            sc.nextLine(); // Consume newline
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

            System.out.print("Enter your Email: ");
            String email = sc.nextLine();

            if (email.matches(emailRegex)) {
                System.out.println("Email is valid.");
            } else {
                System.out.println("Invalid email format.");
            }
            System.out.print("Enter your Username: ");
            String username = sc.nextLine();

            System.out.print("Enter your Password: ");
            String password = sc.nextLine();
            String phoneRegex = "^\\+\\d{10}$";
            System.out.print("Enter your Contact Number: ");
            String contactNumber = sc.nextLine();
            if (contactNumber.matches(phoneRegex)) {
                System.out.println("Phone number is valid.");
            } else {
                System.out.println("Invalid phone number format.");
            }

      // Prepare SQL statement
            String sql = "INSERT INTO DATABASE.employee (emp_id, fname, lname, dept_id, designation_id, email, username, password, contact_number) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, emp_id);
            statement.setString(2, fname);
            statement.setString(3, lname);
            statement.setInt(4, deptId);
            statement.setInt(5, designationId);
            statement.setString(6, email);
            statement.setString(7, username);
            statement.setString(8, password);
            statement.setString(9, contactNumber);

            // Execute the statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new record has been inserted successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Invalid Department ID or Designation ID");
        }
		
	}
	/**
     * Displays all employee records from the database, including details such as ID,
     * first name, last name, department ID, designation ID, email, username, password, and contact number.
     *
     * @throws ClassNotFoundException If there is an issue with the class loading.
     */
	public void viewRecords() throws ClassNotFoundException {
		try (Connection conn = JdbcConnection.getDBConnection()) {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM DATABASE.employee";
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("EMPLOYEE DETAILS:");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-15s %-15s %-10s %-15s %-20s %-15s %-15s %-15s\n",
                    "Emp ID", "First Name", "Last Name", "Dept ID", "Designation ID",
                    "Email", "Username", "Password", "Contact Number");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");

            while (resultSet.next()) {
                int empId = resultSet.getInt("emp_id");
                String fname = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                int deptId = resultSet.getInt("dept_id");
                int designationId = resultSet.getInt("designation_id");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String contactNumber = resultSet.getString("contact_number");

                System.out.printf("%-10s %-15s %-15s %-10s %-15s %-20s %-15s %-15s %-15s\n",
                        empId, fname, lname, deptId, designationId,
                        email, username, password, contactNumber);
            }
        } catch (SQLException e) {
            System.out.println("No record with this employee ID or name");
        }
		
	}
	/**
	 * update the employee records such as first name, last name, address, department Id
	 * 
	 * @param userName
	 */
	public void updateRecords(String userName) {
		
		 try {
	            
	            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("$                    UPDATE EMPLOYEE RECORDS                   $");
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	            
	      

	            System.out.println("Enter employee ID:");
	            int empId = sc.nextInt();
	            sc.nextLine(); // Consume newline left-over

	            System.out.println("Select the field to update:");
	            System.out.println("1. First Name");
	            System.out.println("2. Last Name");
	            System.out.println("3. Department ID");
	            System.out.println("4. Designation ID");
	            System.out.println("5. Email");
	            System.out.println("6. Contact Number");
	            System.out.println("7. Address");
	            System.out.println("8.Exit");
	            
	            boolean flag = true;
	            do {
	            System.out.print("Enter Your Choice : ");
	            int choice = sc.nextInt();
	            sc.nextLine(); // Consume newline left-over

	            
	            switch (choice) {
	                case 1:
	                	updateFirstName(empId);
	                    break;
	                case 2:
	                	updateLastName(empId);
	                    break;
	                case 3:
	                	updateDepartmentId(empId);
	                    break;
	                case 4:
	                	updateDesignationId(empId);
	                    break;
	                case 5:
	                    updateEmail(empId);
	                    break;
	                case 6:
	                    updateContactNumber(empId);
	                    break;
	                case 7:
	                	updateAddress(empId);
	                	break;
	                case 8:
	                	flag=false;
	                	System.out.println("<---THANK YOU FOR USING THIS APPLICATION--->");
	                	break;
	                default:
	                    System.out.println("Invalid choice !! Please enter a valid one");
	                    System.out.println();
	            }
	            }while(flag);
	            
	        } catch (InputMismatchException e) {
	            System.out.println(e);
	            
	        }
		
	}
	
	/**
	 * update (or) change the email address of the employee
	 * 
	 * @param empId
	 */
	public void updateEmail(int empId) {
	    try {
	   
	        System.out.println("Enter new email:");
	        String newEmail = sc.nextLine();

	        Connection conn = JdbcConnection.getDBConnection();

	        // Query to update the email field
	        String query = "UPDATE PROJECTDB.employee SET email = ? WHERE emp_id = ?";
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, newEmail);
	        pstmt.setInt(2, empId);

	        int rowsAffected = pstmt.executeUpdate();
	        System.out.println(rowsAffected + " row(s) updated successfully.");

	        pstmt.close();
	        conn.close();
	    } catch (ClassNotFoundException | SQLException e) {
	        System.out.println(e);
	    }
	}

	// Method to validate email format using regular expression
	public boolean isValidEmail(String email) {
	    String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}
	
	// Method to validate contact number
	public boolean isValidContactNumber(long number) {
        if (number <= 0) {
            return false;
        }
        int digitCount = String.valueOf(number).length();
        final int VALID_CONTACT_NUMBER_LENGTH = 10;

        return digitCount == VALID_CONTACT_NUMBER_LENGTH;
    }
	
	/**
	 * update the contact number of the employee
	 * 
	 * @param empId
	 */
	public void updateContactNumber(int empId) {
	    try {
	       
	        Connection conn = JdbcConnection.getDBConnection();
	        
	        System.out.println("Enter new contact number:");
	        long newContactNumber = sc.nextLong();

	        // Query to update the contact number field
	        if(isValidContactNumber(newContactNumber)) {
	        String query = "UPDATE PROJECTDB.employee SET contact_number = ? WHERE emp_id = ?";
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setLong(1, newContactNumber);
	        pstmt.setInt(2, empId);

	        int rowsAffected = pstmt.executeUpdate();
	        System.out.println(rowsAffected + " row(s) updated successfully.");
	        pstmt.close();
	        conn.close();
	        }

	        
	    } catch (ClassNotFoundException | SQLException e) {
	        System.out.println(e);
	    } 
	}
	
	/**
	 * update the first name of the employee
	 * 
	 * @param empId
	 */
	public void updateFirstName(int empId) {
        try {
            
            System.out.println("Enter new first name:");
            String newFirstName = sc.nextLine();

            Connection conn = JdbcConnection.getDBConnection();

            // Query to update the first name field
            String query = "UPDATE PROJECTDB.employee SET fname = ? WHERE emp_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newFirstName);
            pstmt.setInt(2, empId);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated successfully.");

            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
	}
	
	/**
	 * update the LastName of the employee
	 * 
	 * @param empId
	 */
	public void updateLastName(int empId) {
        try {
            
            System.out.println("Enter new last name:");
            String newLastName = sc.nextLine();

            Connection conn = JdbcConnection.getDBConnection();

            // Query to update the last name field
            String query = "UPDATE PROJECTDB.employee SET lname = ? WHERE emp_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newLastName);
            pstmt.setInt(2, empId);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated successfully.");

            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
	
	/**
	 * update the department details
	 * 
	 * @param empId
	 */
	public void updateDepartmentId(int empId) {
        try {

            System.out.println("Enter new department ID:");
            int newDepartmentId = sc.nextInt();

            Connection conn = JdbcConnection.getDBConnection();

            // Query to update the department ID field
            String query = "UPDATE PROJECTDB.employee SET dept_id = ? WHERE emp_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, newDepartmentId);
            pstmt.setInt(2, empId);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated successfully.");

            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
	
	/**
	 * update the designation Id
	 * 
	 * @param empId
	 */
	public void updateDesignationId(int empId) {
        try {
            
            System.out.println("Enter new designation ID:");
            int newDesignationId = sc.nextInt();

            Connection conn = JdbcConnection.getDBConnection();

            // Query to update the designation ID field
            String query = "UPDATE PROJECTDB.employee SET designation_id = ? WHERE emp_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, newDesignationId);
            pstmt.setInt(2, empId);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated successfully.");

            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
             System.out.println(e);
        }
    }
	
	/**
	 * update the address of the employee
	 * 
	 * @param empId
	 */
	public void updateAddress(int empId) {
        try {

            System.out.println("Enter new address:");
            String newAddress = sc.nextLine();

            Connection conn = JdbcConnection.getDBConnection();

            // Query to update the address field
            String query = "UPDATE PROJECTDB.employee SET address = ? WHERE emp_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newAddress);
            pstmt.setInt(2, empId);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated successfully.");

            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
	/**
     * Updates an employee's password in the database based on the provided Employee ID.
     * Prompts the user to enter the Employee ID and the new password.
     *
     * @throws ClassNotFoundException If there is an issue with the class loading.
     */
	public static void updatePasswords() throws ClassNotFoundException {
		System.out.print("Enter the Employee ID : ");
		int empId = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter the new password: ");
        String newPassword = sc.nextLine();

        try (Connection conn = JdbcConnection.getDBConnection()) {
            String sql = "UPDATE employee SET password = ? WHERE emp_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setInt(2, empId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Password updated successfully for Employee ID: " + empId);
            } else {
                System.out.println("No employee found with ID: " + empId);
            }
        } catch (SQLException e) {
            System.out.println("Failed to update the password");
        } 
		
	}
	/**
     * Deletes an employee record from the database based on the provided Employee ID.
     * Prompts the user to enter the Employee ID for deletion.
     *
     * @throws ClassNotFoundException If there is an issue with the class loading.
     */
	public void deleteRecords() throws ClassNotFoundException {
		System.out.println("Delete an employee record");
        System.out.print("Enter the Employee ID: ");
        int empId = sc.nextInt();
        sc.nextLine(); // Consume newline

        try (Connection conn = JdbcConnection.getDBConnection()) {
            String sql = "DELETE FROM DATABASE.employee WHERE emp_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, empId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee record with ID " + empId + " deleted successfully.");
            } else {
                System.out.println("No employee record found with ID: " + empId);
            }
        } catch (SQLException e) {
            System.out.println("Error occured while deleting the record");
        } 
	}
}