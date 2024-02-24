package com.userdetails;

import java.sql.Date;
import java.util.Scanner;

import com.calenderdetails.CentralizedCalendar;
import com.driverclass.DriverClass;
import com.management.LeaveManagement;
/**
 * The HR class represents a Human Resources (HR) profile, extending the {@code Person} class.
 * It provides methods for performing various HR operations related to employee leave management.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 17 FEB 2024
 */
public class HR extends Person {
	private  Account account;
	private String Account;
    public HR(Account account) {
		super(account);
}
    /**
     * Performs various HR operations, such as managing employee leave, generating leave reports,
     * viewing leave details, viewing leave calendar, and exiting.
     *
     * @throws ClassNotFoundException If there is an issue with the class loading.
     */
	public void operationsHR() throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        LeaveManagement lm = new LeaveManagement();
        System.out.println("|-----------------------------------------------------------------------------|");
        System.out.println("|----------------------Welcome " + super.getuserName() +"!!-------------------|");
        System.out.println("|-----------------------------------------------------------------------------|");
        System.out.println("|                       1. Manage Leave of the employee                       |");
        System.out.println("|                       2. Generate Leave Report                              |");
        System.out.println("|                       3. View Leave                                         |");
        System.out.println("|                       4. View Calendar                                      |");
        System.out.println("|                       5. Move to previous                                   |");
        System.out.println("|                       6. Exit                                               |");
        System.out.println("|-----------------------------------------------------------------------------|");

        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        lm.manage();
                        break;
                    case 2:
                        lm.generate();
                        break;
                    case 3:
                        lm.view();
                        break;
                    case 4:
                        CentralizedCalendar centralizedCalendar = new CentralizedCalendar(0, "Default", "Default description", 0, new Date(2024, 0, 1));
                        CentralizedCalendar.displayLeaveCalendar();
                        break;
                    case 5:
                    	DriverClass.loginPage();
                    	break;
                    case 6:
                        flag = false;
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Choice. Please enter a valid option.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer option.");
                sc.nextLine(); // consume the invalid input
            }
        }
    }
}
