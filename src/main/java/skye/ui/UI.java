package skye.ui;

import skye.data.task.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the command line user interface which consists of a scanner to read in
 * user input and methods to display different messages to be shown to the user.
 */
public class UI {
    private static final int LINE_LENGTH = 100;
    private final Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a message to be shown to the user with horizontal lines
     * before and after the message.
     *
     * @param message Message shown to the user
     */
    public void printMessage(String message) {
        renderLine();
        System.out.println(message);
        renderLine();
    }

    /**
     * Reads the command from the user's input.
     *
     * @return A command from the user's input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Renders a horizontal line on the terminal.
     */
    public void renderLine() {
        System.out.println("_".repeat(LINE_LENGTH));
    }

    /**
     * Display the welcome message used at the start of the program.
     */
    public void showWelcome() {
        printMessage("Hello! I'm Skye, your personal task assistant.\n"
                + "What can I do for you?");
    }

    /**
     * Display the exit message shown when exiting the program.
     */
    public void showGoodBye() {
        scanner.close();
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Display the error message when writing to the save file has failed.
     */
    public void showLoadingError() {
        printMessage("Sorry! I was unable to load the save file :(");
    }

    /**
     * Display the current list of tasks that the user has recorded.
     *
     * @param tasks A list of tasks from the TaskList
     */
    public void showTasks(List<Task> tasks) {
        renderLine();
        System.out.println("Here are the tasks in your list:");
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, tasks.get(i));
            }
        } else {
            System.out.println("Nice!! You're all caught up and have no pending tasks to worry about.");
        }
        renderLine();
    }

    /**
     * Display the task that the user has recently added.
     *
     * @param task Task that was added to the task list
     * @param tasks Task list
     */
    public void showAddedTask(Task task, List<Task> tasks) {
        printMessage(
            String.format(
                    "Got it. I've added this task:\n %s\nNow you have %d task(s) in the list.",
                    task.toString(),
                    tasks.size()
            )
        );
    }

    /**
     * Display the task that the user has recently deleted.
     *
     * @param task Task that was deleted from the task list
     * @param tasks Task list
     */
    public void showRemovedTask(Task task, List<Task> tasks) {
        printMessage(
            String.format(
                "Noted. I've removed this task:\n %s\nNow you have %d tasks in the list",
                task.toString(),
                tasks.size()
            )
        );
    }

    /**
     * Display the task that was recently marked as completed.
     *
     * @param task Task marked as completed
     */
    public void showMarkedTask(Task task) {
        printMessage(String.format("Nice! I've marked this task as done:\n %s", task));
    }

    /**
     * Displays the task that was recently unmarked as incomplete.
     *
     * @param task Task unmarked as incomplete
     */
    public void showUnmarkedTask(Task task) {
        printMessage(String.format("OK, I've marked this task as not done yet:\n %s", task));
    }

    /**
     * Displays the list of tasks due on a specified date
     *
     * @param date Due date
     * @param tasks Task list
     */
    public void showTasksDueOn(LocalDate date, List<Task> tasks) {
        renderLine();
        System.out.println("Here are the tasks due on: " + date.toString());
        if (!tasks.isEmpty()) {
            tasks.forEach(System.out::println);
        } else {
            System.out.println("Great!! You've nothing due!");
        }
        renderLine();
    }

    /**
     * Displays a help guide for the user.
     *
     * @param lines Lines from the help guide
     */
    public void showHelpMessage(List<String> lines) {
        renderLine();
        for (String line : lines) {
            System.out.println(line);
        }
        renderLine();
    }

    /**
     * Displays a message when an unrecognized command is typed and
     * refers the user to the help command
     */
    public void showInvalidCommandMsg() {
        printMessage("I'm sorry, I don't know what that means :-(");
    }

    /**
     * Display a list of matching tasks on the command line interface.
     *
     * @param tasks A list of matching tasks found
     */
    public void showFoundTasks(List<Task> tasks) {
        renderLine();
        System.out.println("Here are the matching tasks in your list:");
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, tasks.get(i));
            }
        } else {
            System.out.println("No matching tasks found :(");
        }
        renderLine();
    }
}
