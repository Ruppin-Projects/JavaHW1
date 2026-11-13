
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

// Main class - runs the messaging system and handles user interaction.
public class Main {

	// Create a list to store all message types using polymorphism.
	public static ArrayList<Message> messages = new ArrayList<>();

	public static void main(String[] args) {

		// Set dummy messages for initial load.
		setDummyMessages();
		// Scanner is used to read input from the user.
		Scanner scanner = new Scanner(System.in);

		int choice = -1;
		// Keep displaying the menu until the user chooses 0 to exit.
		do {
			printMenu();

			try {
				choice = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Error: bad input.");
				continue;
			} finally {
				scanner.nextLine();
			}

			// Perform an action according to the user's menu choice.
			switch (choice) {

			case 0:
				// Exit the program.
				System.out.println("Exiting program...");
				break;

			case 1:
				userAddNewMessage(scanner);
				break;

			case 2:
				userDeleteMessage(scanner);
				break;

			case 3:
				// Print all messages using their overridden toString() methods.
				for (Message msg : messages) {
					System.out.println(msg);
				}
				break;

			case 4:
				userCountMsgWords(scanner);
				break;

			case 5:
				// Print only messages that implement the IDigital interface.
				if (messages.size() == 0) {
					System.out.println("No messages yet.");
					break;
				}
				for (Message msg : messages) {
					if (msg instanceof IDigital) {
						System.out.println(msg);
					}
				}
				break;

			case 6:
				// Optional menu option - mark a selected message as read.
				// Uses the optional isRead field and markRead() method.
				if (messages.size() == 0) {
					System.out.println("No messages yet.");
					break;
				}
				System.out.println("Select a message to mark as read:");

				for (int i = 0; i < messages.size(); i++) {
					System.out.println((i + 1) + ". " + messages.get(i).generatePreview());
				}

				System.out.print("Enter message number: ");
				try {
					int messageNumber = scanner.nextInt();
					// Validate the number before accessing the list.
					if (messageNumber >= 1 && messageNumber <= messages.size()) {
						messages.get(messageNumber - 1).markRead();
						System.out.println("Message marked as read.");
					} else {
						System.out.println("Invalid message number.");
					}
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
				break;

			default:
				// Handle menu numbers that do not exist.
				System.out.println("Invalid choice.");
				break;
			}
		} while (choice != 0);
		scanner.close();
	}

	public static void setDummyMessages() {
		// Create two BoardMessage objects with different priorities.
		// The shorter constructor automatically sets the date and regular priority.
		BoardMessage board1 = new BoardMessage("Simon", "Hello World", false, "Global");
		messages.add(board1);

		BoardMessage board2 = new BoardMessage("Itay", "Hello Simon", LocalDateTime.now(), false, "Global",
				Priority.URGENT);
		messages.add(board2);

		// Create two EmailMessage objects.
		EmailMessage email1 = new EmailMessage("Simon", "Hello Itay", false, "Salute");
		messages.add(email1);

		EmailMessage email2 = new EmailMessage("Itay", "Hello Simon", LocalDateTime.now(), false, "Salute-Back",
				new ArrayList<File>());
		messages.add(email2);

		// Create a File object and attach it to the second email.
		File file1 = new File("report", "pdf");
		email2.addAttachment(file1);

		// Create two SMSMessage objects (our custom message type).
		SMSMessage sms1 = new SMSMessage("Simon", "Hello Itay", false, "050101010");
		messages.add(sms1);

		SMSMessage sms2 = new SMSMessage("Itay", "Hello Simon", LocalDateTime.now(), false, "05111111", true);
		messages.add(sms2);

	}

	public static void userAddNewMessage(Scanner s) {
		// Add a new message according to the type selected by the user.
		System.out.println("\nChoose message type:");
		System.out.println("1. Board Message");
		System.out.println("2. Email Message");
		System.out.println("3. SMS Message");
		System.out.print("Enter type: ");
		try {
			int type = s.nextInt();
			s.nextLine(); // Clear leftover newline

			// Check that the selected message type is valid.
			if (type < 1 || type > 3) {
				System.out.println("Invalid message type.");
				return;
			}

			// Get the common message information.
			System.out.print("Enter sender: ");
			String sender = s.nextLine();

			System.out.print("Enter content: ");
			String content = s.nextLine();

			// Create the appropriate message object.
			switch (type) {

			case 1:
				System.out.print("Enter board name: ");
				String boardName = s.nextLine();

				BoardMessage board = new BoardMessage(sender, content, false, boardName);

				messages.add(board);
				break;

			case 2:
				System.out.print("Enter email subject: ");
				String subject = s.nextLine();

				EmailMessage email = new EmailMessage(sender, content, false, subject);

				messages.add(email);
				break;

			case 3:
				System.out.print("Enter recipient phone number: ");
				String phoneNumber = s.nextLine();
				if (phoneNumber == null || !phoneNumber
						.matches("/^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$/gmi")) {
					throw new InvalidPhoneNumberException("Phone number is not valid.");
				}
				SMSMessage sms = new SMSMessage(sender, content, false, phoneNumber);

				messages.add(sms);
				break;
			}

			System.out.println("Message added successfully.");

		} catch (InvalidPhoneNumberException e) {
			// Handle invalid phone number information.
			System.out.println("Phone number error: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			// Handle invalid message information.
			System.out.println("Invalid message information error: " + e.getMessage());
		} catch (Exception e) {
			// Handle any unexpected errors.
			System.out.println("Error: " + e.getMessage());
			s.nextLine();
		}
	}

	public static void userDeleteMessage(Scanner s) {
		// Delete a message selected by its number in the list.
		if (messages.size() == 0) {
			System.out.println("No messages to delete.");
			return;
		}
		System.out.println("Select a message to delete:");

		for (int i = 0; i < messages.size(); i++) {
			// Display numbers starting from 1 instead of index 0.
			System.out.println((i + 1) + ". " + messages.get(i).generatePreview());
		}

		int deleteNumber;
		System.out.print("Enter message number: ");
		try {
			deleteNumber = s.nextInt();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			s.nextLine();
			return;
		}

		// Check that the selected message exists.
		if (deleteNumber >= 1 && deleteNumber <= messages.size()) {
			messages.remove(deleteNumber - 1);
			System.out.println("Message deleted successfully.");
		} else {
			System.out.println("Invalid message number.");
		}

	}

	public static void userCountMsgWords(Scanner s) {
		// Count messages containing at least one of the entered words.
		if (messages.size() == 0) {
			System.out.println("No messages yet.");
			return;
		}
		ArrayList<String> searchWords = new ArrayList<>();
		int count = 0;

		System.out.print("Enter words to search for (separated by spaces): ");

		try {

			String input = s.nextLine();

			// Split the input into separate words.
			String[] words = input.trim().split("\\s+");

			for (String word : words) {
				searchWords.add(word);
			}

			// Use the find() method from Message to search each message.
			for (Message msg : messages) {
				if (msg.find(searchWords))
					count++;
			}

			System.out.println("Number of matching messages: " + count);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			s.nextLine();
		}
	}

	public static void printMenu() {

		System.out.print("\n===== Messaging System =====\n" + "1. Add a message\n" + "2. Delete a message\n"
				+ "3. Print all messages\n" + "4. Count messages containing specific words\n"
				+ "5. Print digital messages only\n" + "6. Mark a message as read\n" + "0. Exit\n"
				+ "Enter your choice: ");

	}

}
