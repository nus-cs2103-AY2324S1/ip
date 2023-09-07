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
    private String DIVIDER = "____________________________________________________________\n";
    private String LOGO = "(• >       (• >       (• >       (• >       (• >       (• >\n"
            +  "/  ))        /  ))        /  ))        /  ))        /  ))        /  ))\n"
            +  "  ``          ``          ``          ``          ``         ``";
    private String HELP = "IMPORTANT NOTES:\n"
            + "Todo: todo <task>\n"
            + "Deadline: deadline <deadline> /by <duedate>\n"
            + "Event: event <event> /from <start> /to <end>\n"
            + "Datetime format: \"dd/MM/yyyy HH:mm\"\n";
    private String GREET = "Hello! I'm Birdy\n"
            + "chirp chirp! How can I help?\n";
    private String PARTING = "chirp! See you around!\n";

    private String currentMessage = "";

    private void appendCurrentMessage(String message) {
        currentMessage += message + "\n";
    }

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
        appendCurrentMessage(error);
        String output = DIVIDER + "    " + error + DIVIDER;
        System.out.println(output);
    }

    /**
     * Displays an error message when loading tasks from a file fails.
     */
    public void showLoadingError() {
        appendCurrentMessage("chirp! cannot load tasks from file");
        String output = DIVIDER + "    chirp! cannot load tasks from file\n" + DIVIDER;
        System.out.println(output);
    }

    /**
     * Displays a parting message to the user.
     */
    public void showParting() {
        appendCurrentMessage(PARTING);
        String output = DIVIDER + PARTING + DIVIDER;
        System.out.println(output);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        appendCurrentMessage(LOGO);
        appendCurrentMessage(HELP);
        appendCurrentMessage(GREET);
        String output = LOGO + DIVIDER + HELP + DIVIDER + GREET;
        System.out.println(output);
    }

    /**
     * Displays help information to the user.
     */
    public void showHelp() {
        appendCurrentMessage(HELP);
        String output = DIVIDER + HELP + DIVIDER;
        System.out.println(output);
    }

    /**
     * Displays a horizontal line.
     */
    public void addLine() {
        appendCurrentMessage("");
        System.out.println(DIVIDER);
    }

    /**
     * Displays a list of tasks to the user
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showList(List<Task> tasks) {
        System.out.println(DIVIDER);
        if (tasks.size() < 1) {
            appendCurrentMessage("CHIRP! free and easy");
            System.out.println("    CHIRP! free and easy");
            return;
        }
        tasks.forEach(task -> appendCurrentMessage(task.toString()));
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
        appendCurrentMessage("chirp! I've added this task");
        appendCurrentMessage(task);
        appendCurrentMessage(String.format("Now you have %d tasks in the list", size));
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
        appendCurrentMessage("chirp chirp! right out the window");
        System.out.printf(DIVIDER + "    chirp! chirp! %s right out the window!\n" + DIVIDER, task);
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMark(Task task) {
        appendCurrentMessage("chirp! I've marked this task as done: ");
        appendCurrentMessage(task.toString());
        System.out.println(DIVIDER + "    chirp! I've mark this task as done:\n" +  String.format("    %s\n", task.toString()) + DIVIDER);
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmark(Task task) {
        appendCurrentMessage("chirp! I've mark this task as not done yet:");
        appendCurrentMessage(task.toString());
        System.out.println(DIVIDER + "    chirp! I've mark this task as not done yet:\n" +  String.format("    %s\n", task.toString()) + DIVIDER);
    }

    /**
     * Display tasks in the filtered list.
     *
     * @param filteredTasks The filtered list of tasks
     */
    public void showMatch(List<Task> filteredTasks) {
        System.out.println(DIVIDER);
        System.out.println("    tweet! Here are the matching tasks in your list:");
        for (int i = 0; i < filteredTasks.size(); i ++) {
            String task = filteredTasks.get(i).toString();
            appendCurrentMessage(task);
            System.out.printf("    %d. %s\n", i + 1, task);
        }
        System.out.println(DIVIDER);
    }

    public String getCurrentMessage() {
        String message = currentMessage;
        currentMessage = "";
        return message;
    }
}
