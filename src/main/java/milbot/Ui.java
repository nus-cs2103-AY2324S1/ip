package milbot;

import taskclasses.*;

/**
 * Ui class handles user interface-related functionalities.
 */
public class Ui {
    private static final String INDENTATION = "     ";
    private static final String HORIZONTAL_LINE = "__________________________________________________________________________";

    /**
     * Prints a welcome message when the chatbot is launched.
     */
    public void printWelcomeMessage() {
        String logo = " ____     ____    _    _\n"
                + "|     \\__/    |  | |  | |\n"
                + "|  | \\ _ / |  |  | |  | |\n"
                + "|  |       |  |  | |  | |____\n"
                + "|__|       |__|  |_|  |______|\n";
        System.out.println(logo);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Hi there, I'm Mil - your personal chatbot.\n     How can I help you today?");
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    /**
     * Prints a goodbye message before the chatbot exits.
     */
    public void printGoodbyeMessage() {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Have a nice day and see you again soon!");
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    /**
     * Prints an error message.
     *
     * @param message The error message to be printed.
     */
    public void printErrorMessage(String message) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + message);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    /**
     * Prints a message indicating a new task has been added to the task list.
     *
     * @param taskList The TaskList containing the added task.
     * @param task     The added task.
     */
    public void printNewTask(TaskList taskList, Task task) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + "  " + task);
        System.out.println(INDENTATION + "Now you have " + taskList.getSize() + " tasks in the list.");
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList The TaskList containing the tasks to be printed.
     */
    public void printTaskList(TaskList taskList) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        int i = 1;
        for (Task task : taskList.getTaskList()) {
            System.out.println(String.format("%s%d.%s",
                    INDENTATION, i, task.toString()));
            i++;
        }
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void printMarkTask(Task task) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + task);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    /**
     * Prints a message indicating that a task has been marked as not done yet.
     *
     * @param task The task that has been marked as not done yet.
     */
    public void printUnmarkTask(Task task) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
        System.out.println(INDENTATION + task);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    /**
     * Prints a message indicating that a task has been removed from the task list.
     *
     * @param task      The task that has been removed.
     * @param taskList  The TaskList after the task has been removed.
     */
    public void printRemoveTask(Task task, TaskList taskList) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + "  " + task);
        System.out.println(INDENTATION + "Now you have " + taskList.getSize() + " tasks in the list.");
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    /**
     * Prints a message indicating an unknown command.
     */
    public void printUnknownMessage() {
        System.out.println("☹ Oopsie! I'm sorry, but I don't know what that means.");
    }

}
