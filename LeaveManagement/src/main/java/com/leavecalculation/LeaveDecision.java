package com.leavecalculation;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.jdbcconnection.JdbcConnection;
/**
 * The  LeaveDecision class handles the decision-making process for leave requests.
 * It connects to the database, fetches leave requests with 'Submitted' status, and processes them based on leave type.
 *
 * @author Roshini Kathirvel (Expleo)
 * @since 14 FEB 2024
 */
public class LeaveDecision {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public void makeDecision() throws ClassNotFoundException {
        try {
            // Connect to the database
        	
        	connection = JdbcConnection.getDBConnection();
            // Fetch leave requests with status 'Requested'
        	// Notification nf=new Notification();
            String query = "SELECT leave_id, emp_id, leave_type, start_date, end_date, reason FROM DATABASE.leave_details WHERE status = 'Submitted'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            processLeaveRequests(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection, statement, and result set
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
               System.out.println("Connection Establishment failed");
            }
        }
    }
    /**
     * Processes the leave requests retrieved from the database.
     *
     * @param resultSet The ResultSet containing leave requests.
     * @throws SQLException             Thrown for SQL-related errors.
     * @throws ClassNotFoundException Thrown when the required class is not found.
     */

    private static void processLeaveRequests(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        while (resultSet.next()) {
            int leaveId = resultSet.getInt("leave_id");
            int employeeId = resultSet.getInt("emp_id");
            String leaveType = resultSet.getString("leave_type");
            Date startDate = resultSet.getDate("start_date");
            Date endDate = resultSet.getDate("end_date");
            String reason = resultSet.getString("reason");

            // Process the leave request based on leave type
            if (leaveType.equalsIgnoreCase("casual")) {
                int leaveBalance = LeaveBalance.casualLeaveBalance(employeeId);
                if (leaveBalance > 0) {
                    approveLeaveRequestAndUpdateBalance(leaveId, "Approved", "casual_leave_balance", 1);
                } else {
                    rejectLeaveRequest(leaveId, "Rejected");
                }
            } else if (leaveType.equalsIgnoreCase("sick")) {
                int leaveBalance = LeaveBalance.sickLeaveBalance(employeeId);
                if (leaveBalance > 0) {
                    approveLeaveRequestAndUpdateBalance(leaveId, "Approved", "sick_leave_balance", 1);
                } else {
                    rejectLeaveRequest(leaveId, "Rejected");
                }
            } 
        }
    }
    /**
     * Approves a leave request and updates its status in the database.
     *
     * @param leaveId The ID of the leave request.
     * @param status  The new status of the leave request.
     * @throws SQLException Thrown for SQL-related errors.
     */
    private static void approveLeaveRequest(int leaveId, String status) throws SQLException {
        String updateQuery = "UPDATE DATABASE.leave_details SET status = ? WHERE leave_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, leaveId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Leave request with ID " + leaveId + " has been approved successfully.");
            } else {
                System.out.println("Failed to find leave request with ID " + leaveId);
            }
        }
    }
    /**
     * Approves a leave request, updates its status, and deducts the corresponding leave balance.
     *
     * @param leaveId        The ID of the leave request.
     * @param status         The new status of the leave request.
     * @param balanceColumn  The column representing the leave balance to be updated.
     * @param deduction      The amount to deduct from the leave balance.
     * @throws SQLException Thrown for SQL-related errors.
     */
    private static void approveLeaveRequestAndUpdateBalance(int leaveId, String status, String balanceColumn, int deduction)
            throws SQLException {
        approveLeaveRequest(leaveId, status); // Approve the leave request

        // Update leave balance
        String updateBalanceQuery = "UPDATE leave_balance SET " + balanceColumn + " = " + balanceColumn + " - ? WHERE emp_id = ?";
        try (PreparedStatement balanceStatement = connection.prepareStatement(updateBalanceQuery)) {
            balanceStatement.setInt(1, deduction);
            balanceStatement.setInt(2, leaveId); // Assuming emp_id is used for leave balance lookup
            int balanceRowsAffected = balanceStatement.executeUpdate();

            if (balanceRowsAffected > 0) {
                System.out.println("Leave balance for emp_id " + leaveId + " has been updated successfully.");
                
                // Commit changes to the database
                connection.commit();
            } else {
                System.out.println("Failed to update leave balance for emp_id " + leaveId);
            }
        }
    }

    /**
     * Rejects a leave request and updates its status in the database.
     *
     * @param leaveId The ID of the leave request.
     * @param status  The new status of the leave request.
     * @throws SQLException Thrown for SQL-related errors.
     */
    private static void rejectLeaveRequest(int leaveId, String status) throws SQLException {
        String updateQuery = "UPDATE DATABASE.leave_details SET status = ? WHERE leave_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, leaveId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Leave request with ID " + leaveId + " has been rejected successfully.");
            } else {
                System.out.println("Failed to find leave request with ID " + leaveId);
            }
        }
    }
}