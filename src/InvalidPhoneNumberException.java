
/*
 * Task 6 - Custom exception for our SMSMessage class.
 * Thrown when a phone number is null or blank.
 * */
public class InvalidPhoneNumberException extends RuntimeException {

	// Constructor - passes the error message to the parent Exception class.
	public InvalidPhoneNumberException(String message) {
		super(message);
	}

}
