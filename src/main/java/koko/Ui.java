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
     * Returns a message in a standardized format.
     *
     * @param originalMessage The message to be printed.
     */
    private String formatOutput(String originalMessage) {
        String indentedMessage = Arrays.stream(originalMessage.split("\n"))
                .map(line -> "     " + line)
                .collect(Collectors.joining("\n"));

        return indentedMessage;
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
     * Returns a greeting message to the user.
     */
    public String greet() {
        return formatOutput("Hello! I'm " + this.name + "\nWhat can I do for you?");
    }

    /**
     * Returns a message to the user when a task is added.
     *
     * @param task      The task that was added.
     * @param taskCount The number of tasks in the list.
     */
    public String generateTaskAddedMessage(Task task, int taskCount) {
        return formatOutput(String.format(
                "Got it. I've added this task:\n  %s\n Now you have %d %s in the list.",
                task.toString(), taskCount, (taskCount == 1 ? "task" : "tasks")));
    }

    /**
     * Returns a message to the user when a task is deleted.
     *
     * @param task      The task that was deleted.
     * @param taskCount The number of tasks in the list.
     */
    public String generateTaskDeletedMessage(Task task, int taskCount) {
        return formatOutput(String.format(
                "Noted. I've removed this task:\n  %s\n Now you have %d %s in the list.",
                task.toString(), taskCount, (taskCount == 1 ? "task" : "tasks")));
    }

    /**
     * Returns a message to the user when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String generateTaskMarkedMessage(Task task) {
        return formatOutput(String.format("Nice! I've marked this task as done:\n  %s", task.toString()));
    }

    /**
     * Returns a message to the user when a task is marked as undone.
     *
     * @param task The task that was marked as undone.
     */
    public String generateTaskUnmarkedMessage(Task task) {
        return formatOutput(String.format("Nice! I've unmarked this task as done:\n  %s", task.toString()));
    }

    /**
     * Returns the list of tasks to the user.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public String generateTaskListOutput(TaskList taskList) {
        return formatOutput(taskList.toStringForUi());
    }

    /**
     * Returns an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public String generateErrorMessage(String errorMessage) {
        return formatOutput(String.format("Error: %s", errorMessage));
    }

    /**
     * Returns a message to the user when tasks are loaded from a file.
     *
     * @param taskList The list of tasks that were loaded.
     */
    public String showLoadedTasks(TaskList taskList) {
        return formatOutput("Loaded tasks from file:\n" + taskList.toStringForUi());
    }

    /**
     * Returns a list of tasks that match a keyword.
     *
     * @param matchingTasks The list of tasks that match a keyword.
     */
    public String showMatchingTasks(TaskList matchingTasks) {
        return formatOutput("Here are the matching tasks in your list:\n" +
                matchingTasks.toStringForUi());
    }

}
