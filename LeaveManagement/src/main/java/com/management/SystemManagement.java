package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.jdbcconnection.JdbcConnection;
/**
 * The SystemManagement class provides methods for managing employee records,
 * including adding, viewing, updating, and deleting records in the employee database.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 16 FEB 2024
 */
public class SystemManagement {
	Scanner sc= new Scanner(System.in);
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
     * Updates an employee's password in the database based on the provided Employee ID.
     * Prompts the user to enter the Employee ID and the new password.
     *
     * @throws ClassNotFoundException If there is an issue with the class loading.
     */
	public void updateRecords() throws ClassNotFoundException {
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