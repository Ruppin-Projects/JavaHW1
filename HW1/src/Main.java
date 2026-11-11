
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

// Main class - runs the messaging system and handles user interaction.
public class Main {

	public static void main(String[] args) {

		// Create a list to store all message types using polymorphism.
		ArrayList<Message> messages = new ArrayList<>();

		// Create two BoardMessage objects with different priorities.
		// The shorter constructor automatically sets the date and regular priority.
		BoardMessage board1 = new BoardMessage("Simon", "Hello World", false, "Global");
		messages.add(board1);

		BoardMessage board2 = new BoardMessage("Itay", "Hello Simon",LocalDateTime.now(), false, "Global", Priority.URGENT);
		messages.add(board2);

		// Create two EmailMessage objects.
		EmailMessage email1 = new EmailMessage("Simon", "Hello Itay", false, "Salute");
		messages.add(email1);

		EmailMessage email2 = new EmailMessage("Itay", "Hello Simon",LocalDateTime.now(), false, "Salute-Back",new ArrayList<File>());
		messages.add(email2);

		// Create a File object and attach it to the second email.
		File file1 = new File("report", "pdf");
		email2.addAttachment(file1);

		// Create two SMSMessage objects (our custom message type).
		// Catch the custom exception if a phone number is invalid.
		try {

			SMSMessage sms1= new SMSMessage("Simon","Hello Itay", false, "050101010");
			messages.add(sms1);

			SMSMessage sms2= new SMSMessage("Itay","Hello Simon",LocalDateTime.now(), false, "05111111", true);
			messages.add(sms2);

		}

		catch (InvalidPhoneNumberException e){

			System.out.println(e.getMessage());

		}

		// Scanner is used to read input from the user.
		Scanner scanner = new Scanner(System.in);

		int choice;

		// Keep displaying the menu until the user chooses 0 to exit.
		do{

			System.out.println("\n===== Messaging System =====");
			System.out.println("1. Add a message");
			System.out.println("2. Delete a message");
			System.out.println("3. Print all messages");
			System.out.println("4. Count messages containing specific words");
			System.out.println("5. Print digital messages only");
			System.out.println("6. Mark a message as read");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");

			choice=scanner.nextInt();

			// Perform an action according to the user's menu choice.
			switch(choice) {

			case 0:
				// Exit the program.
				System.out.println("Exiting program...");
				break;

			case 1:
				// Add a new message according to the type selected by the user.
				System.out.println("Choose message type:");
				System.out.println("1. Board Message");
				System.out.println("2. Email Message");
				System.out.println("3. SMS Message");
				System.out.print("Enter type: ");

				int type = scanner.nextInt();
				scanner.nextLine(); // Clear leftover newline

				// Check that the selected message type is valid.
				if (type < 1 || type > 3) {
					System.out.println("Invalid message type.");
					break;
				}

				// Get the common message information.
				System.out.print("Enter sender: ");
				String sender = scanner.nextLine();

				System.out.print("Enter content: ");
				String content = scanner.nextLine();

				try {
					// Create the appropriate message object.
					switch (type) {

						case 1:
							System.out.print("Enter board name: ");
							String boardName = scanner.nextLine();

							BoardMessage board = new BoardMessage(
								sender, content, false, boardName
							);

							messages.add(board);
							break;

						case 2:
							System.out.print("Enter email subject: ");
							String subject = scanner.nextLine();

							EmailMessage email = new EmailMessage(
								sender, content, false, subject
							);

							messages.add(email);
							break;

						case 3:
							System.out.print("Enter recipient phone number: ");
							String phoneNumber = scanner.nextLine();

							SMSMessage sms = new SMSMessage(
								sender, content, false, phoneNumber
							);

							messages.add(sms);
							break;
					}

					System.out.println("Message added successfully.");

				} catch (InvalidPhoneNumberException | IllegalArgumentException e) {
					// Handle invalid message information.
					System.out.println("Error: " + e.getMessage());
				}

				break;

			case 2:
				// Delete a message selected by its number in the list.
				System.out.println("Select a message to delete:");

				for (int i = 0; i < messages.size(); i++) {
					// Display numbers starting from 1 instead of index 0.
					System.out.println((i + 1) + ". " + messages.get(i).generatePreview());
				}

				System.out.print("Enter message number: ");
				int deleteNumber = scanner.nextInt();

				// Check that the selected message exists.
				if (deleteNumber >= 1 && deleteNumber <= messages.size()) {
					messages.remove(deleteNumber - 1);
					System.out.println("Message deleted successfully.");
				} else {
					System.out.println("Invalid message number.");
				}

				break;

			case 3:
				// Print all messages using their overridden toString() methods.
				for (Message msg : messages) {
					System.out.println(msg);
				}
				break;

			case 4:
				// Count messages containing at least one of the entered words.
				ArrayList<String> searchWords = new ArrayList<>();
				int count = 0;

				System.out.print("Enter words to search for (separated by spaces): ");

				scanner.nextLine(); // Clear the newline left by nextInt()
				String input = scanner.nextLine();

				// Split the input into separate words.
				String[] words = input.trim().split("\\s+");

				for (String word : words) {
					searchWords.add(word);
				}

				// Use the find() method from Message to search each message.
				for (Message msg : messages) {
					if (msg.find(searchWords)) {
						count++;
					}
				}

				System.out.println("Number of matching messages: " + count);
				break;

			case 5:
				// Print only messages that implement the IDigital interface.
				for (Message msg : messages) {
					if(msg instanceof IDigital) {
						System.out.println(msg);
					}
				}
				break;

			default:
				// Handle menu numbers that do not exist.
				System.out.println("Invalid choice.");
				break;

			case 6:
				// Optional menu option - mark a selected message as read.
				// Uses the optional isRead field and markRead() method.
				System.out.println("Select a message to mark as read:");

				for (int i = 0; i < messages.size(); i++) {
					System.out.println((i + 1) + ". " + messages.get(i).generatePreview());
				}

				System.out.print("Enter message number: ");
				int messageNumber = scanner.nextInt();

				// Validate the number before accessing the list.
				if (messageNumber >= 1 && messageNumber <= messages.size()) {
					messages.get(messageNumber - 1).markRead();
					System.out.println("Message marked as read.");
				} else {
					System.out.println("Invalid message number.");
				}

				break;
			}

		}while(choice !=0);

	}

}
