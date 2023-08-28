import java.util.Objects;
import java.util.Scanner;

public class Duke {
	/**
	 * Storage object to handle file operations
	 */
	private final Storage STORAGE = new Storage("data/duke.txt");
	private final TaskList TASKLIST = new TaskList();

	/**
	 * The main function where the program starts
	 *
	 * @param args input args
	 */
	public static void main(String[] args) {
		Duke duke = new Duke();
		duke.printLine();
		System.out.println("Hello I'm RyamBot");
		System.out.println("What can I do for you?");
		duke.STORAGE.writeToFile("Hello I'm RyamBot");
		duke.printLine();
		duke.queryBot();
		duke.printLine();
	}

	/**
	 * Prints a line
	 */
	void printLine() {
		System.out.println("____________________________________________________________");
	}

	void queryBot() {
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
			try {
				String query = input.nextLine();  // Read user input
				printLine();
				if (query.equals("bye")) {
					exit();
					break;
				} else if (query.equals("list")) {
					list();
				} else if (query.startsWith("delete")) {
					delete(query);
				} else if (query.startsWith("mark")) {
					mark(query);
				} else if (query.startsWith("unmark")) {
					unmark(query);
				} else if (query.startsWith("event")) {
					event(query);
				} else if (query.startsWith("deadline")) {
					deadline(query);
				} else if (query.startsWith("todo")) {
					todo(query);
				} else {
					throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
				}
				printLine();
			} catch (DukeException e) {
				System.out.println(e.getMessage());
				printLine();
			}
		}
	}

	/**
	 * Checks if the argument is numeric or not. Leading and trailing whitespace characters in str are ignored. Empty
	 * or null str return false.
	 *
	 * @param str - the string to be checked
	 * @return true if the argument is numeric and can be parsed as a number. Returns false otherwise.
	 */
	boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * @throws DukeException
	 */
	void list() throws DukeException {
		if (TASKLIST.length() <= 0) {
			throw new DukeException("☹ OOPS!!! I'm sorry, but you don't have any tasks yet!");
		}
		TASKLIST.printList();
	}

	/**
	 * @param query
	 * @throws DukeException
	 */
	void delete(String query) throws DukeException {
		if (query.split(" ").length == 1) {
			throw new DukeException("☹ OOPS!!! You are missing a number\n" +
							"Please enter a valid delete query - delete 1");
		}
		if (query.split(" ").length > 2) {
			throw new DukeException("☹ OOPS!!! You have too many numbers\n" +
							"Please enter a valid delete query - delete 1");
		}
		String[] splitted = query.split(" ", 2);
		if (!isNumeric(splitted[1])) {
			throw new DukeException("☹ OOPS!!! You entered a non-numeric item!\n" +
							"Please enter a valid delete query - delete 1");
		}
		int index = Integer.parseInt(splitted[1]) - 1;
		if (index >= TASKLIST.length() || index < 0) {
			throw new DukeException("No such task exists! Please enter a valid number within following list!\n" +
							"Please use the command list to see the list of tasks\n" +
							"and then delete the following task that you would like");
		} else {
			TASKLIST.delete(index);
		}
	}

	/**
	 * @param query
	 * @throws DukeException
	 */
	void mark(String query) throws DukeException {
		if (query.split(" ").length == 1) {
			throw new DukeException("☹ OOPS!!! You are missing a number\n" +
							"Please enter a valid mark query - mark 1");
		}
		if (query.split(" ").length > 2) {
			throw new DukeException("☹ OOPS!!! You have too many numbers\n" +
							"Please enter a valid mark query - mark 1");
		}
		String[] splitted = query.split(" ", 2);
		if (!isNumeric(splitted[1])) {
			throw new DukeException("☹ OOPS!!! You entered a non-numeric item!\n" +
							"Please enter a valid mark query - mark 1");
		}
		int index = Integer.parseInt(splitted[1]) - 1;
		if (index >= TASKLIST.length() || index < 0) {
			throw new DukeException("No such task exists! Please enter a valid number within following list!\n" +
							"Please use the command list to see the list of tasks\n" +
							"and then mark the following task that you would like");
		} else {
			Task currTask = TASKLIST.get(index);
			currTask.mark();
			System.out.println(currTask);
		}
	}

	/**
	 * @param query
	 * @throws DukeException
	 */
	void unmark(String query) throws DukeException {
		if (query.split(" ").length == 1) {
			throw new DukeException("☹ OOPS!!! You are missing a number\n" +
							"Please enter a valid unmark query - unmark 1");
		}
		if (query.split(" ").length > 2) {
			throw new DukeException("☹ OOPS!!! You have too many numbers\n" +
							"Please enter a valid unmark query - unmark 1");
		}
		String[] splitted = query.split(" ", 2);
		if (!isNumeric(splitted[1])) {
			throw new DukeException("☹ OOPS!!! You entered a non-numeric item!\n" +
							"Please enter a valid mark query - mark 1");
		}
		int index = Integer.parseInt(splitted[1]) - 1;
		if (index >= TASKLIST.length()) {
			throw new DukeException("No such task exists! Please enter a valid number within following list!\n" +
							"Please use the command list to see the list of tasks\n" +
							"and then unmark the following task that you would like");
		} else {
			Task currTask = TASKLIST.get(index);
			currTask.unmark();
			System.out.println(currTask);
		}
	}

	/**
	 * @param query
	 * @throws DukeException
	 */
	void todo(String query) throws DukeException {
		if (query.split(" ").length == 1) {
			throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n" +
							"Please enter a valid todo - todo read book or todo sleep");
		}
		String[] splitted = query.split(" ", 2);
		Task newTask = new ToDo(splitted[1]);
		TASKLIST.addToList(newTask);
	}

	/**
	 * @param query
	 * @throws DukeException
	 */
	void deadline(String query) throws DukeException {
		if (query.split(" ").length == 1) {
			throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n" +
							"Please enter a valid deadline - deadline return book /by 2pm");
		}
		if (!query.contains("/by")) {
			throw new DukeException("☹ OOPS!!! Please enter a valid deadline - deadline return book /by 2pm");
		}
		if (query.split("\\s+/by\\s+").length == 1) {
			throw new DukeException("☹ OOPS!!! You added a /by but did not include a deadline!.\n" +
							"Please enter a valid deadline - deadline return book /by 2pm");
		}
		String[] splitted = query.split(" ", 2);
		String[] parts = splitted[1].split("\\s*/by\\s+");
		String taskName = parts[0];
		if (taskName.equals("")) {
			throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n" +
							"Please enter a valid deadline - deadline return book /by 2pm");
		}
		String deadline = parts[1];
		Task newTask = new Deadline(taskName, deadline);
		TASKLIST.addToList(newTask);
	}

	/**
	 * @param query
	 * @throws DukeException
	 */
	void event(String query) throws DukeException {
		if (query.split(" ").length == 1) {
			throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.\n" +
							"Please enter a valid event - event read book /from 2pm /to 4pm");
		}
		if (!query.contains("/from") || !query.contains("/to")) {
			throw new DukeException("☹ OOPS!!! Your query is missing the prefixes /from or /to\n" +
							"Please enter a valid event - event read book /from 2pm /to 4pm");
		}
		int fromIndex = query.indexOf("/from");
		int toIndex = query.indexOf("/to");
		if (fromIndex > toIndex) {
			throw new DukeException("☹ OOPS!!! The /from prefix should come before the /to prefix.\n" +
							"Please enter a valid event - event read book /from 2pm /to 4pm");
		}
		if (query.split("\\s+/from\\s+").length == 1 || query.split("\\s+/to\\s+").length == 1) {
			throw new DukeException("☹ OOPS!!! You added a /from or /to but did not include a time!.\n" +
							"Please enter a valid event - event read book /from 2pm /to 4pm");
		}
		String[] splitted = query.split(" ", 2); // Split into 2 parts: tasktype and the rest
		String[] parts = splitted[1].split("\\s*/from\\s+|\\s*/to\\s+");
		String taskName = parts[0];
		if (Objects.equals(taskName, "")) {
			throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.\n" +
							"Please enter a valid event - event read book /from 2pm /to 4pm");
		}
		String from = parts[1];
		if (from.length() == 0) {
			throw new DukeException("☹ OOPS!!! You added a /from but did not include a time!.\n" +
							"Please enter a valid event - event read book /from 2pm /to 4pm");
		}
		String to = parts[2];
		if (to.length() == 0) {
			throw new DukeException("☹ OOPS!!! You added a /to but did not include a time!.\n" +
							"Please enter a valid event - event read book /from 2pm /to 4pm");
		}
		Task newTask = new Event(taskName, from, to);
		TASKLIST.addToList(newTask);
	}

	/**
	 * Prints exit message
	 */
	void exit() {
		System.out.println("Bye. Hope to see you again soon!");
	}

}
