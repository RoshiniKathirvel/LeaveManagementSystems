package com.leavecalender;

import java.sql.Date;
/**
 * The  Email class represents an email notification for leave requests.
 * It extends the {@link Notification} class and provides methods to send notifications
 * based on the status of the leave request.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 15 FEB 2024
 */


public class Email extends Notification {
	/**
     * Constructs an Email object with the specified leave details.
     *
     * @param leave_id   The ID of the leave request.
     * @param emp_id     The ID of the employee making the leave request.
     * @param leave_type The type of leave (e.g., sick, casual, unpaid).
     * @param startDate  The start date of the leave request.
     * @param endDate    The end date of the leave request.
     * @param reason     The reason for the leave request.
     * @param status     The status of the leave request (e.g., Approved, Rejected, Submitted).
     */
    public Email(long leave_id, long emp_id, String leave_type, Date startDate, Date endDate, String reason, String status) {
        super(leave_id, emp_id, leave_type, startDate, endDate, reason, status);
    }
    /**
     * Sends an email notification based on the status of the leave request.
     * If the status is Approved, it notifies the user that the leave request has been approved.
     * If the status is Rejected, it notifies the user that the leave request has been rejected.
     * If the status is neither Approved nor Rejected, it informs the user that the leave request is in the submitted state.
     */
    
        /**
         * Sends an email notification based on the status of the leave request.
         * If the status is Approved, it notifies the user that the leave request has been approved.
         * If the status is Rejected, it notifies the user that the leave request has been rejected.
         * If the status is neither Approved nor Rejected, it informs the user that the leave request is in the submitted state.
         */
        public void sendNotification() {
            if ("Approved".equalsIgnoreCase(getStatus())) {
                String message = "Leave request for " + getEmp_id() + " has been approved.";
                System.out.println("Notification: " + message);
            } else if ("Rejected".equalsIgnoreCase(getStatus())) {
                String message = "Leave request for " + getEmp_id() + " has not approved";
                System.out.println("Notification: " + message);
            } else {
                System.out.println("Your Leave Request is in the submitted state");
            }
        }
      
        
    }
    

