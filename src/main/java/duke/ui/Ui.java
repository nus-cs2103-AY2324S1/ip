package duke.ui;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
	private Scanner scanner;

	public Ui() {
		this.scanner = new Scanner(System.in);
	}

	/**
	 * Prints a line
	 */
	public void printLine() {
		System.out.println("____________________________________________________________");
	}

	public String readCommand() {
		return this.scanner.nextLine();
	}

	/**
	 * Prints a line
	 */
	public void showWelcome() {
		printLine();
		System.out.println("Hello I'm RyamBot");
		System.out.println("What can I do for you?");
		printLine();
	}

	/**
	 * The exit command when user types "bye"
	 **/
	public void exit() {
		printLine();
		System.out.println("I shall now take my leave. Farewell!");
		printLine();
	}

	public void showAdd(int size, Task task) {
		printLine();
		System.out.println("Got it. I've added this task:");
		System.out.println(task.toString());
		System.out.println("Now you have " + size + " tasks in the list.");
		printLine();
	}

	public void showList(int size) {
		printLine();
		System.out.println("Here are the tasks in your list:");
		System.out.println(size + " tasks in total.");
		printLine();
	}

	public void showMark(int index, Task task) {
		printLine();
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(task.toString());
		printLine();
	}

	public void showUnmark(int index, Task task) {
		printLine();
		System.out.println("Nice! I've unmarked this task as done:");
		System.out.println(task.toString());
		printLine();
	}

	public void showDelete(int index, Task task) {
		printLine();
		System.out.println("Noted. I've removed this task:");
		System.out.println(task.toString());
		System.out.println("Now you have " + index + " tasks in the list.");
		printLine();
	}

	public void help() {
		printLine();
		System.out.println("Here are the list of commands you can use:");
		System.out.println("list - Lists all the tasks");
		System.out.println("todo <task> - Adds a todo task");
		System.out.println("deadline <task> /by <date in dd/MM/yy HHmm> - Adds a deadline task");
		System.out.println("event <task> /from <date in dd/MM/yy HHmm> /to <date> - Adds an event task");
		System.out.println("delete <index> - Deletes the task at the index");
		System.out.println("mark <index> - Marks the task at the index as done");
		System.out.println("unmark <index> - Unmarks the task at the index as done");
		System.out.println("help - Shows the list of commands");
		System.out.println("bye - Exits the program");
		printLine();
	}
}
