
import java.time.LocalDateTime;

// Task 6 - Our custom message type: SMS.
// Inherits from Message and implements IDigital because SMS uses digital communication.
public class SMSMessage extends Message implements IDigital {

	// Additional fields chosen for our custom SMS message.
	private String phoneNumber;
	private boolean deliveryStatus;

	// Main constructor - receives all fields, including the inherited Message
	// fields.
	// Uses super() to initialize the fields belonging to the parent class.
	public SMSMessage(String sender, String content, LocalDateTime sendDate, boolean isRead, String phoneNumber,
			boolean deliveryStatus) {
		super(sender, content, sendDate, isRead);

		// Checks that the phone number is not null or blank.
		// Throws our custom exception if the phone number is invalid.
		if (phoneNumber == null || phoneNumber.isBlank()) {
			throw new InvalidPhoneNumberException("Phone number cannot be null/blank.");
		}

		this.phoneNumber = phoneNumber;
		this.deliveryStatus = deliveryStatus;
	}

	// Second constructor - automatically sets the current date and time.
	// Delivery status is set to false by default.
	// Calls the main constructor to avoid repeating validation code.
	public SMSMessage(String sender, String content, boolean isRead, String phoneNumber) {
		this(sender, content, LocalDateTime.now(), isRead, phoneNumber, false);
	}

	// Returns the recipient's phone number.
	public String getPhoneNumber() {
		return phoneNumber;
	}

	// Returns whether the SMS has been delivered.
	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}

	// Updates the delivery status of the SMS.
	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	// Custom implementation of the abstract generatePreview() method.
	// Shows the sender, recipient's phone number, and message content.
	@Override
	public String generatePreview() {
		return "[SMS] From: " + sender + " | To: " + phoneNumber + " | " + content;
	}

	// Implementation of the IDigital interface method.
	// Describes the communication method used for SMS messages.
	@Override
	public String printCommunicationMethod() {
		return "Sent via Cellular Network (SMS).";
	}

	// Returns all SMS information, including the inherited Message fields.
	@Override
	public String toString() {
		return "SMSMessage - phoneNumber=" + phoneNumber + ", deliveryStatus= " + deliveryStatus + ", Message details: "
				+ super.toString();
	}

}
