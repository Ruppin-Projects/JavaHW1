import java.time.LocalDateTime;

public class BoardMessage extends Message {

	private String boardName;
	private Priority priority; // enum
	
	
	@Override
	public String generatePreview() {
		String part;
		if(content.length()<=15) {
			part= content;
		}
		else {
			part= content.substring(0,15);
			part+= "...";
		}
		
		return ("[Board] " +sender + ": " + part);
	}

	public BoardMessage(String sender, String content, LocalDateTime sendDate, boolean isRead, String boardName, Priority priority) {
		
		super(sender, content, sendDate, isRead);
		if(boardName == null || boardName.isBlank()) {
			throw new IllegalArgumentException("Board name cannot be empty.");
			
		}
		
		if(priority==null) {
			throw new IllegalArgumentException("Priority cannot be empty.");
		}
		this.boardName=boardName;
		this.priority = priority;
	}
	
	public BoardMessage(String sender, String content, boolean isRead, String boardName) {
		this(sender,content,LocalDateTime.now() ,isRead, boardName,Priority.REGULAR);
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		if(boardName == null || boardName.isBlank()) {
			throw new IllegalArgumentException("Board name cannot be empty.");
			
		}
		this.boardName = boardName;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		if(priority==null) {
			throw new IllegalArgumentException("Priority cannot be empty.");
		}
		this.priority = priority;
	}

	@Override
	public String toString() {
		return super.toString() + ", Board: " + boardName + " Priority: " + priority;
	}
	
	public boolean isUrgent() {
		return priority == Priority.URGENT;
	}
	
}
