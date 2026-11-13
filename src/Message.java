
import java.time.LocalDateTime;
import java.util.ArrayList;

// Abstract parent class for all message types.
// Contains the fields and methods shared by all messages.
public abstract class Message {

	protected String sender;
	protected String content;
	protected LocalDateTime sendDate;
	protected boolean isRead = false; // Optional field - indicates whether the message has been read.

	// Main constructor - receives all fields and validates the required
	// information.
	public Message(String sender, String content, LocalDateTime sendDate, boolean isRead) {
		// Sender and content cannot be null or blank.
		if (sender == null || sender.isBlank()) {
			throw new IllegalArgumentException("Sender cannot be empty.");
		}
		if (content == null || content.isBlank()) {
			throw new IllegalArgumentException("Content cannot be empty.");
		}
		// The sending date must be provided.
		if (sendDate == null) {
			throw new IllegalArgumentException("Send date cannot be null.");
		}
		this.sender = sender;
		this.content = content;
		this.sendDate = sendDate;
		this.isRead = isRead;

	}

	// Second constructor - automatically uses the current date and time.
	// Calls the main constructor to avoid repeating validation code.
	public Message(String sender, String content, boolean isRead) {
		this(sender, content, LocalDateTime.now(), isRead);

	}

	// Getters and setters for the message fields.
	public String getContent() {
		return content;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public String getSender() {
		return sender;
	}

	public LocalDateTime getSendDate() {
		return sendDate;
	}

	// Searches the message content for any of the words in the given list.
	// Returns true if at least one word is found, otherwise false.
	public boolean find(ArrayList<String> words) {
		for (String word : words) {
			if (content.contains(word))
				return true;
		}
		return false;

	}

	// Optional method - marks the current message as read.
	public void markRead() {
		this.isRead = true;
	}

	// Abstract method - each message type must implement its own preview format.
	public abstract String generatePreview();

	// Returns a string containing all the common message information.
	@Override
	public String toString() {
		return "Message - sender=" + sender + ", content=" + content + ", sendDate=" + sendDate + ", isRead=" + isRead;
	}
}
