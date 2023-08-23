import java.util.Scanner;

public class Duke {
	public static void main(String[] args) {
		printLine();
		TaskList taskList = new TaskList(); // Initialise the list
		System.out.println("Hello I'm RyamBot");
		System.out.println("What can I do for you?");
		printLine();
		queryBot(taskList);
	}

	public static void printLine() {
		System.out.println("____________________________________________________________");
	}

	static void queryBot(TaskList taskList) {
		try (Scanner input = new Scanner(System.in)) {
			while (input.hasNextLine()) {
				String query = input.nextLine();  // Read user input
				printLine();
				if (query.equals("bye")) {
					exit();
					printLine();
					break;
				} else if (query.equals("list")) {
					taskList.printList();
				} else if (query.startsWith("mark")) {
					mark(taskList, query);
				} else if (query.startsWith("unmark")) {
					unmark(taskList, query);
				} else if (query.startsWith("event")) {
					event(taskList, query);
				} else if (query.startsWith("deadline")) {
					deadline(taskList, query);
				} else if (query.startsWith("todo")) {
					todo(taskList, query);
				} else {
					System.out.println("I'm sorry, but I don't know what that means :-(");
				}
				printLine();
			}
		}
	}

	static void todo(TaskList taskList, String query) {
		try {
			String[] splitted = query.split(" ", 2);
			Task newTask = new ToDo(splitted[1]);
			taskList.addToList(newTask);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Please enter a valid todo!");
		}
	}

	static void deadline(TaskList taskList, String query) {
		try {
			String[] splitted = query.split(" ", 2);
			String[] parts = splitted[1].split("\\s*/by\\s+");
			String taskName = parts[0];
			String deadline = parts[1];
			Task newTask = new Deadline(taskName, deadline);
			taskList.addToList(newTask);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Please enter a valid deadline!");
		}
	}

	static void event(TaskList taskList, String query) {
		try {
			String[] splitted = query.split(" ", 2); // Split into 2 parts: tasktype and the rest
			String[] parts = splitted[1].split("\\s*/from\\s+|\\s*/to\\s+");
			String taskName = parts[0];
			String from = parts[1];
			String to = parts[2];
			Task newTask = new Event(taskName, from, to);
			taskList.addToList(newTask);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Please enter a valid event!");
		}
	}

	static void unmark(TaskList taskList, String query) {
		String[] splitted = query.split(" ", 2);
		int index = Integer.parseInt(splitted[1]) - 1;
		if (index >= taskList.length()) {
			System.out.println("No such task exists!");
		} else {
			taskList.get(index).unmark();
			System.out.println("OK, I've marked this task as not done yet:");
			System.out.println(taskList.get(index).toString());
		}
	}

	static void mark(TaskList taskList, String query) {
		String[] splitted = query.split(" ", 2);
		int index = Integer.parseInt(splitted[1]) - 1;
		if (index >= taskList.length()) {
			System.out.println("No such task exists!");
		} else {
			taskList.get(index).mark();
			System.out.println("Nice! I've marked this task as done:");
			System.out.println(taskList.get(index).toString());
		}
	}

	static void exit() {
		System.out.println("Bye. Hope to see you again soon!");
	}
}
