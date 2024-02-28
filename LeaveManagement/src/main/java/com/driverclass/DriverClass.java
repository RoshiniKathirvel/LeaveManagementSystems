package com.driverclass;
import java.util.Scanner;
import java.util.InputMismatchException;

import com.exception.LoginException;
import com.management.SystemManagement;
import com.userdetails.Account;
import com.userdetails.Admin;
import com.userdetails.Employee;
import com.userdetails.HR;
import com.userdetails.Manager;
/**
 * The  DriverClass class serves as the entry point for the Expleo Leave Application.
 * It provides a text-based user interface for logging in as different roles and accessing their functionalities.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 13 FEB 2024  
 */
public class DriverClass {
    public static void main(String[] args) throws com.exception.LoginException, ClassNotFoundException {
        
        System.out.println("|--------------------------------------------------------------------------------------------------------------|");
        System.out.println("|--------------------------------Welcome to Expleo Leave Application-------------------------------------------|");
        System.out.println("|--------------------------------------------------------------------------------------------------------------|");
        System.out.println();
        loginPage();
       
    }
    
    public static void loginPage() throws ClassNotFoundException, LoginException{
    	 Scanner sc = new Scanner(System.in);
    	 System.out.println("***********************************************************************************************");
         System.out.println("*                                     LOGIN PAGE                                              *");
         System.out.println("***********************************************************************************************");
         System.out.println();
         System.out.println("|-------------------------------------------------------------------------|");
         System.out.println("|                              1.System Admin                             |");
         System.out.println("|                              2.HR                                       |");
         System.out.println("|                              3.Employee                                 |");
         System.out.println("|                              4.Manager                                  |");
         System.out.println("|                              5.Forgot Password                          |");
         System.out.println("|                              6.Exit                                     |");
         System.out.println("|-------------------------------------------------------------------------|");
         
         int choice = 0;
         boolean exit = true;
         while (exit) {
             try {
                 System.out.println("Enter your choice : ");
                 choice = sc.nextInt();
                 switch (choice) {
                     case 1:
                     	System.out.println(" |--------------------------------------------------------------------------------------------------------|");
                         System.out.println("|                                    Enter your credentials                                              |");
                         System.out.println("|--------------------------------------------------------------------------------------------------------|");
                         System.out.println("Enter your username: ");
                         String user = sc.next();
                         System.out.println("Enter your password: ");
                         String pass = sc.next();
                         Account ac = new Account(user,pass);
              
                         if(ac.authenticate("Admin")){
                         	Admin ad=new Admin(ac);
                             ad.display();
                         }else {
                         	System.out.println("Invalid UserName or Password");
                         }
                         break;
                     case 2:
                     	 System.out.println("|---------------------------------------------------------------------------------------------------------------|");
                         System.out.println("|                                                        Enter your credentials                                 |");
                         System.out.println("|---------------------------------------------------------------------------------------------------------------|");
                         System.out.println("Enter your username: ");
                         user = sc.next();
                         System.out.println("Enter your password: ");
                         pass = sc.next();
                        Account acfirst = new Account(user,pass);
                         if(acfirst.authenticate("HR")){
                         	HR hr=new HR(acfirst);
                         	hr.operationsHR();
                         }else {
                         	System.out.println("Invalid UserName or Password");
                         }
                         break;
                     case 3:
                     	System.out.println("|------------------------------------------------------------------------------------------------|");
                         System.out.println("|                                          Enter your credentials                                |");
                         System.out.println("|------------------------------------------------------------------------------------------------|");
                         System.out.println("Enter your username: ");
                         user = sc.next();
                         System.out.println("Enter your password: ");
                         pass = sc.next();
                         Account acsecond = new Account(user,pass);
                         if(acsecond.authenticate("Employee")){
                         	Employee em=new Employee(acsecond);
                         	em.employeeOperations();
                         }else {
                         	System.out.println("Invalid UserName or Password");
                         }
                         break;
                     case 4:
                         System.out.println("|---------------------------------------------------------------------------------------------|");
                         System.out.println("|                                         Enter your credentials                              |");
                         System.out.println("|---------------------------------------------------------------------------------------------|");
                         System.out.println("Enter your username: ");
                         user = sc.next();
                         System.out.println("Enter your password: ");
                         pass = sc.next();
                         Account acthird = new Account(user,pass);
                         if(acthird.authenticate("Manager")){
                         	Manager mn=new Manager(acthird);
                         	mn.operationManager();
                         }else {
                         	System.out.println("Invalid UserName or Password");
                         }
                         break;
                     case 5:
                    	 SystemManagement.updatePasswords();
                    	 break;
                     case 6:
                         exit = false;
                         System.out.println("Thank you for using Expleo Leave Application. Goodbye!");
                         System.exit(0);
                         break;
                     default:
                         System.out.println("Invalid Choice");
                         break;
                 }
             } catch (InputMismatchException e) {
                 System.out.println("Please enter a integer value.");
                 sc.nextLine(); // consume the invalid input to avoid an infinite loop
             }
         }
    }
    
   
}
