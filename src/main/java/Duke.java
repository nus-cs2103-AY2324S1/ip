import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

	static String botName = "GOAT";
	static Scanner input = new Scanner(System.in);
	static List<String> tasks = new ArrayList<>();

	public static void main(String[] args) {
		String logo = "\t   ______    ___        _    _________ \n"
				+ "\t .' ___  | .'   `.     / \\  |  _   _  |\n"
				+ "\t/ .'   \\_|/  .-.  \\   / _ \\ |_/ | | \\_|\n"
				+ "\t| |   ____| |   | |  / ___ \\    | |    \n"
				+ "\t\\ `.___]  \\  `-'  /_/ /   \\ \\_ _| |_   \n"
				+ "\t `._____.' `.___.'|____| |____|_____|  \n";
		System.out.println("\tHello from\n" + logo);

		greet();
		while (true) {
			String command = input.nextLine().trim();
			if (command.equals("bye"))
				break;
			switch (command) {
				case "list":
					listTasks();
					break;
				default:
					addTask(command);
			}
		}
		sayBye();
	}

	static void listTasks() {
		printLine();
		for (int i = 0; i < tasks.size(); ++i)
			System.out.printf("\t %d. %s\n", i + 1, tasks.get(i));
		printLine();
	}

	static void addTask(String task) {
		tasks.add(task);

		printLine();
		System.out.printf("\t added: %s\n", task);
		printLine();
	}

	static void greet() {
		printLine();
		System.out.printf("\tHello! I'm %s\n", botName);
		System.out.println("\tWhat can I do for you?");
		printLine();
	}

	static void sayBye() {
		printLine();
		System.out.println("\tBye. Hope to see you again soon!");
		printLine();
	}

	static void printLine() {
		String line = "\t____________________________________________________________";
		System.out.println(line);
	}
}
