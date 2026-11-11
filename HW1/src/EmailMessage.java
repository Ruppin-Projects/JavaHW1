
import java.time.LocalDateTime;
import java.util.ArrayList;

// Represents an email message that inherits from Message.
// Implements IDigital because emails use digital communication.
public class EmailMessage extends Message implements IDigital {

	private String subject;
	private ArrayList<File> attachments;

	// Returns a short preview containing the subject and sender.
	@Override
	public String generatePreview() {
		return ("[Email] Subject: " +subject + " | From: " +sender);
	}

	// Implementation of the IDigital interface method.
	// Describes how an email is sent.
	@Override
	public String printCommunicationMethod() {

		return ("Sent via Email Server.");
	}

	// Main constructor - receives all fields, including inherited ones.
	// Calls the Message constructor using super() to avoid duplicating code.
	public EmailMessage(String sender, String content, LocalDateTime sendDate, boolean isRead, String subject, ArrayList<File> attachments) {
		super(sender, content, sendDate, isRead);

		// The subject cannot be null or blank.
		if (subject == null || subject.isBlank()) {
			throw new IllegalArgumentException("Subject cannot be empty.");

		}

		// The attachments list must exist, but it can be empty.
		if(attachments == null) {
			throw new IllegalArgumentException("Attachments list cannot be null.");
		}

		this.subject=subject;
		this.attachments=attachments;
	}

	// Second constructor - automatically sets the current date
	// and creates an empty attachments list.
	// Uses the first constructor to avoid repeating code.
	public EmailMessage(String sender, String content, boolean isRead, String subject) {
		this(sender, content, LocalDateTime.now(), isRead, subject, new ArrayList<File>());
	}

	// Getter and setter for the email subject.
	public String getSubject() {
		return subject;
	}

	// Checks that the new subject is valid before updating it.
	public void setSubject(String subject) {
		if (subject==null || subject.isBlank()) {
			throw new IllegalArgumentException("Subject cannot be empty.");
		}
		this.subject = subject;
	}

	// Returns the list of files attached to this email.
	public ArrayList<File> getAttachments() {
		return attachments;
	}

	// Returns all email information, including inherited Message fields.
	@Override
	public String toString() {
		return "EmailMessage [subject=" + subject + ", attachments=" + attachments + ", Message details: " + super.toString()
				+ "]";
	}

	// Adds a file to the attachments list.
	// A null file cannot be added.
	public void addAttachment(File file) {
		if(file==null) {
			throw new IllegalArgumentException("File cannot be null.");
		}

		attachments.add(file);
	}

	// Removes all matching files from the attachments list.
	// Throws our custom AttachmentException if the file is not found.
	public void removeAttachment(File file) throws AttachmentException {
		if(!attachments.contains(file)) {
			throw new AttachmentException("The file does not exist in the list.");
		}

		// Keep removing matching files until none remain.
		// Uses the equals() method we implemented in File.
		while(attachments.contains(file)) {
			attachments.remove(file);

		}

	}

}
