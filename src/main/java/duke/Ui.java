package duke;

import tasks.Task;
import tasks.TaskList;

/**
 * Prints out all the relevant content for the user interface.
 */
public class Ui {

    private static final String HORIZONTAL_LINE = "_____________________________________________________\n";
    private static final String INTRO_MESSAGE = HORIZONTAL_LINE +
            " ____  _   _   ____  _____  ____   _     ____  _____\n" +
            "/ (__`| |_| | / () \\|_   _|/ () \\ | |__ / () \\|_   _|\n" +
            "\\____)|_| |_|/__/\\__\\ |_| /__/\\__\\|____|\\____/  |_|\n\n" +
            "Hello! I'm ChatALot.\n" +
            "What can I do for you?\n" +
            HORIZONTAL_LINE;
    private static final String OUTRO_MESSAGE = HORIZONTAL_LINE +
            "Bye. Hope to see you again soon!\n" +
            HORIZONTAL_LINE;

    /**
     * Prints the intro message.
     */
    public static void intro() {
        System.out.print(INTRO_MESSAGE);
    }

    /**
     * Prints the outro message.
     */
    public static void outro() {
        System.out.print(OUTRO_MESSAGE);
    }

    /**
     * Prints the message of when a new task is added.
     *
     * @param task Task that was added.
     * @param taskListSize Size of task list after the task was added.
     */
    public static void addTask(Task task, int taskListSize) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:\n" +
                "  " +
                task +
                "\nNow you have " +
                taskListSize +
                " tasks in the list.");
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Prints the message of when a task is deleted.
     *
     * @param task Task that was deleted.
     * @param taskListSize Size of task list after the task was deleted.
     */
    public static void deleteTask(Task task, int taskListSize) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(String.format("Noted. I've removed this task:\n" +
                "  " +
                task +
                "\nNow you have %d tasks in the list.", taskListSize));
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Prints the message of when a task is marked as done.
     *
     * @param task Task that was marked as done.
     */
    public static void mark(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:\n" +
                "  " +
                task);
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Prints the message of when a task is marked as not done.
     *
     * @param task Task that was marked as not done.
     */
    public static void unmark(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "  " +
                task);
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Lists out all current tasks to the user.
     *
     * @param taskList Latest task list of tasks.
     */
    public static void list(TaskList taskList) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(taskList);
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Prints out the indicated  message to the user.
     *
     * @param message Message to be printed.
     */
    public static void printMessage(String message) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.print(HORIZONTAL_LINE);
    }

}