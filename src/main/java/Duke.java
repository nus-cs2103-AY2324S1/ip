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
			String[] queryArr = query.split(" ");
			int index = Integer.parseInt(queryArr[1]) - 1;
			printLine();
			System.out.println("Nice! I've marked this task as done: ");
			taskList.get(index).mark();
			System.out.println("[" + taskList.get(index).getStatusIcon() + "] " + taskList.get(index).description);
			printLine();
			queryBot(taskList);
		} else if (query.startsWith("unmark")) {
			String[] queryArr = query.split(" ");
			int index = Integer.parseInt(queryArr[1]) - 1;
			printLine();
			System.out.println("OK, I've marked this task as not done yet: ");
			taskList.get(index).unmark();
			System.out.println("[" + taskList.get(index).getStatusIcon() + "] " + taskList.get(index).description);
			printLine();
			queryBot(taskList);
		} else {
			Task newTask = new Task(query);
			taskList.addToList(newTask);
			queryBot(taskList);
		}
	}

	static void exit() {
		printLine();
		System.out.println("Bye. Hope to see you again soon!");
		printLine();
	}
}
