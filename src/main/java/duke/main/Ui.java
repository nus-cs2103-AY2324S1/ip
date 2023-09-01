package duke.main;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Ui class handles user interface interactions for the chatbot application, such as displaying messages.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    // Constants
    private String DIVIDER = "    ____________________________________________________________\n";
    private String LOGO = "    (• >       (• >       (• >       (• >       (• >       (• >\n"
            +  "    /))        /))        /))        /))        /))        /))\n"
            +  "     ``         ``         ``         ``         ``         ``\n";
    private String HELP = "    IMPORTANT NOTES:\n"
            + "        Todo: todo <task>\n"
            + "        Deadline: deadline <deadline> /by <duedate>\n"
            + "        Event: event <event> /from <start> /to <end>\n"
            + "        Datetime format: \"dd/MM/yyyy HH:mm\"\n";
    private String GREET = "    Hello! I'm Birdy\n"
            + "    chirp chirp! How can I help?\n";
    private String PARTING = "    chirp! See you around!\n";

    /**
     * Reads command from the user.
     *
     * @return The command entered by user as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param error The error message to be displayed.
     */
    public void showError(String error) {
        String output = DIVIDER + "    " + error + DIVIDER;
        System.out.println(output);
    }

    /**
     * Displays an error message when loading tasks from a file fails.
     */
    public void showLoadingError() {
        String output = DIVIDER + "    chirp! cannot load tasks from file\n" + DIVIDER;
        System.out.println(output);
    }

    /**
     * Displays a parting message to the user.
     */
    public void showParting() {
        String output = DIVIDER + PARTING + DIVIDER;
        System.out.println(output);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        String output = LOGO + DIVIDER + HELP + DIVIDER + GREET;
        System.out.println(output);
    }

    /**
     * Displays help information to the user.
     */
    public void showHelp() {
        String output = DIVIDER + HELP + DIVIDER;
        System.out.println(output);
    }

    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays a list of tasks to the user
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showList(List<Task> tasks) {
        System.out.println(DIVIDER);
        tasks.forEach(task -> System.out.println("    " + task.toString()));
        System.out.println(DIVIDER);
    }

    /**
     * Displays a success message when a task is added.
     *
     * @param task The task that was added.
     * @param size The new size of the task list.
     */
    public void showAddSuccess(String task, int size) {
        System.out.println(DIVIDER + "    chirp! I've added this task:\n"
                + String.format("    %s\n", task)
                + String.format("    Now you have %d tasks in the list\n", size)
                + DIVIDER);
    }

    /**
     * Displays a success message when a task is removed.
     *
     * @param task The task that was removed.
     */
    public void showDeleteSuccess(String task) {
        System.out.println(DIVIDER + "    chirp! chirp! duke.task.Task right out the window!\n" + DIVIDER);
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMark(Task task) {
        System.out.println(DIVIDER + "    chirp! I've mark this task as done:\n" +  String.format("    %s\n", task.toString()) + DIVIDER);
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmark(Task task) {
        System.out.println(DIVIDER + "    chirp! I've mark this task as not done yet:\n" +  String.format("    %s\n", task.toString()) + DIVIDER);
    }
}
