package com.exception;
/**
 * The LoginException class represents an exception specific to login-related issues.
 * It is a subclass of the  class and is thrown when there are problems with user authentication.
 *
 * @author Roshini Kathirvel(Exploe)
 * @since 14 FEB 2024
 */
public class LoginException extends Exception {
    public LoginException(String msg) {
        super(msg);
    }
}
/**
 * The  InputMismatchException class represents an exception for input mismatch situations.
 * It is a subclass of the class and is thrown when there are discrepancies in expected input.
 *
 */
class InputMismatchException extends Exception{
	public InputMismatchException(String msg) {
		super(msg);
	}
}