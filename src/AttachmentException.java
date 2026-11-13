
/* 
 * Custom exception for errors related to email attachments.
 * Used when trying to remove a file that does not exist in the attachments list.
 */
public class AttachmentException extends Exception {
	// Constructor - passes the error message to the parent Exception class.
	public AttachmentException(String message) {
		super(message);
	}
}
