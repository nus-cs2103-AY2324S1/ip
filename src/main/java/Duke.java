import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
	private final Storage storage;

	private final TaskList taskList;

	public Duke(String filePath) {
		this.storage = new Storage(filePath);
		this.taskList = this.storage.readFromFile();
	}

	/**
	 * The main function where the program starts
	 *
	 * @param args input args
	 */
	public static void main(String[] args) {
		Duke duke = new Duke("data/duke.txt");
		duke.printLine();
		System.out.println("Hello I'm RyamBot");
		System.out.println("What can I do for you?");
		duke.printLine();
		// Interact with the user
		duke.interact();
		duke.printLine();
	}

	/**
	 * Prints a line
	 */
	void printLine() {
		System.out.println("____________________________________________________________");
	}

	public void interact() {
		Parser parser = new Parser(this.taskList, this.storage);
		parser.queryBot();
	}

}
