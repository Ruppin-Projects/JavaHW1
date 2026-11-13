
import java.util.Objects;

/*
 * Represents a file attached to an email.
 * Stores the file's name and type.
 */
public class File {

	private String fileName;
	private String fileType;

	// Constructor - initializes the file after validating both parameters.
	public File(String fileName, String fileType) {
		// File name and type cannot be null, empty, or contain only spaces.
		if (fileName == null || fileName.isBlank() || fileType == null || fileType.isBlank()) { // check empty strings.
			throw new IllegalArgumentException("One of the arguments is empty/invalid.");
		}
		this.fileName = fileName;
		this.fileType = fileType;
	}

	// Getters and setters for the file's fields.
	public String getFileName() {
		return fileName;
	}

	// Validates the new file name before changing it.
	public void setFileName(String fileName) {

		if (fileName == null || fileName.isBlank()) {
			throw new IllegalArgumentException("File name cannot be empty.");
		}
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	// Validates the new file type before changing it.
	public void setFileType(String fileType) {
		if (fileType == null || fileType.isBlank()) {
			throw new IllegalArgumentException("File type cannot be empty.");
		}
		this.fileType = fileType;
	}
	
	// Returns a string containing all the file's information.
	@Override
	public String toString() {
		return "File [fileName=" + fileName + ", fileType=" + fileType + "]";
	}

	// Additional method - generates a hash code based on the file name and type.
	// Needed to keep hashCode consistent with our equals method.
	@Override
	public int hashCode() {
		return Objects.hash(fileName, fileType);
	}

	// Additional method - compares files by their name and type.
	// Used by contains() and remove() when managing email attachments.
	@Override
	public boolean equals(Object obj) {

		// If both references point to the same object, they are equal.
		if (this == obj)
			return true;

		// A file cannot be equal to null.
		if (obj == null)
			return false;

		// The objects must belong to the same class.
		if (!(obj instanceof File))
			return false;

		// Cast the object to File so we can compare its fields.
		File other = (File) obj;

		// Both file name and file type must match.
		return Objects.equals(fileName, other.fileName) && Objects.equals(fileType, other.fileType);

	}

}
