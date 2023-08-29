package ren;

import ren.task.Task;
import ren.task.TaskList;

/**
 * Represents the User Interface of Ren.
 */
public class RenUi {
    private static final String LINE_BREAK = "____________________________________________________________\n";
    private static String welcomeMsg = LINE_BREAK +
            " Hello! I'm Ren.\n" +
            " What can I do for you?\n" +
            LINE_BREAK;
    private static String goodbyeMsg = LINE_BREAK +
            " Bye! Pleasure speaking with you :) \n" +
            LINE_BREAK;

    /**
     * Prints welcome message.
     */
    public static void printWelcomeMsg() {
        System.out.println(welcomeMsg);
    }

    /**
     * Prints goodbye message.
     */
    public static void printGoodbyeMsg() {
        System.out.println(goodbyeMsg);
    }

    /**
     * Prints error message when user inputs an invalid command.
     */
    public static void requestProperCommand() {
        System.out.println(LINE_BREAK);
        System.out.println("Please Enter a valid command.");
        System.out.println(LINE_BREAK);
    }

    /**
     * Message for when a task is added.
     */
    public static void declareTaskAdded(Task task, TaskList tasks) {
        System.out.println(LINE_BREAK +
                String.format("Added %s\n", task) +
                tasks.declareNumOfTasks() +
                LINE_BREAK);
    }

    /**
     * Message for when a task is deleted.
     */
    public static void declareTaskDeleted(Task task, TaskList tasks) {
        System.out.println(LINE_BREAK +
                String.format("Deleted %s\n", task) +
                tasks.declareNumOfTasks() +
                LINE_BREAK);
    }

    /**
     * Message for when a task is updated.
     */
    public static void declareTaskUpdated(Task task, TaskList tasks, boolean isDone) {
        System.out.println(LINE_BREAK +
                String.format("Marked as %s!\n %s\n",
                        isDone ? "done" : "undone",
                        task) +
                LINE_BREAK);
    }

    /**
     * Prints the list of tasks.
     */
    public static void listTasks(TaskList tasks) {
        System.out.println(LINE_BREAK);
        try {
            tasks.listTasks();
        } catch (NullPointerException e) {
            System.out.println("No tasks to list!");
        }
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints the list of tasks that match the query.
     */
    public static void displayFoundTasks(TaskList tasks, String query) {
        System.out.println(LINE_BREAK);
        System.out.println("Here is what I found:");
        tasks.listMatchingTasks(query);
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints the exception message.
     */
    public static void printException(Exception e) {
        System.out.println(LINE_BREAK);
        System.out.println(e.getMessage());
        System.out.println(LINE_BREAK);
    }
}
