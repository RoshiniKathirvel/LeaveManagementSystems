package com.leavecalender;

import com.jdbcconnection.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * The  Notification class represents a notification for leave requests.
 * It contains details such as leave ID, employee ID, leave type, start date, end date, reason, and status.
 * This class provides methods to get and set these details and to fetch leave status from the database.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 15 FEB 2024
 */
public class Notification {
    private long leave_id;
    private long emp_id;
    private String leave_type;
    private Date startDate;
    protected Date endDate;
    private String reason;
    private String status;
public Notification() {
	
}
/**
 * Constructs a Notification object with the specified leave details.
 *
 * @param leave_id   The ID of the leave request.
 * @param emp_id     The ID of the employee making the leave request.
 * @param leave_type The type of leave (e.g., sick, casual, unpaid).
 * @param startDate  The start date of the leave request.
 * @param endDate    The end date of the leave request.
 * @param reason     The reason for the leave request.
 * @param status     The status of the leave request (e.g., Approved, Rejected, Submitted).
 */
    public Notification(long leave_id, long emp_id, String leave_type, Date startDate, Date endDate, String reason, String status) {
        this.leave_id = leave_id;
        this.emp_id = emp_id;
        this.leave_type = leave_type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
    }

    public long getLeave_id() {
        return leave_id;
    }

    public void setLeave_id(long leave_id) {
        this.leave_id = leave_id;
    }

    public long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(long emp_id) {
        this.emp_id = emp_id;
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * Fetches the leave status from the database for the given employee ID.
     *
     * @param emp_id The employee ID.
     * @return The leave status or null if not found.
     * @throws ClassNotFoundException If there is an issue with the class loading.
     */
    protected static String fetchLeaveStatusFromDatabase(long emp_id) throws ClassNotFoundException {
        try (Connection conn = JdbcConnection.getDBConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "SELECT status FROM DATABASE.leave_details WHERE emp_id = ?")) {

            preparedStatement.setLong(1, emp_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("No Application has been submitted");
        }

        return null;
    }
}

