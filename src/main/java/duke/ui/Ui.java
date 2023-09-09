package duke.ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Handles all the textual information with user
 */
public class Ui {
	private String name;
	private final Scanner in;
	private PrintStream out;
	private static final String COMMENT_LINE_REGEX = "#.";

	private static final String LOGO = " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";

	private static final String DIVIDER = "_______________________________________________________\n";

	// scannner takes in inputstream

	/**
	 * Handles user input and gives an output
	 * @param in input of user
	 * @param out output to user
	 */
	public Ui(InputStream in, PrintStream out) {
		this.in = new Scanner(in);
		this.out = out;
	}

	/**
	 * name of interface
	 * @param name represents the Ui
	 */

	public Ui(String name) {
		this(System.in, System.out);
		this.name = name;
	}

	/**
	 * Check if there are more inputs
	 * @return a boolean check
	 */
	public boolean hasNextLine() {
		return this.in.hasNextLine();
	}


	/**
	 * checks if input is a comment
	 * @param inputLine the input
	 * @return boolean, whether input is comment or not
	 */
	private boolean isCommentLine(String inputLine) {
		return inputLine.trim().matches(COMMENT_LINE_REGEX);
	}

	/**
	 * checks whether the input is empty or is a comment, in both cases, we ignore
	 * @param inputLine the input
	 * @return a boolean on whether we will ignore
	 */
	private boolean shouldIgnore(String inputLine) {
		return inputLine.trim().isEmpty() || isCommentLine(inputLine);
	}

	/**
	 * Output to user prompting them for command
	 * @return a String to be piped into GUI
	 */

	public String readCommand() {
		// has nextline then save it skip the comments
		out.print("Enter your Command:");
		String inputLine = in.nextLine();

		while (shouldIgnore(inputLine)) {
			inputLine = in.nextLine();
		}
		String message = String.format("Command entered: %s", inputLine);
		System.out.println(message);
		return inputLine;
	}

	/**
	 * Check if command is valid
	 * @param inputLine the input
	 * @return a message to the user whether command is valid or not
	 */
	public String parseCommand(String inputLine) {
		if (shouldIgnore(inputLine)) {
			return "Not a valid command";
		}
		String message = String.format("Command entered: %s", inputLine);
		return message;
	}

	/**
	 * outputs to user if unable to process file
	 * @param e error message exception
	 */
	public void showLoadingError(Exception e) {
		System.out.println("Unable to process stored file:");
		System.out.println(e.getMessage());
	}

	/**
	 * Ouputs to user a welcome message
	 */
	public void showWelcome() {
		String greeting = String.format("____________________________________________________________\n" +
				"Hello! I'm %s\n" +
				"What can I do for you?\n" +
				"____________________________________________________________", this.name);
		System.out.println(LOGO);
		System.out.println(greeting);
	}

	/**
	 * Ouputs to user a good bye message
	 * @return String representation of goodbye
	 */
	public String showGoodBye() {
		String bye = "____________________________________________________________\n" +
				"Bye. Hope to see you again soon!\n" +
				"____________________________________________________________";
		return bye;
	}

	/**
	 * Outputs to user a delete message
	 * @param task the task to be done
	 * @param remainingCount the remaining task after deletion
	 * @return an Output to the user
	 */

	public String showDelete(Task task, String remainingCount) {
		String echo = String.format("____________________________________________________________\n" +
				"Noted. I've removed this task:\n" +
				"%s\n" +
				"Now you have %s tasks in the list.\n" +
				"____________________________________________________________", task.toString(), remainingCount);
		return echo;
	}

	/**
	 * Outputs to user when we add a task
	 * @param task the task
	 * @param taskCount Number of tasks
	 * @return Output to user when we add the respective tasks
	 */

	public String showAddTask(Task task, String taskCount) {
		String echo = String.format("____________________________________________________________\n" +
				"Got it. I've added this task:\n" +
				"%s\n" +
				"Now you have %s tasks in the list\n" +
				"____________________________________________________________", task.toString(), taskCount);
		return echo;
	}

	/**
	 * Prints a divider
	 */

	public void showLine() {
		System.out.println(DIVIDER);
	}

	/**
	 * @return String representation of divider
	 */

	public String getLine() {
		return DIVIDER;
	}

	/**
	 * returns String representation of finding a task
	 * @param br the StringBuilder containing String
	 * @return String representation of finding a task
	 */
	public String showFindTask(StringBuilder br) {
		return br.toString();
	}

	/**
	 * returns String representation of list
	 * @param br StringBuilder
	 * @return String representation of list
	 */
	public String showList(StringBuilder br) {
		String echo = String.format("____________________________________________________________\n"
				+ "Here are the task in your list:\n"
				+ "%s"
				+ "____________________________________________________________", br.toString());
		return echo;
	}

	/**
	 * return String representation of marking a task
	 * @param task the marked task
	 * @return String representation of marking a task
	 */

	public String showMark(Task task) {
		String echo = String.format("____________________________________________________________\n" +
				"Nice! I've marked this task as done:\n" +
				task.toString() + "\n" +
				"____________________________________________________________");
		return echo;
	}

	/**
	 * return String representation of unmarking a task
	 * @param task the task which is unmarked
	 */
	public void showUnMark(Task task) {
		String echo = String.format("____________________________________________________________\n" +
				"Nice! I've marked this task as not done yet:\n" +
				task.toString() + "\n" +
				"____________________________________________________________");
		System.out.println(echo);
	}


}
