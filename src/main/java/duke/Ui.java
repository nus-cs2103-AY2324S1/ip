package duke;

import tasks.Task;
import tasks.TaskList;

/**
 * Prints formatted outputs for all the commands
 */
public class Ui {

    private static final String HORIZONTAL_LINE =
            "_____________________________________________________\n";
    private static final String INTRO_MESSAGE = HORIZONTAL_LINE
            + " ____  _   _   ____  _____  ____   _     ____  _____\n"
            + "/ (__`| |_| | / () \\|_   _|/ () \\ | |__ / () \\|_   _|\n"
            + "\\____)|_| |_|/__/\\__\\ |_| /__/\\__\\|____|\\____/  |_|\n\n"
            + "Hello! I'm ChatALot.\n"
            + "What can I do for you?\n"
            + HORIZONTAL_LINE;
    private static final String OUTRO_MESSAGE = HORIZONTAL_LINE
            + "Bye. Hope to see you again soon!\n"
            + HORIZONTAL_LINE;

    public static void printIntro() {
        System.out.print(INTRO_MESSAGE);
    }

    public static void printOutro() {
        System.out.print(OUTRO_MESSAGE);
    }

    /**
     * Prints the formatted output for when a task is added.
     *
     * @param task The task that was added.
     * @param taskListSize The task list size after the task was added.
     */
    public static void printAddingOfTask(Task task, int taskListSize) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:\n"
                + "  "
                + task
                + "\nNow you have "
                + taskListSize
                + " tasks in the list.");
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Prints the formatted output for when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param taskListSize The task list size after the task was deleted.
     */
    public static void printDeletingOfTask(Task task, int taskListSize) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(String.format("Noted. I've removed this task:\n"
                + "  "
                + task
                + "\nNow you have %d tasks in the list.", taskListSize));
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Prints the formatted output for when a task is marked complete.
     *
     * @param task Task that was marked complete.
     */
    public static void printMarkingOfTask(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:\n"
                + "  "
                + task);
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Prints the formatted output for when a task is marked incomplete.
     *
     * @param task Task that was marked incomplete.
     */
    public static void printUnmarkingOfTask(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:\n"
                + "  "
                + task);
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Prints the current task list.
     *
     * @param taskList The task list to be printed.
     */
    public static void printTaskList(TaskList taskList) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(taskList);
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Prints any arbitrary message.
     *
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.print(HORIZONTAL_LINE);
    }

}
