package duke.tools;

import java.time.LocalDateTime;
import java.util.Scanner;

import duke.tasks.Task;

/**
 * Handles user interface interactions, displaying messages and reading user input.
 */
public class Ui {

    static final String LINE = "_______________________________________";
    static final String INDENTATION = "  ";
    private final Scanner scanner;

    /**
     * Constructs an Ui object and initializes the scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message with the bot's logo.
     */
    public void showWelcome() {
        String logo = "                     _                 _      \n"
                + " _ __ ___  ___ _ __ (_)_ __ ___  _ __ (_)_  __\n"
                + "| '__/ _ \\/ __| '_ \\| | '__/ _ \\| '_ \\| \\ \\/ /\n"
                + "| | |  __/\\__ \\ |_) | | | | (_) | | | | |>  < \n"
                + "|_|  \\___||___/ .__/|_|_|  \\___/|_| |_|_/_/\\_\\\n"
                + "              |_|                             ";
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm your personal AI");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Reads the user's command from the scanner.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Checks if there is more input from the user.
     *
     * @return True if there's more input, false otherwise.
     */
    public boolean checkForCommand() {
        return scanner.hasNext();
    }

    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the outro message when the bot is exiting.
     */
    public void showOutro() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The added task.
     * @param size The number of tasks after adding.
     */
    public void showAddMessage(Task task, int size) {
        System.out.println("Got it!. I've added this task:");
        System.out.println(INDENTATION + task);
        System.out.printf("Now you have %d tasks in the list%n", size);
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The deleted task.
     * @param size The number of tasks after deletion.
     */
    public void showDeleteMessage(Task task, int size) {
        System.out.println("Noted... I've removed this task:");
        System.out.println(INDENTATION + task);
        System.out.printf("Now you have %d tasks in the list%n", size);
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The marked task.
     */
    public void showMarkMessage(Task task) {
        System.out.println("Great job completing the task! I've marked it as done.");
        System.out.println(INDENTATION + task);
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating that a task has been marked as undone.
     *
     * @param task The marked task.
     */
    public void showUnmarkMessage(Task task) {
        System.out.println("Oops... Did you mark it incorrectly?");
        System.out.println(INDENTATION + task);
        System.out.println(LINE);
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void printTasks(TaskList tasks) {
        System.out.println("Here are your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf(INDENTATION + "%d. %s%n", i + 1, task);
        }
        System.out.println(LINE);
    }

    /**
     * Displays the scheduled tasks for a given date and time.
     *
     * @param tasks    The list of tasks to be displayed.
     * @param datetime The specified date and time for filtering tasks.
     */
    public void printScheduledTasks(TaskList tasks, LocalDateTime datetime) {
        System.out.println("Here are your list of tasks:");
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isWithinDateRange(datetime)) {
                System.out.printf(INDENTATION + "%d. %s%n", index++, task);
            }
        }
        System.out.println(LINE);
    }

    /**
     * Displays the tasks related to a certain keyword.
     *
     * @param tasks    The list of tasks to be displayed.
     * @param keyword  The word used to filter tasks.
     */
    public void printRelatedTasks(TaskList tasks, String keyword) {
        System.out.println("Here are your list of tasks:");
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isRelatedContent(keyword)) {
                System.out.printf(INDENTATION + "%d. %s%n", index++, task);
            }
        }
        System.out.println(LINE);
    }

    /**
     * Displays an error message.
     *
     * @param e The exception containing the error message.
     */
    public void showErrorMessage(Exception e) {
        System.out.println(e.getMessage());
        System.out.println(LINE);
    }

    /**
     * Displays an error message indicating an unknown issue.
     */
    public void showLoadingError() {
        System.out.println("Respironix has encountered an issue; exiting");
    }

}
