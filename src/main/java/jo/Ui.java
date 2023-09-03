package jo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import jo.task.Task;

/**
 * Responsible for handling user interactions and displaying messages in the `Jo` application.
 * It provides methods for reading user input, showing welcome messages and displaying errors.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new `Ui` object with a `Scanner` for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command entered by the user.
     * @return The user's input command as a string.
     */
    public String readCommand() {
        System.out.print("Enter a command: ");
        return this.scanner.nextLine();
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println("> Hello! I'm Jo.\n> What can I do for you?");
    }

    /**
     * Displays a farewell message when the application is about to exit.
     */
    public void sayBye() {
        System.out.println("> Bye. Hope to see you again soon!");
        this.scanner.close();
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("> OOPS!!! " + message);
    }

    /**
     * Displays a message indicating the result of marking a task as done or undone.
     * @param task   The task that was marked.
     * @param isDone `true` if the task was marked as done, `false` if marked as undone.
     */
    public void markResult(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("> Nice! I've marked this task as done:");
            System.out.println("\t" + task);
        } else {
            System.out.println("> OK, I've marked this task as not done yet:");
            System.out.println("\t" + task);
        }
    }

    /**
     * Displays a message indicating the result of adding or removing a task from the list.
     * @param task      The task that was added or removed.
     * @param taskList  The updated task list.
     * @param isAdd     `true` if a task was added, `false` if a task was removed.
     */
    public void modifyListResult(Task task, TaskList taskList, boolean isAdd) {
        if (isAdd) {
            System.out.println("> Got it. I've added this task:");
        } else {
            System.out.println("> Noted. I've removed this task:");
        }
        System.out.println("\t" + task.toString());
        System.out.printf("> Now you have %d tasks in the list.%n", taskList.getSize());
    }

    /**
     * Displays a list of tasks that match a specified deadline date.
     * @param deadline     The deadline date for which tasks are displayed.
     * @param resultList   The list of tasks that match the deadline.
     */
    public void searchResult(LocalDate deadline, ArrayList<Task> resultList) {
        System.out.println("> Here are the tasks that are due on "
                + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ": ");
        if (!resultList.isEmpty()) {
            for (Task t : resultList) {
                System.out.println("\t" + t);
            }
        } else {
            System.out.println("\tYay, you have no task due on this day!");
        }
    }

    /**
     * Displays the list of tasks in the task list.
     * @param taskList The task list to display.
     */
    public void printList(TaskList taskList) {
        System.out.println("> Here are the tasks in your list:");

        for (int i = 0; i < taskList.getSize(); i++) {
            Task t = taskList.getTask(i);
            System.out.println("\t" + (i + 1) + ". " + t.toString());
        }
    }
}
