package ipbot.util;

import ipbot.model.Pair;
import ipbot.model.Task;

import java.util.Map;
import java.util.Scanner;

/**
 * A class to handle all interactions with the user.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String NAME = "Ip Bot";
    private Scanner scanner;
    private String currentCommand;

    /**
     * Defines a Ui object to interact with standard input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the dividing line.
     */
    public void printDivider() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the welcome text.
     */
    public void printWelcome() {
        printDivider();
        System.out.println("Hello I'm " + NAME + "!");
        System.out.println("While I may not be able to fight like Ip Man, I can assist you in other areas!");
        System.out.println("What can I do for you?");
        printDivider();
    }

    /**
     * Prints the exit text.
     */
    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    /**
     * Reads the command given by the user.
     */
    public void readCommand() {
        this.currentCommand = scanner.nextLine().strip();
    }

    /**
     * Process the previous command given by the user.
     *
     * @return The processed command given by the user.
     * A Pair consisting of a command as a String, and the arguments as a Map.
     */
    public Pair<String, Map<String, String>> processCommand() {
        return Parser.parseCommand(this.currentCommand);
    }

    /**
     * Returns a task as a String in list format.
     *
     * @param task The task to be printed.
     * @param number The number to be given to the task in the list.
     */
    public static String taskListFormatString(Task task, int number) {
        return String.format("%d. %s\n", number, task.toString());
    }

    /**
     * Returns a notification that a task has been added.
     *
     * @param task The task that was added.
     * @param taskTypeStr The type of task that was added.
     */
    public static String addedItemString(Task task, String taskTypeStr) {
        return "Added " + taskTypeStr + " item: " + task.toString();
    }

    /**
     * Returns a notification that a task has been deleted.
     *
     * @param task The task that was deleted.
     */
    public static String deletedItemString(Task task) {
        return "Deleted item: " + task.toString();
    }

    /**
     * Returns a notification that a task has been marked or unmarked.
     *
     * @param task The task that was marked or unmarked.
     * @param markAsDone Whether we are marking or unmarking the task.
     */
    public static String markTaskString(Task task, boolean markAsDone) {
        String done = markAsDone ? "done" : "undone";
        return "Marking task as " + done + ": " + task.toString();
    }

    /**
     * Returns a notification that we are marking or unmarking a task that has already been marked or unmarked.
     *
     * @param task The task that was marked or unmarked.
     * @param markAsDone Whether we are marking or unmarking the task.
     */
    public static String alreadyMarkedTaskString(Task task, boolean markAsDone) {
        String done = markAsDone ? "done" : "undone";
        return "Task was already marked as " + done + ": " + task.toString();
    }
}
