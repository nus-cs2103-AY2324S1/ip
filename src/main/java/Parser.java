import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class Parser {

	/**
	 * The storage to read and write to the file
	 */
	private final Storage storage;
	/**
	 * The tasklist to store the tasks
	 */
	private final TaskList taskList;
	/**
	 * The ui to print the messages
	 */
	private final Ui ui;

	/**
	 * Constructor for Parser
	 *
	 * @param taskList
	 * @param storage
	 * @param ui
	 */
	public Parser(TaskList taskList, Storage storage, Ui ui) {
		this.storage = storage;
		this.taskList = taskList;
		this.ui = ui;
	}

	/**
	 * The main function where the program starts
	 */
	void queryBot() {
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
			try {
				String query = input.nextLine();  // Read user input
				ui.printLine();
				if (query.equals("bye")) {
					ui.exit();
					break;
				} else if (query.equals("list")) {
					list(taskList);
				} else if (query.startsWith("delete")) {
					delete(query, taskList, storage);
				} else if (query.startsWith("mark")) {
					mark(query, taskList, storage);
				} else if (query.startsWith("unmark")) {
					unmark(query, taskList, storage);
				} else if (query.startsWith("event")) {
					event(query, taskList, storage);
				} else if (query.startsWith("deadline")) {
					deadline(query, taskList, storage);
				} else if (query.startsWith("todo")) {
					todo(query, taskList, storage);
				} else {
					throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
				}
				ui.printLine();
			} catch (DukeException e) {
				System.out.println(e.getMessage());
				ui.printLine();
			}
		}
		ui.printLine();
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
	 * Prints the list of tasks
	 *
	 * @param taskList the list of tasks
	 * @throws DukeException if the list is empty
	 */
	void list(TaskList taskList) throws DukeException {
		if (taskList.length() <= 0) {
			throw new DukeException("☹ OOPS!!! I'm sorry, but you don't have any tasks yet!");
		}
		taskList.printList();
	}

	/**
	 * Deletes the task from the list at the specified index
	 *
	 * @param query    the query from the user
	 * @param taskList the list of tasks
	 * @param storage  the storage to read and write to the file
	 * @throws DukeException if the query is not in the correct format
	 */
	void delete(String query, TaskList taskList, Storage storage) throws DukeException {
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
		if (index >= taskList.length() || index < 0) {
			throw new DukeException("No such task exists! Please enter a valid number within following list!\n" +
							"Please use the command list to see the list of tasks\n" +
							"and then delete the following task that you would like");
		} else {
			taskList.delete(index);
			storage.writeToFile(taskList);
		}
	}

	/**
	 * Marks the task from the list at the specified index
	 *
	 * @param query    the query from the user
	 * @param taskList the list of tasks
	 * @param storage  the storage to read and write to the file
	 * @throws DukeException if the query is not in the correct format
	 */
	void mark(String query, TaskList taskList, Storage storage) throws DukeException {
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
		if (index >= taskList.length() || index < 0) {
			throw new DukeException("No such task exists! Please enter a valid number within following list!\n" +
							"Please use the command list to see the list of tasks\n" +
							"and then mark the following task that you would like");
		} else {
			Task currTask = taskList.get(index);
			currTask.mark();
			storage.writeToFile(taskList);
		}
	}

	/**
	 * Unmarks the task from the list at the specified index
	 *
	 * @param query    the query from the user
	 * @param taskList the list of tasks
	 * @param storage  the storage to read and write to the file
	 * @throws DukeException if the query is not in the correct format
	 */
	void unmark(String query, TaskList taskList, Storage storage) throws DukeException {
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
		if (index >= taskList.length()) {
			throw new DukeException("No such task exists! Please enter a valid number within following list!\n" +
							"Please use the command list to see the list of tasks\n" +
							"and then unmark the following task that you would like");
		} else {
			Task currTask = taskList.get(index);
			currTask.unmark();
			storage.writeToFile(taskList);
		}
	}

	/**
	 * todo should be in the following format - todo read book
	 *
	 * @param query    the query from the user
	 * @param taskList the list of tasks
	 * @param storage  the storage to read and write to the file
	 * @throws DukeException if the query is not in the correct format
	 */
	void todo(String query, TaskList taskList, Storage storage) throws DukeException {
		if (query.split(" ").length == 1) {
			throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n" +
							"Please enter a valid todo - todo read book or todo sleep");
		}
		String[] splitted = query.split(" ", 2);
		Task newTask = new ToDo(splitted[1]);
		taskList.addToList(newTask, storage);
	}

	/**
	 * Deadline should be in the following format - deadline return book /by dd/MM/yy HHmm
	 *
	 * @param query    the query from the user
	 * @param taskList the list of tasks
	 * @param storage  the storage to read and write to the file
	 * @throws DukeException if the query is not in the correct format
	 */
	void deadline(String query, TaskList taskList, Storage storage) throws DukeException {
		if (query.split(" ").length == 1) {
			throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n" +
							"Please enter a valid deadline - deadline return book /by dd/MM/yy HHmm");
		}
		if (!query.contains("/by")) {
			throw new DukeException("☹ OOPS!!! Please enter a valid deadline - deadline return book /by 2pm");
		}
		if (query.split("\\s+/by\\s+").length == 1) {
			throw new DukeException("☹ OOPS!!! You added a /by but did not include a deadline!.\n" +
							"Please enter a valid deadline - deadline return book /by dd/MM/yy HHmm");
		}
		String[] splitted = query.split(" ", 2);
		String[] parts = splitted[1].split("\\s*/by\\s+");
		String taskName = parts[0];
		if (taskName.equals("")) {
			throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n" +
							"Please enter a valid deadline - deadline return book /by dd/MM/yy HHmm");
		}
		String deadline = parts[1];
		try {
			LocalDateTime deadlineDate = formatInputDate(deadline);
			Task newTask = new Deadline(taskName, deadlineDate);
			taskList.addToList(newTask, storage);
		} catch (DateTimeParseException e) {
			throw new DukeException("☹ OOPS!!! You entered an invalid date format!.\n" +
							"Please enter a valid date format in the following format - dd/MM/yy HHmm");
		}
	}

	/**
	 * Event should be in the following format - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm
	 *
	 * @param query    the query from the user
	 * @param taskList the list of tasks
	 * @param storage  the storage to read and write to the file
	 * @throws DukeException if the query is not in the correct format
	 */
	void event(String query, TaskList taskList, Storage storage) throws DukeException {
		if (query.split(" ").length == 1) {
			throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.\n" +
							"Please enter a valid event - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
		}
		if (!query.contains("/from") || !query.contains("/to")) {
			throw new DukeException("☹ OOPS!!! Your query is missing the prefixes /from or /to\n" +
							"Please enter a valid event - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
		}
		int fromIndex = query.indexOf("/from");
		int toIndex = query.indexOf("/to");
		if (fromIndex > toIndex) {
			throw new DukeException("☹ OOPS!!! The /from prefix should come before the /to prefix.\n" +
							"Please enter a valid event - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
		}
		if (query.split("\\s+/from\\s+").length == 1 || query.split("\\s+/to\\s+").length == 1) {
			throw new DukeException("☹ OOPS!!! You added a /from or /to but did not include a time!.\n" +
							"Please enter a valid event - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
		}
		String[] splitted = query.split(" ", 2); // Split into 2 parts: tasktype and the rest
		String[] parts = splitted[1].split("\\s*/from\\s+|\\s*/to\\s+");
		String taskName = parts[0];
		if (Objects.equals(taskName, "")) {
			throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.\n" +
							"Please enter a valid event - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
		}
		String from = parts[1];
		if (from.length() == 0) {
			throw new DukeException("☹ OOPS!!! You added a /from but did not include a time!.\n" +
							"Please enter a valid event - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
		}
		String to = parts[2];
		if (to.length() == 0) {
			throw new DukeException("☹ OOPS!!! You added a /to but did not include a time!.\n" +
							"Please enter a valid event - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
		}
		try {
			LocalDateTime fromDate = formatInputDate(from);
			LocalDateTime toDate = formatInputDate(to);
			if (fromDate.isBefore(toDate)) {
				Task newTask = new Event(taskName, fromDate, toDate);
				taskList.addToList(newTask, storage);
			} else {
				throw new DukeException("☹ OOPS!!! The start date cannot be after the end date!");
			}
		} catch (DateTimeParseException e) {
			throw new DukeException("☹ OOPS!!! You entered an invalid date format!.\n" +
							"Please enter a valid date format in the following format - dd/MM/yy HHmm");
		}
	}

	/**
	 * Formats the input date to the following format - dd/MM/yy HHmm
	 *
	 * @param date the input date
	 * @return LocalDateTime
	 * @throws DateTimeParseException if the date is not in the correct format
	 */
	LocalDateTime formatInputDate(String date) throws DateTimeParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
		return LocalDateTime.parse(date, formatter);
	}
}
