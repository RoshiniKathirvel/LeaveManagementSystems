package com.userdetails;
import java.sql.Date;
import java.util.Scanner;

import com.calenderdetails.CentralizedCalendar;
import com.driverclass.DriverClass;
import com.leavecalculation.LeaveBalance;
import com.leavecalculation.LeaveDecision;
import com.leavecalender.Email;
import com.leavecalender.Notification;
/**
 * The  Manager class represents a manager profile, extending the {@code Person} class.
 * It provides methods for performing various manager operations related to employee leave management.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 17 FEB 2024
 */
public class Manager extends Person {
	private  Account account;
    public Manager(Account account) {
		super(account);
    }

	Scanner sc = new Scanner(System.in);
	/**
     * Performs various manager operations, such as viewing leave balance, making leave decisions,
     * viewing leave calendar, and exiting.
     */
    public void operationManager() {
        LeaveDecision ld = new LeaveDecision();
        LeaveBalance lb = new LeaveBalance();
        Notification nf=new Notification();
        boolean exit = true;

        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("|----------------------Welcome " + super.getuserName() +"!!---------------------|");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("|                            1. View Leave Balance                              |");
        System.out.println("|                            2. Making the decision                             |");
        System.out.println("|                            3. View Leave Calendar                             |");
        System.out.println("|                            4. Move to previous                                |");
        System.out.println("|                            5. Exit                                            |");
        System.out.println("|-------------------------------------------------------------------------------|");

        while (exit) {
            try {
                System.out.println("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                 case 1:
                lb.displayLeaveBalance(username, password);
                break;

                    case 2:
                    	
                       ld.makeDecision();
                       long leave_id = nf.getLeave_id(); 
                       long emp_id = nf.getEmp_id(); 
                       String leave_type =nf.getLeave_type(); 
                       Date startDate =(Date) nf.getStartDate(); 
                       Date endDate =(Date) nf.getEndDate(); 
                       String reason =nf.getReason(); 
                       String status =nf.getStatus(); 
                       Email emailNotification = new Email(leave_id, emp_id, leave_type, startDate, endDate, reason, status);
                       emailNotification.sendNotification(); 
                        break;
                    case 3:
                     
                        CentralizedCalendar centralizedCalendar = new CentralizedCalendar(0, "Default", "Default description", 0, new Date(2024, 0, 1));
                        CentralizedCalendar.displayLeaveCalendar();
                        break;
                    case 4:
                    	DriverClass.loginPage();
                    	break;
                    case 5:
                        exit = false;
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Choice. Please enter a valid option.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error occured while fetching the information");
                sc.nextLine(); // consume the invalid input
            }
        }
    }
}
