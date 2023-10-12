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
    public String generateAddMessage(Task task, int size) {
        String str = "Got it!. I've added this task:\n"
                + INDENTATION + task + "\n"
                + String.format("Now you have %d tasks in the list", size);
        System.out.println(str);
        System.out.println(LINE);
        return str;
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The deleted task.
     * @param size The number of tasks after deletion.
     */
    public String generateDeleteMessage(Task task, int size) {
        String str = "Noted... I've removed this task:\n"
                + INDENTATION + task + "\n"
                + String.format("Now you have %d tasks in the list", size);
        System.out.println(str);
        System.out.println(LINE);
        return str;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The marked task.
     */
    public String generateMarkMessage(Task task) {
        String str = "Great job completing the task! I've marked it as done."
                + INDENTATION + task;
        System.out.println(str);
        System.out.println(LINE);
        return str;
    }

    /**
     * Displays a message indicating that a task has been marked as undone.
     *
     * @param task The marked task.
     */
    public String generateUnmarkMessage(Task task) {
        String str = "Oops... Did you mark it incorrectly?"
                + INDENTATION + task;
        System.out.println(str);
        System.out.println(LINE);
        return str;
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     * @return The list of tasks as a String.
     */
    public String printTasks(TaskList tasks) {
        StringBuilder str = new StringBuilder();
        str.append("Here are your list of tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            str.append(INDENTATION).append(i + 1).append(" ").append(task).append("\n");
        }
        System.out.println(str);
        System.out.println(LINE);
        return str.toString();
    }

    /**
     * Displays the scheduled tasks for a given date and time.
     *
     * @param tasks    The list of tasks to be displayed.
     * @param datetime The specified date and time for filtering tasks.
     * @return The list of scheduled tasks as a String.
     */
    public String printScheduledTasks(TaskList tasks, LocalDateTime datetime) {
        StringBuilder str = new StringBuilder();
        str.append("Here are your list of tasks:\n");
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isWithinDateRange(datetime)) {
                str.append(INDENTATION).append(index++).append(" ").append(task).append("\n");
            }
        }
        System.out.println(str);
        System.out.println(LINE);
        return str.toString();
    }

    /**
     * Displays the tasks related to a certain keyword.
     *
     * @param tasks    The list of tasks to be displayed.
     * @param keyword  The word used to filter tasks.
     * @return The list of related tasks as a String.
     */
    public String printRelatedTasks(TaskList tasks, String keyword) {
        StringBuilder str = new StringBuilder();
        str.append("Here are your list of tasks:\n");
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            assert task != null;

            if (task.isRelatedContent(keyword)) {
                str.append(INDENTATION).append(index++).append(" ").append(task).append("\n");
            }
        }
        System.out.println(str);
        System.out.println(LINE);
        return str.toString();
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
