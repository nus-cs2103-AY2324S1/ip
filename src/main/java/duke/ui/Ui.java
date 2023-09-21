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
	private final PrintStream out;
	private static final String COMMENT_LINE_REGEX = "#.";

	private static final String LOGO =
			"          .--.\n" +
					"         |o_o |\n" +
					"         |:_/ |\n" +
					"        //   \\ \\\n" +
					"       (|     | )\n" +
					"      /'\\_   _/`\\\n" +
					"      \\___)=(___/";

	/**
	 * Handles user input and gives an output
	 *
	 * @param in input of user
	 * @param out output to user
	 */
	public Ui(InputStream in, PrintStream out) {
		this.in = new Scanner(in);
		this.out = out;
	}

	public Ui(String name) {
		this(System.in, System.out);
		this.name = name;
	}

	/**
	 * Check if there are more inputs
	 *
	 * @return a boolean check
	 */
	public boolean hasNextLine() {
		return this.in.hasNextLine();
	}


	/**
	 * Checks if input is a comment.
	 * @param inputLine the input.
	 * @return boolean, whether input is comment or not.
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
	 * Output to user prompting them for command.
	 *
	 * @return a String to be piped into GUI.
	 */

	public String readCommand() {
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
	 * Outputs to user if unable to process file
	 *
	 * @param e error message exception
	 */
	public void showLoadingError(Exception e) {
		System.out.println("Unable to process stored file:");
		System.out.println(e.getMessage());
	}

	public void showWelcome() {
		String greeting = String.format("____________________________________________________________\n" + "Hello! I'm %s\n" +
				"What can I do for you?\n" +
				"____________________________________________________________", this.name);
		System.out.println(LOGO);
		System.out.println(greeting);
	}

	public String greet() {
		String greeting = String.format(
				LOGO + "\n" +
						"Hello! I'm %s\n" +
						"What can I do for you?\n" +
						"Type help into the console to find out what you can do!", this.name);
		return greeting;
	}



	/**
	 * Outputs to user a goodBye message.
	 * @return String representation of goodbye.
	 */
	public String showGoodBye() {
		String bye = "____________________________________________________________\n" +
				"Bye. Hope to see you again soon!\n" +
				"____________________________________________________________";
		return bye;
	}

	/**
	 * Outputs to user a delete message.
	 *
	 * @param task the task to be done.
	 * @param remainingCount the remaining task after deletion.
	 * @return an Output to the user.
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
	 * Outputs to user when we add a task.
	 *
	 * @param task the task.
	 * @param taskCount Number of tasks.
	 * @return Output to user when we add the respective tasks.
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
	 * Returns String representation of finding a task
	 *
	 * @param br the StringBuilder containing String
	 * @return String representation of finding a task
	 */
	public String showFindTask(StringBuilder br) {
		return br.toString();
	}

	public String showList(StringBuilder br) {
		String echo = String.format(
				"Here are the task in your list:\n"
						+ "%s"
				, br.toString());
		return echo;
	}

	/**
	 * Return String representation of marking a task.
	 *
	 * @param task the marked task.
	 * @return String representation of marking a task.
	 */

	public String showMark(Task task) {
		String echo = String.format(
				"Nice! I've marked this task as done:\n" +
						task.toString() + "\n"
		);
		return echo;
	}

	/**
	 * Return String representation of unMarking a task.
	 *
	 * @param task the task which is unmarked.
	 */
	public String showUnMark(Task task) {
		String echo = String.format(
				"Nice! I've marked this task as not done yet:\n" +
						task.toString() + "\n"
		);
		return echo;
	}

	/**
	 * Shows help guide for user.
	 *
	 * @return a string representing help guide.
	 */
	public String showHelp() {
		return "These are the commands that are permissible\n" +
				"list -list all things\n" +
				"find keyWord\n" +
				"bye -exit system\n" +
				"mark/unmark idx i.e mark 1, marks a given task\n" +
				"delete idx i.e delete 1\n" +
				"todo description i.e todo math assignment, adds a todo task\n" +
				"deadline aa /by 630am 29june\n" +
				"deadline aa /by 06:30:00 2015-04-24\n" +
				"event aa /from 6am 26june /to 9am 29june\n" +
				"archive idx\n" +
				"unarhive idk\n" +
				"archivelist\n" +
				"archivedelete idx\n";
	}

	public String showClear() {
		return "Now all tasks in your list have been cleared!";
	}

	/**
	 * Display unArchive.
	 *
	 * @param task to be unArchived.
	 * @return String representation of unArchiving a task.
	 */

	public String showUnArchive(Task task) {
		return "Retrieved file from folder (unarchive) " + task.toString();
	}

	/**
	 * Display Archive
	 *
	 * @param task to be archived
	 * @return String representation of unArchiving a task
	 */
	public String showArchive(Task task) {
		return "Stored task into archive folder (archive) " + task.toString();
	}
}
