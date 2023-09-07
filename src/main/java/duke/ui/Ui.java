package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the messages printed to the user.
 */
public class Ui {
    private final String HORIZONTAL_LINE = "    _______________________________________________________________";

    /**
     * Prints the task list to the user.
     * @param taskList The list of tasks.
     */
    public void printList(TaskList taskList) {
        System.out.println("    Here are the tasks in your list:");
        taskList.printTaskList();
    }

    /**
     * Prints the hello message to the user.
     */
    public void hello() {
        printHorizontalLine();
        System.out.println("    Hello! I'm Thinh's chatbot\n    What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints the goodbye message to the user.
     */
    public void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Prints the add task message to the user.
     *
     * @param task The task added to the task list.
     * @param newSize The new size of the task list.
     */
    public void addTask(Task task, int newSize) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task.toString());
        System.out.println("    Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Prints the delete task message to the user.
     *
     * @param task The task deleted from the task list.
     * @param newSize The new size fo the task list.
     */
    public void deleteTask(Task task, int newSize) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task.toString());
        System.out.println("    Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Prints the message that shows the task has marked done.
     *
     * @param task The task to be marked done.
     */
    public void markTaskDone(Task task) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task.toString());
    }

    /**
     * Prints the message that shows the task has marked not done.
     *
     * @param task The task to be marked not done.
     */
    public void markTaskNotDone(Task task) {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task.toString());
    }

    /**
     * Prints the horizontal line.
     */
    public void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the error occurred when running in the program.
     *
     * @param e The exception thrown in the program.
     */
    public void printError(Exception e) {
        System.out.println("    " + e.getMessage());
    }
}
