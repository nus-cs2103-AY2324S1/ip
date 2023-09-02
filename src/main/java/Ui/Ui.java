package ui;
import java.util.Scanner;

import duke.DukeException;
import task.Task;
import taskList.TaskList;

/**
 * The `Ui` class is responsible for handling the user interface of the BloopBot application.
 * It provides methods to display messages, read user input, and show information about tasks and commands.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class Ui {

    /**
     * Displays a welcome message to the user when the application starts.
     */
    public void showWelcome() {
        System.out.println("Hello from BloopBot");
        System.out.println("My name is BloopBloop");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays a farewell message to the user when they exit the application.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param e The DukeException containing the error message.
     */
    public void showError(DukeException e) {
        System.out.println("Error: " + e.getMessage());
    }

    /**
     * Displays a horizontal line to separate different sections of output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a list of available commands to the user.
     */
    public void showCommands() {
        System.out.println("List of Commands: Add, Deadline, Event, Todo, Echo, Mark, Unmark, Delete, Bye");
        System.out.println("1. Add - Add a task to the list");
        System.out.println("2. Deadline - Add a task with a deadline");
        System.out.println("3. Event - Add an event task");
        System.out.println("4. Todo - Add a todo task");
        System.out.println("5. Echo - Echo a message");
        System.out.println("6. Mark - Mark a task as done");
        System.out.println("7. Unmark - Unmark a task as done");
        System.out.println("8. Delete - Delete a task");
        System.out.println("9. Bye - Exit the program");
    }

    /**
     * Reads a user command from the console input.
     *
     * @return The user's input as a String.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The TaskList containing the tasks to be displayed.
     */
    public void showTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        taskList.displayTasks();
    }

    /**
     * Displays a message to confirm the addition of a task.
     *
     * @param task       The added task.
     * @param totalTasks The total number of tasks in the list.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays a message to confirm the deletion of a task.
     *
     * @param task       The deleted task.
     * @param totalTasks The total number of tasks in the list.
     */
    public void showDeletedTask(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays an echoed message to the user.
     *
     * @param message The message to be echoed.
     */
    public void showEcho(String message) {
        System.out.println("Echo: " + message);
    }

    /**
     * Displays a message to indicate that a task has been unmarked as done.
     *
     * @param taskToUnmark The task that was unmarked.
     */
    public void showTaskUnmarked(Task taskToUnmark) {
        System.out.println("Task unmarked: " + taskToUnmark.getDescription());
    }

    /**
     * Displays a message to indicate that a task has been marked as done.
     *
     * @param taskToUnmark The task that was marked as done.
     */
    public void showTaskMarked(Task taskToUnmark) {
        System.out.println("Task marked: " + taskToUnmark.getDescription());
    }
}
