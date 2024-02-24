package com.userdetails;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.calenderdetails.CentralizedCalendar;
import com.driverclass.DriverClass;
import com.exception.LoginException;
import com.management.SystemManagement;
/**
 * The Admin class represents an admin profile, extending the {@code Person} class.
 * It provides methods to display and perform administrative tasks.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 16 FEB 2024
 */
public class Admin extends Person {
    private  Account account;
    /**
     * Constructs an {@code Admin} with the specified account.
     *
     * @param account The account associated with the admin.
     */
    public Admin(Account account) {
		super(account);
	}

	public void display() throws ClassNotFoundException, LoginException {
        Scanner sc = new Scanner(System.in);

        System.out.println("|--------------------------------------------------------------------------------|");
        System.out.println("                       Welcome " + super.getuserName()+"!!                       ");
        System.out.println("|--------------------------------------------------------------------------------|");
        System.out.println();
        System.out.println("|---------------------------------------------------------|");
        System.out.println("|    1. Create Record for the employee                    |");
        System.out.println("|    2. View the datas of the employee                    |");
        System.out.println("|    3. Update the password of the employee               |");
        System.out.println("|    4. Delete Records of the employees                   |");
        System.out.println("|    5. View Leave Calendar                               |");
        System.out.println("|    6. Move To Previous                                  |");
        System.out.println("|    7. Exit                                              |");
        System.out.println("|---------------------------------------------------------|");
        System.out.println();
        SystemManagement acc = new SystemManagement();

        boolean flag = true;
       
        while (flag) {
            try {
                
            	 System.out.println("Enter Your Option : ");
                 int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        acc.addRecords();
                        break;
                    case 2:
                        acc.viewRecords();
                        break;
                    case 3:
                        acc.updateRecords();
                        break;
                    case 4:
                        acc.deleteRecords();
                        break;
                    case 5:
                        CentralizedCalendar centralizedCalendar = new CentralizedCalendar(0, "Default", "Default description", 0, new Date(2024, 0, 1));
                        CentralizedCalendar.displayLeaveCalendar();
                        break;
                    case 6:
                    	DriverClass.loginPage();
                    	break;
                    case 7:
                        flag = false;
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Choice. Please enter a valid option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer option.");
                sc.nextLine(); 
            }
        }
    }
}
