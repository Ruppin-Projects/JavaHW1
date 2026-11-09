import java.time.LocalDateTime;
import java.util.ArrayList;
public abstract class Message {
	
	protected String sender;
	protected String content;
	protected LocalDateTime sendDate;
	protected boolean isRead; // Optional
	
	
	public Message (String sender, String content, LocalDateTime sendDate, boolean isRead) {
		if (sender == null || sender.isBlank()) {
			throw new IllegalArgumentException("Sender cannot be empty.");
		}
		if (content == null || content.isBlank()) {
			throw new IllegalArgumentException("Content cannot be empty.");
		}
		if(sendDate == null) {
			throw new IllegalArgumentException("Send date cannot be null.");
		}
		this.sender=sender;
		this.content=content;
		this.sendDate=sendDate;
		this.isRead=isRead;
		
	}
	public Message (String sender, String content,boolean isRead) {
		this(sender, content, LocalDateTime.now(), isRead);
		
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		if (content == null || content.isBlank()) {
			throw new IllegalArgumentException("Content cannot be empty.");
		}
		this.content = content;
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
	@Override
	public String toString() {
		return "Message [sender=" + sender + ", content=" + content + ", sendDate=" + sendDate + ", isRead=" + isRead
				+ "]";
	}

	public boolean find(ArrayList<String> words) {
		for(String word :words) {
			if(content.contains(word)) {
				return true;
			}
			
			
		}
		return false;
		
	}
	public void markRead() {
		this.isRead=true;
		
		
	}
	public abstract String generatePreview();
}
