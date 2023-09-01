package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.list.FunnyList;

import java.util.Scanner;

public class Ui {

	private Scanner sc;
	public Ui() {
		this.sc = new Scanner(System.in);
	}

	public void showWelcome() {
		printLine();
        System.out.println("\tHello! I'm FUNNY.\n\tWhat can I do for you?");
        printLine();
	}

	public String readCommand() {
		return this.sc.nextLine().trim();
	}
	public void showLoadingError() {
		printLine();
		System.out.println("\tNo previous tasklist detected. We have initialised a new tasklist");
		printLine();
	}

	public void showAddTodoMessage(Task task, FunnyList taskList) {
		System.out.println("\tGot it. I've added this task:");
		System.out.println("\t\t" + task);
		System.out.println("\tNow you have " + String.valueOf(taskList.size()) + " tasks in the list");
	}

	public void showInvalidInput() {
		System.out.println(new DukeException("I'm sorry, but I don't know what that means :-("));
	}

	public void showError(DukeException e) {
		System.out.println(e);
	}

	public void showInvalidTimeMessage() {
		System.out.println(new DukeException("Please represent time in a proper time format of yyyy-mm-dd"));
	}

	public void showMarkMessage(Task task) {
		System.out.println("\tNice! I've marked this task as done:\n\t\t" + task);
	}

	public void showUnmarkMessage(Task task) {
		System.out.println("\tOk, I've marked this task as not done yet:\n\t\t" + task);
	}

	public void showItems(FunnyList taskList) {
		for (int i = 0; i < taskList.size(); i++) {
			System.out.println("\t" + String.valueOf(i + 1) + ". " + taskList.get(i));
		}
	}

	public void showDeleteMessage(Task task, FunnyList taskList) {
		System.out.println("\tNoted. I've removed this duke.task:");
		System.out.println("\t\t" + task);
		System.out.println("\tNow you have " + String.valueOf(taskList.size()) + " tasks in the list");
	}

	public void showGoodbyeMessage() {
		System.out.println("\tBye. Hope to see you again soon!");
	}
	public void printLine() {
		System.out.print("\t");
		for (int i = 0; i < 80; i++) {
			System.out.print("─");
		}
		System.out.println();
	}
}
