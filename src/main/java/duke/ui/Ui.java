package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.list.FunnyList;

import java.util.Scanner;

/**
 * The Ui class is responsible for handling the standard input and output of the Duke Program.
 * It handles the printing of prompts and responses and takes in input commands from standard input
 */
public class Ui {

	private Scanner sc;

	/**
	 * Constructs a new instance of the Ui class
	 * Initialises an internal Scanner object to read input from standard input
	 */
	public Ui() {
		this.sc = new Scanner(System.in);
	}

	/**
	 *  Displays a welcome message from the FUNNY application.
	 *  Introduces the application and invites users to interact with it
	 */
	public void showWelcome() {
		printLine();
        System.out.println("\tHello! I'm FUNNY.\n\tWhat can I do for you?");
        printLine();
	}

	/**
	 *  Read a command input from the user.
	 *
	 * @return A string containing the user's input command.
	 */
	public String readCommand() {
		return this.sc.nextLine().trim();
	}

	/**
	 * Displays an error message indicating a loading issue with the tasklist.
	 * Indicates that a brand new tasklist is initialised.
	 */
	public void showLoadingError() {
		printLine();
		System.out.println("\tNo previous tasklist detected. We have initialised a new tasklist");
		printLine();
	}

	/**
	 * Displays a message confirming the addition of a new Todo task.
	 * This method prints a formatted message to the console after successfully
	 * adding a new Todo task to the tasklist. It includes information about
	 * the added task and the updated total number of tasks in the list.
	 *
	 * @param task The Todo task that was added.
	 * @param taskList The FunnyList containing the tasks.
	 */
	public void showAddTodoMessage(Task task, FunnyList taskList) {
		System.out.println("\tGot it. I've added this task:");
		System.out.println("\t\t" + task);
		System.out.println("\tNow you have " + String.valueOf(taskList.size()) + " tasks in the list");
	}

	/**
	 * Displays an error message indicating that a wrong command was entered.
	 * Indicates that a brand new tasklist is initialised.
	 */
	public void showInvalidInput() {
		System.out.println(new DukeException("I'm sorry, but I don't know what that means :-("));
	}

	/**
	 * Displays an error message based on the provided DukeException.
	 *
	 * @param e The DukeException containing the error details.
	 */
	public void showError(DukeException e) {
		System.out.println(e);
	}

	/**
	 * Displays a message confirming the task to be marked as completed.
	 * It includes information about the marked task.
	 *
	 * @param task The task to be marked as completed.
	 */
	public void showMarkMessage(Task task) {
		System.out.println("\tNice! I've marked this task as done:\n\t\t" + task);
	}

	/**
	 * Displays a message confirming the task to be unmarked as incomplete.
	 * It includes information about the unmarked task.
	 *
	 * @param task The task to be marked as incomplete.
	 */
	public void showUnmarkMessage(Task task) {
		System.out.println("\tOk, I've marked this task as not done yet:\n\t\t" + task);
	}

	/**
	 * Displays the items in a FunnyList.
	 * This method iterates through the provided FunnyList and displays each item
	 * formatted as a numbered list.
	 *
	 * @param taskList The FunnyList containing the tasks to be displayed.
	 */
	public void showItems(FunnyList taskList) {
		for (int i = 0; i < taskList.size(); i++) {
			System.out.println("\t" + String.valueOf(i + 1) + ". " + taskList.get(i));
		}
	}

	/**
	 * Displays a message confirming the task to be unmarked as incomplete.
	 * It includes information about the unmarked task.
	 *
	 * @param task The task to be marked as incomplete.
	 */
	public void showDeleteMessage(Task task, FunnyList taskList) {
		System.out.println("\tNoted. I've removed this duke.task:");
		System.out.println("\t\t" + task);
		System.out.println("\tNow you have " + String.valueOf(taskList.size()) + " tasks in the list");
	}

	/**
	 * Displays a goodbye message confirming the termination of the program.
	 */
	public void showGoodbyeMessage() {
		System.out.println("\tBye. Hope to see you again soon!");
	}

	/**
	 * Prints a decorative line to the console.
	 */
	public void printLine() {
		System.out.print("\t");
		for (int i = 0; i < 80; i++) {
			System.out.print("─");
		}
		System.out.println();
	}
}
