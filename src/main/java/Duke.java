import java.util.Scanner;

public class Duke {
//	static ArrayList<Task> list = new ArrayList<>();

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
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		String query = myObj.nextLine();  // Read user input
		if (query.equals("bye")) {
			exit();
		} else if (query.equals("list")) {
			taskList.printList();
			queryBot(taskList);
		} else if (query.startsWith("mark")) {
			String[] splitted = query.split(" ", 2);
			int index = Integer.parseInt(splitted[1]) - 1;
			printLine();
			if (index >= taskList.length()) {
				System.out.println("No such task exists!");
			} else {
				taskList.get(index).mark();
				System.out.println("Nice! I've marked this task as done: ");
				System.out.println("[" + taskList.get(index).getStatusIcon() + "] " + taskList.get(index).description);
			}
			printLine();
			queryBot(taskList);
		} else if (query.startsWith("unmark")) {
			String[] splitted = query.split(" ", 2);
			int index = Integer.parseInt(splitted[1]) - 1;
			printLine();
			if (index >= taskList.length()) {
				System.out.println("No such task exists!");
			} else {
				taskList.get(index).unmark();
				System.out.println("OK, I've marked this task as not done yet: ");
				System.out.println(taskList.get(index).toString());
			}
			printLine();
			queryBot(taskList);
		} else if (query.startsWith("event")) {
			try {
				String[] splitted = query.split(" ", 2); // Split into 2 parts: tasktype and the rest
				String[] parts = splitted[1].split("\\s*/from\\s+|\\s*/to\\s+");
				String taskName = parts[0];
				String from = parts[1];
				String to = parts[2];
				Task newTask = new Event(taskName, from, to);
				taskList.addToList(newTask);
				queryBot(taskList);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Please enter a valid event!");
				queryBot(taskList);
			}
		} else if (query.startsWith("deadline")) {
			try {
				String[] splitted = query.split(" ", 2);
				String[] parts = splitted[1].split("\\s*/by\\s+");
				String taskName = parts[0];
				String deadline = parts[1];
				Task newTask = new Deadline(taskName, deadline);
				taskList.addToList(newTask);
				queryBot(taskList);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Please enter a valid deadline!");
				queryBot(taskList);
			}
		} else if (query.startsWith("todo")) {
			try {
				String[] splitted = query.split(" ", 2);
				Task newTask = new ToDo(splitted[1]);
				taskList.addToList(newTask);
				queryBot(taskList);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Please enter a valid todo!");
				queryBot(taskList);
			}
		} else {
			queryBot(taskList);
		}
	}

	static void exit() {
		printLine();
		System.out.println("Bye. Hope to see you again soon!");
		printLine();
	}
}
