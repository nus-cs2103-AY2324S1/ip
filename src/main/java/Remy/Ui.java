package remy;

import java.util.Scanner;

import remy.task.Task;
import remy.task.TaskList;

/**
 * Reads user input and outputs Remy's response in the correct format.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String SHORT_DIVIDER = "_____________\n";
    private static final String WELCOME_MESSAGE = "I'm Remy.Remy, and it is NOT nice to see you.\n"
                    + "Faster tell me what you want and go away.";

    private static final String EXIT_MESSAGE = "Hope to never see you again!\n" + DIVIDER;

    public final Scanner scanner;

    /**
     * Constructs new Ui object that is able to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void printShortDivider() {
        System.out.println(SHORT_DIVIDER);
    }

    /**
     * Prints out a horizontal divider line.
     */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the Welcome Message segment.
     */
    public static void printWelcomeMessage() {
        printLongSandwich(WELCOME_MESSAGE);
    }

    /**
     * Prints the Exit Message segment.
     */
    public static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
        printDivider();
    }

    /**
     * Prints given content sandwiched by two short dividers.
     * @param content to be included within the dividers.
     */
    public static void printShortSandwich(String content) {
        printShortDivider();
        System.out.println(content);
        printShortDivider();
    }

    /**
     * Prints given content sandwiched by two long dividers.
     * @param content to be included within the dividers.
     */
    public static void printLongSandwich(String content) {
        printDivider();
        System.out.println(content);
        printDivider();
    }

    /**
     * Prints out an error message surrounded by exclamation marks.
     * @param errorMessage to be included within the exclamation dividers.
     */
    public static void printError(String errorMessage) {
        System.out.println("\n" + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(errorMessage);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
    }

    /**
     * Prints out the taskList sandwiched by short dividers.
     * @param taskList TaskList of items to be printed.
     */
    public static void printList(TaskList taskList) {
        printShortSandwich(taskList.toString());
    }

    /**
     * Prints out the "Successfully added task" message with the following details:
     *    1. String representation of Task added
     *    2. Total tasks in the taskList.
     * @param task that has been added.
     * @param num of tasks currently in the taskList.
     */
    public static void printAddedTask(Task task, int num) {
        String taskWord = num == 1 ? "task" : "tasks";
        String content = "Added, now scram.\n" + task.toString() + "\n"
                + "Now you have " + num + " " + taskWord + " in the list.";
        Ui.printShortSandwich(content);
    }

    /**
     * Prints search results with the corresponding message and formatting.
     */
    public static void printSearchResults(String results) {
        String content = "Here are the matching tasks in your list: \n" + results;
        printShortSandwich(content);
    }

    /**
     * Reads and returns the input submitted by the user.
     * If the given command is entirely made of whitespace, continuously prompt user for new input.
     * @return input The input submitted by the user.
     */
    //@@author samuelim01-reused
    // Reused Samuel's inplementation of this method
    public String readCommand() {
        String input = scanner.nextLine();
        while (input.trim().isEmpty()) {
            input = scanner.nextLine();
        }
        return input;
    }
}
