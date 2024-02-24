package com.userdetails;
/**
 * The Person class represents a generic person with a username and password.
 * It serves as a base class for different profiles in the leave management system.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 19 FEB 2024
 */
public class Person {
    protected String username;
    protected String password;
    private  Account account;
    /**
     * Constructs a {@code Person} with the specified account.
     *
     * @param account The account associated with the person.
     */
    public Person(Account account) {
           this.account=account;
    }

    public String getuserName() {
          return account.getuserName();
    }


    public void setuserName() {
   	 this.username=account.getuserName();
    }
    
    public String getPassword() {
          return account.getPassword();
    }
    
    public void setPassword() {
   	 this.password=account.getPassword();
    }
}