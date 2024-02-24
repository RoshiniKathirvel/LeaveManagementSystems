package com.userdetails;
import java.sql.Date;
import com.exception.LoginException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.calenderdetails.CentralizedCalendar;
import com.driverclass.DriverClass;
import com.leavecalculation.LeaveBalance;
import com.leavecalculation.RequestLeave;
/**
 * The  Employee class represents an employee profile, extending the {@code Person} class.
 * It provides methods for performing various operations related to employee leave and leave requests.
 *
 * @author Roshini Kathirvel (Expleo)
 * @since 17 FEB 2024
 */
public class Employee extends Person {
	private  Account account;

    public Employee(Account account) {
		super(account);
	}
    /**
     * Performs various employee operations, such as viewing leave balance, applying leave,
     * modifying leave requests, canceling leave requests, checking leave application status,
     * viewing leave calendar, and exiting.
     *
     * @throws ClassNotFoundException If there is an issue with the class loading.
     * @throws LoginException 
     */
	public  void employeeOperations() throws ClassNotFoundException, LoginException {
        Scanner sc = new Scanner(System.in);
        LeaveBalance lb = new LeaveBalance();
        RequestLeave lc = new RequestLeave();

        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("                       Welcome " + super.getuserName() +"!!                     ");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("|                            1. View Balance Leave                              |");
        System.out.println("|                            2. Apply Leave                                     |");
        System.out.println("|                            3. Modify Leave                                    |");
        System.out.println("|                            4. Cancel Leave                                    |");
        System.out.println("|                            5. Status of the Leave Application                 |");
        System.out.println("|                            6. View Leave Calendar                             |");
        System.out.println("|                            7. Move to Previous                                |");
        System.out.println("|                            8.Exit                                             |");
        System.out.println("|-------------------------------------------------------------------------------|");

        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                    	
                    	lb.displayLeaveBalance(super.getuserName(),super.getPassword());
                    	break;
                    case 2:
                        System.out.println("APPLY LEAVE TYPE");
                        System.out.println();
                        System.out.println("1. CASUAL LEAVE");
                        System.out.println("2. SICK LEAVE");
                        System.out.println("3.Move to previous");
                        System.out.println("4. EXIT");
                        lc.applyLeave(super.getuserName());
                        break;
                    case 3:
                        lc.modifyLeaveRequest(super.getuserName());
                        break;
                    case 4:
                        lc.cancelLeaveRequest();
                        break;
                    case 5:
                        System.out.println("Enter your ID :");
                        int emp_id=sc.nextInt();
                        lc.viewStatus(emp_id);
                        break;
                    case 6:
                        CentralizedCalendar centralizedCalendar = new CentralizedCalendar(0, "Default", "Default description", 0, new Date(2024, 0, 1));
                        CentralizedCalendar.displayLeaveCalendar();
                        break;
                    case 7:
                    	DriverClass.loginPage();
                    	break;
                    case 8:
                    	flag=false;
                    	System.exit(0);
                    	break;
                    default:
                        System.out.println("Invalid Choice. Please enter a valid option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer option.");
                sc.nextLine(); 
            }
        }
    }
}
