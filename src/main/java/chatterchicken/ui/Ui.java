package chatterchicken.ui;

import chatterchicken.task.Task;
import java.util.ArrayList;

public class Ui {

    private static final String LINE = "\n    _____________________________________________________________________________\n";

    private static final String INDENT = "      ";
    private static final String INDENT_BIG = "        ";

    /**
     * Displays a greeting message to the user to introduce chatterchicken.ChatterChicken.
     */
    public void displayGreeting() {
        printToScreen(INDENT + "Hello! I'm ChatterChicken!\n"
                + INDENT + "What can I do for you?");
    }

    /**
     * Displays a farewell message to the user as they exit the chatterchicken.ChatterChicken application.
     */
    public void displayFarewell() {
        printToScreen(INDENT + "Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task The task that was added.
     * @param size The updated size of the task list.
     */
    public void displayAddTask(Task task, int size) {
        printToScreen(INDENT + "Got it. I've added this task:\n"
                + INDENT_BIG + task.getTaskForPrinting() + "\n"
                + INDENT + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been deleted from the task list.
     *
     * @param task The task that was deleted.
     * @param size The updated size of the task list.
     */
    public void displayDeleteTask(Task task, int size) {
        printToScreen(INDENT + "Noted. I've removed this task:\n"
                + INDENT_BIG + task.getTaskForPrinting() + "\n"
                + INDENT + "Now you have " + size + " tasks in your list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public void displayMarkTask(Task task) {
        printToScreen(INDENT + "Nice! I've marked this task as done:\n"
                + INDENT_BIG + task.getTaskForPrinting());
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked.
     */
    public void displayUnmarkTask(Task task) {
        printToScreen(INDENT + "OK, I've marked this task as not done yet:\n"
                + INDENT_BIG + task.getTaskForPrinting());
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void displayList(ArrayList<Task> taskList) {
        System.out.println(LINE + INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(INDENT_BIG + (i + 1) + "." + taskList.get(i).getTaskForPrinting());
        }
        System.out.println(LINE);
    }

    /**
     * Prints a message to the screen, surrounded by the predefined line separator.
     *
     * @param message The message to be printed.
     */
    public void printToScreen(String message) {
        System.out.println(LINE + message + LINE);
    }
}
