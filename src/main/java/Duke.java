import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

	private static final String BOT_NAME = "GOAT";
	private static final String SAVE_FILEPATH = "data/save.txt";

	private static final Scanner in = new Scanner(System.in);
	private static final TaskList taskList = new TaskList();
	private static final Printer out = new Printer();
	private static final SaveFile saveFile = new SaveFile(SAVE_FILEPATH);
	private static final CommandFactory commandFactory = new CommandFactory(out, taskList, saveFile);

	public static void main(String[] args) {
		initialize();
		while (true) {
			String input = in.nextLine().trim();
			if (input.equals("bye"))
				break;
			try {
				Command c = commandFactory.parse(input);
				c.execute();
			} catch (DukeException e) {
				handleDukeException(e);
			}
		}
		sayBye();
	}

	static void handleDukeException(DukeException e) {
		out.print(e.getMessage());
	}

	static void initialize() {
		String logo = "        ______    ___        _    _________ \n"
				+ "      .' ___  | .'   `.     / \\  |  _   _  |\n"
				+ "     / .'   \\_|/  .-.  \\   / _ \\ |_/ | | \\_|\n"
				+ "     | |   ____| |   | |  / ___ \\    | |    \n"
				+ "     \\ `.___]  \\  `-'  /_/ /   \\ \\_ _| |_   \n"
				+ "      `._____.' `.___.'|____| |____|_____|  \n";

		out.print(String.format("Hello from\n%s", logo), String.format("Hello! I'm %s", BOT_NAME));
		try {
			saveFile.readFromSaveFile();
		} catch(Exception e) {
			out.print("Cannot read from savefile");
		}
	}

	static void sayBye() {
		out.print("Bye. Hope to see you again soon!");
	}
}
