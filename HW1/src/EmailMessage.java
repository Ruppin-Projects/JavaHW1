import java.time.LocalDateTime;
import java.util.ArrayList;
public class EmailMessage extends Message implements IDigital {
	
	private String subject;
	private ArrayList<File> attachments;

	@Override
	public String generatePreview() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String printCommunicationMethod() {
		
		return ("Sent via Email Server");
	}
	public EmailMessage(String sender, String content, LocalDateTime sendDate, boolean isRead, String subject, ArrayList<File> attachments) {
		super(sender, content, sendDate, isRead);
		if (subject == null || subject.isBlank()) {
			throw new IllegalArgumentException("Subject cannot be empty");
			
		}
		if(attachments == null) {
			throw new IllegalArgumentException("Attachments list cannot be null");
		}
		this.subject=subject;
		this.attachments=attachments;
	}
	public EmailMessage(String sender, String content, boolean isRead, String subject) {
		this(sender, content, LocalDateTime.now(), isRead, subject, new ArrayList<File>());
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		if (subject==null || subject.isBlank()) {
			throw new IllegalArgumentException("Subject cannot be empty");
		}
		this.subject = subject;
	}
	public ArrayList<File> getAttachments() {
		return attachments;
	}
	
	

}
