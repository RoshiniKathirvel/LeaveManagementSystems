package com.management;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.leavecalender.Notification;

/**
 * The LeaveManagement class provides methods for managing leave-related operations,
 * such as viewing leave history, generating a list of employees with leave applications,
 * and viewing details of a specific leave based on leave ID.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 16 FEB 2024
 */

import com.jdbcconnection.JdbcConnection;
import com.leavecalender.Email;

public class LeaveManagement {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public LeaveManagement() throws ClassNotFoundException {
        try {
            connection = JdbcConnection.getDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Displays the leave history for a given employee based on employee ID.
     */
    public void manage() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter employee ID:");
        int emp_id = sc.nextInt();

        try {
            String query = "SELECT * FROM leave_details WHERE emp_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, emp_id);
            resultSet = preparedStatement.executeQuery();

            // Process and display the leave history for the given employee
            System.out.println("Leave History for Employee ID " + emp_id + ":");
            System.out.println("--------------------------------------------------");

            while (resultSet.next()) {
                int leaveId = resultSet.getInt("leave_id");
                String leaveType = resultSet.getString("leave_type");
                java.sql.Date startDate = resultSet.getDate("start_date");
                java.sql.Date endDate = resultSet.getDate("end_date");
                String reason = resultSet.getString("reason");
                String status = resultSet.getString("status");

                System.out.println("Leave ID: " + leaveId);
                System.out.println("Leave Type: " + leaveType);
                System.out.println("Start Date: " + startDate);
                System.out.println("End Date: " + endDate);
                System.out.println("Reason: " + reason);
                System.out.println("Status: " + status);
                System.out.println("--------------------------------------------------");
                
            }

        } catch (SQLException e) {
            System.out.println("No employee history is shown");
        }
    }
    /**
     * Generates a list of unique employee IDs with leave applications.
     */
    public void generate() {
        try {
            String query = "SELECT DISTINCT emp_id FROM leave_details";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            // Process and display a list of unique employee IDs with leave applications
            System.out.println("Employees with Leave Applications:");
            System.out.println("----------------------------------");

            while (resultSet.next()) {
                int empId = resultSet.getInt("emp_id");
                System.out.println("Employee ID: " + empId);
            }

        } catch (SQLException e) {
            System.out.println("No employee on leave");
        }
    }
    /**
     * Views details of a specific leave based on leave ID.
     */
    public void view() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter leave ID:");
        int leaveId = sc.nextInt();

        try {
            String query = "SELECT * FROM leave_details WHERE leave_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, leaveId);
            resultSet = preparedStatement.executeQuery();

            // Process and display the details of a specific leave based on leave ID
            System.out.println("Leave Details for Leave ID " + leaveId + ":");
            System.out.println("--------------------------------------------------");

            while (resultSet.next()) {
                int empId = resultSet.getInt("emp_id");
                String leaveType = resultSet.getString("leave_type");
                java.sql.Date startDate = resultSet.getDate("start_date");
                java.sql.Date endDate = resultSet.getDate("end_date");
                String reason = resultSet.getString("reason");
                String status = resultSet.getString("status");

                System.out.println("Employee ID: " + empId);
                System.out.println("Leave Type: " + leaveType);
                System.out.println("Start Date: " + startDate);
                System.out.println("End Date: " + endDate);
                System.out.println("Reason: " + reason);
                System.out.println("Status: " + status);
                System.out.println("--------------------------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("There is no records to fetch");
        }
    }
    /**
     * Closes the database connection and associated resources.
     */
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Closing the connection");
        }
    }
}
