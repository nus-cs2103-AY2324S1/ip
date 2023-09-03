package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to handle interactions with the user.
 */
public class UI {
    private String name;
    private Scanner scanner;

    /**
     * Constructor for the UI class.
     *
     * @param name Name of the chatbot.
     */
    public UI(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns user input.
     *
     * @return User input.
     */
    public String readCommand() {
        String line = scanner.nextLine();
        return line;
    }

    /**
     * Prints a welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm " + this.name);
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a task added message.
     *
     * @param task Task added.
     * @param taskCount Total number of tasks.
     */
    public void printTaskAddedMessage(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        this.printTaskCount(taskCount);
    }

    /**
     * Prints a task deleted message.
     *
     * @param task Task deleted.
     * @param taskCount Total number of tasks.
     */
    public void printTaskDeletedMessage(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        this.printTaskCount(taskCount);
    }

    /**
     * Prints a task marked message.
     *
     * @param task Task marked.
     */
    public void printTaskMarkedMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints a task unmarked message.
     *
     * @param task Task unmarked.
     */
    public void printTaskUnmarkedMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints the tasks on a date.
     *
     * @param tasks List of tasks on a date
     */
    public void printTasksOn(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Prints the tasks matching a keyword.
     *
     * @param tasks List of tasks matching a keyword
     */
    public void printTasksMatching(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Prints a loading error message.
     */
    public void printLoadingErrorMessage() {
        System.out.println("Stored data could not be loaded");
    }

    /**
     * Closes scanner object used to read input
     */
    public void closeUi() {
        this.scanner.close();
    }

    /**
     * Prints a task count message.
     *
     * @param taskCount Total number of tasks.
     */
    private void printTaskCount(int taskCount) {
        System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
    }
}
