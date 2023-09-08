package ui;

import java.util.Scanner;

import task.Task;

import tasklist.TaskList;

/**
 * This class handles the User Interface part of the chatbot,
 * managing user inputs and displaying outputs.
 */
public class Ui {

    /** Scanner for user input */
    private Scanner scanner;

    /** For spacing purposes */
    private static final String SPACE = "------------------------------------";

    /** Name of bot */
    private String name = "Adam's Bot";

    /**
     * Constructs a Ui object which initializes a Scanner object to read user inputs.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command input from the user.
     *
     * @return The trimmed user input.
     */
    public String readCommand() {
        // remove trailing spaces and get use input
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println(SPACE);
        System.out.println(errorMessage);
        System.out.println(SPACE);
    }

    /**
     * Displays a welcome message to the user when they first run the program.
     */
    public void showWelcome() {
        System.out.println(SPACE);
        System.out.println("Hello! I'm " + this.name);
        System.out.println("What can I do for you?");
        System.out.println(SPACE);
    }

    /**
     * Displays a goodbye message to the user when they exit the program with the command "bye".
     */
    public void showGoodbye() {
        System.out.println(SPACE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SPACE);
    }

    /**
     * Displays a list of tasks to the user when they enter the command "list".
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        System.out.println(SPACE);

        // iterate through ArrayList to print tasks
        for (int i = 0; i < taskList.size(); i++) {
            int currentNumber = i + 1;
            System.out.println(currentNumber + ". " + taskList.get(currentNumber).toString());
        }
        System.out.println(SPACE);
    }

    /**
     * Displays the task to the user that was marked done.
     *
     * @param task The task to be displayed after being marked done.
     */
    public void showMarkText(Task task) {
        System.out.println(SPACE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
        System.out.println(SPACE);
    }

    /**
     * Displays the task to the user that was marked undone.
     *
     * @param task The task to be displayed after being marked undone.
     */
    public void showUnmarkText(Task task) {
        System.out.println(SPACE);
        System.out.println("Nice! I've marked this task as undone:");
        System.out.println("  " + task.toString());
        System.out.println(SPACE);
    }

    /**
     * Displays the task that has been added and the size of the list after that addition.
     *
     * @param task The task to be added into the list.
     * @param size The current size of the task list after adding the task into the list.
     */
    public void showAddText(Task task, int size) {
        System.out.println(SPACE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(SPACE);
    }

    /**
     * Displays the task that has been deleted and the size of the list after that deletion.
     *
     * @param task The task to be deleted from the list.
     * @param size The current size of the task list after deleting the task from the list.
     */
    public void showDeleteText(Task task, int size) {
        System.out.println(SPACE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(SPACE);
    }
}

