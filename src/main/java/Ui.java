import java.util.Scanner;

public class Ui {
	private final Scanner scanner;

	public Ui() {
		this.scanner = new Scanner(System.in);
	}

	public void printDivider() {
		String DIVIDER = "____________________________________________________________";
		System.out.println(DIVIDER);
	}

	/**
	 * Blocks until a line is received from System.in, and returns the input.
	 * @return The input as a string
	 */
	public String getNextInput() {
		return this.scanner.nextLine();
	}

	public void showGreetings() {
		printDivider();
		System.out.println("Hello! I'm CarbonBot");
		System.out.println("What can I do for you?");
		printDivider();
	}

	public void showMessage(String message) { System.out.println(message); }

	/**
	 * Prints the error message when the task list could not be loaded from disk.
	 */
	public void showLoadingError() {
		System.out.println("Failed to load save file from disk.");
	}

	/**
	 * Clears up the resources at the end of the Ui usage.
	 */
	public void close() {
		this.scanner.close();
	}
}
