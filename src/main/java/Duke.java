import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

	static final String botName = "GOAT";
	static final Scanner in = new Scanner(System.in);
	static final List<Task> tasks = new ArrayList<>();
	static final Printer out = new Printer();

	public static void main(String[] args) {
		greet();
		while (true) {
			String input = in.nextLine().trim();
			if (input.equals("bye"))
				break;
			try {
				Command c = Command.parse(input);
				c.execute(out);
			} catch (DukeException e) {
				handleDukeException(e);
			}
		}
		sayBye();
	}

	static void handleDukeException(DukeException e) {
		out.print(e.getMessage());
	}

	static void greet() {
		String logo = "        ______    ___        _    _________ \n"
				+ "      .' ___  | .'   `.     / \\  |  _   _  |\n"
				+ "     / .'   \\_|/  .-.  \\   / _ \\ |_/ | | \\_|\n"
				+ "     | |   ____| |   | |  / ___ \\    | |    \n"
				+ "     \\ `.___]  \\  `-'  /_/ /   \\ \\_ _| |_   \n"
				+ "      `._____.' `.___.'|____| |____|_____|  \n";

		out.print(String.format("Hello from\n%s", logo), String.format("Hello! I'm %s", botName));
	}

	static void sayBye() {
		out.print("Bye. Hope to see you again soon!");
	}
}
