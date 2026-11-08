
public class File {
	
	private String fileName;
	private String fileType;
	
	
	public File (String fileName, String fileType) {
		if (fileName == null || fileName.isBlank() ||fileType == null || fileType.isBlank() ) { // check empty strings.
			throw new IllegalArgumentException("One of the arguments is empty/invalid.");
			
		}
		this.fileName=fileName;
		this.fileType=fileType;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		if (fileName == null || fileName.isBlank()) {
			throw new IllegalArgumentException("File name cannot be empty.");
			
		}
		this.fileName = fileName;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		if(fileType == null || fileType.isBlank()) {
			throw new IllegalArgumentException("File type cannot be empty.");
		}
		this.fileType = fileType;
	}


	@Override
	public String toString() {
		return "File [fileName=" + fileName + ", fileType=" + fileType + "]";
	}
	
	
	
}
