package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the user interface of the application.
 */
public class Ui {

    /**
     * The horizontal line to be printed.
     */
    private final String horizontalLine = "__________________________________________________________________________";

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String logo = "    ___    _   ___   ________  __      ____      __________  ____  _   __\n"
                + "   /   |  / | / / | / / __ \\ \\/ /     / __ \\    /_  __/ __ \\/ __ \\/ | / /\n"
                + "  / /| | /  |/ /  |/ / / / /\\  /_____/ / / /_____/ / / /_/ / / / /  |/ / \n"
                + " / ___ |/ /|  / /|  / /_/ / / /_____/ /_/ /_____/ / / _, _/ /_/ / /|  /  \n"
                + "/_/  |_/_/ |_/_/ |_/\\____/ /_/      \\____/     /_/ /_/ |_|\\____/_/ |_/   \n";
        System.out.println(horizontalLine + logo + "Hello! I'm ANNOY-O-TRON!\nWhat can I do for you?\n"
                + horizontalLine);
    }

    /**
     * Prints the goodbye message.
     */
    public void showBye() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
    }

    /**
     * Prints a horizontal line for UI separation.
     */
    public void showLine() {
        System.out.println(horizontalLine);
    }

    /**
     * Prints the error message when loading tasks from file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Prints the error message.
     *
     * @param message The error message to be printed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints the message when a task is added.
     *
     * @param task The task that is added.
     */
    public void showAddedTask(Task task) {
        System.out.println("Got it. I've added this task:\n" + task);
    }

    /**
     * Prints the message when a task is deleted.
     *
     * @param task The task that is deleted.
     */
    public void showDeletedTask(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task);
    }

    /**
     * Prints the message when a task is marked as done.
     *
     * @param task The task that is marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Prints the message when a task is marked as not done yet.
     *
     * @param task The task that is marked as not done yet.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + "." + task);
            index++;
        }
    }

    /**
     * Prints the current number of tasks in the list.
     *
     * @param taskList The current task list.
     */
    public void showNumberOfTasks(TaskList taskList) {
        System.out.println("Now you have " + taskList.getListSize() + " tasks in the list.");
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }
}
