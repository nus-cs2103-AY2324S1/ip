package koko;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handles user interface interactions, including reading input and displaying output.
 */
public class Ui {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private final String name;
    private final Scanner scanner;

    /**
     * Constructs a new Ui instance.
     *
     * @param name The name of the UI, typically the name of the bot or service.
     */
    public Ui(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a message in a standardized format.
     *
     * @param originalMessage The message to be printed.
     */
    private void printFormatted(String originalMessage) {
        String indentedMessage = Arrays.stream(originalMessage.split("\n"))
                .map(line -> "     " + line)
                .collect(Collectors.joining("\n"));

        String formattedMessage = String.format("    %s\n%s\n    %s",
                HORIZONTAL_LINE, indentedMessage, HORIZONTAL_LINE);

        System.out.println(formattedMessage);
    }

    /**
     * Starts a loop to read user input until the user inputs "bye".
     *
     * @param parseInputAndDispatch A Consumer that takes user input and performs appropriate actions.
     */
    public void startUserInputLoop(Consumer<String> parseInputAndDispatch) {
        Stream.generate(scanner::nextLine)
                .takeWhile(input -> !input.equals("bye"))
                .forEach(parseInputAndDispatch);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        printFormatted("Hello! I'm " + this.name + "\nWhat can I do for you?");
    }

    /**
     * Displays an exit message to the user.
     */
    public void exit() {
        printFormatted("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message to the user when a task is added.
     *
     * @param task      The task that was added.
     * @param taskCount The number of tasks in the list.
     */
    public void printTaskAddedMessage(Task task, int taskCount) {
        printFormatted(String.format("Got it. I've added this task:\n  %s\n Now you have %d %s in the list.",
                task.toString(), taskCount, (taskCount == 1 ? "task" : "tasks")));
    }

    /**
     * Displays a message to the user when a task is deleted.
     *
     * @param task      The task that was deleted.
     * @param taskCount The number of tasks in the list.
     */
    public void printTaskDeletedMessage(Task task, int taskCount) {
        printFormatted(String.format("Noted. I've removed this task:\n  %s\n Now you have %d %s in the list.",
                task.toString(), taskCount, (taskCount == 1 ? "task" : "tasks")));
    }

    /**
     * Displays a message to the user when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printTaskMarkedMessage(Task task) {
        printFormatted(String.format("Nice! I've marked this task as done:\n  %s", task.toString()));
    }

    /**
     * Displays a message to the user when a task is marked as undone.
     *
     * @param task The task that was marked as undone.
     */
    public void printTaskUnmarkedMessage(Task task) {
        printFormatted(String.format("Nice! I've unmarked this task as done:\n  %s", task.toString()));
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void printTaskList(TaskList taskList) {
        printFormatted(taskList.toStringForUi());
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void printErrorMessage(String errorMessage) {
        printFormatted(String.format("Error: %s", errorMessage));
    }

    /**
     * Displays a message to the user when tasks are loaded from a file.
     *
     * @param taskList The list of tasks that were loaded.
     */
    public void showLoadedTasks(TaskList taskList) {
        printFormatted("Loaded tasks from file:\n" + taskList.toStringForUi());
    }

    /**
     * Displays a list of tasks that match a keyword.
     * @param matchingTasks The list of tasks that match a keyword.
     */
    public void showMatchingTasks(TaskList matchingTasks) {
        printFormatted("Here are the matching tasks in your list:\n" + matchingTasks.toStringForUi());
    }

}
