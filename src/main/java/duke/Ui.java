package duke;

import tasks.Task;

/**
 * Provides formatted string outputs to be shown to user.
 */
public class Ui {

    private static final String INTRO_MESSAGE =
            " ____  _   _   ____  _____  ____   _     ____  _____\n"
            + "/ (__`| |_| | / () \\|_   _|/ () \\ | |__ / () \\|_   _|\n"
            + "\\____)|_| |_|/__/\\__\\ |_| /__/\\__\\|____|\\____/  |_|\n\n"
            + "Hello! I'm ChatALot.\n"
            + "What can I do for you?\n";
    private static final String OUTRO_MESSAGE =
            "Bye. Hope to see you again soon!\n";

    public static String getIntroMessage() {
        return INTRO_MESSAGE;
    }

    public static String getOutroMessage() {
        return OUTRO_MESSAGE;
    }

    /**
     * Gives the formatted output for when a task is added.
     *
     * @param task The task that was added.
     * @param taskListSize The task list size after the task was added.
     * @return The formatted output for when a task is added
     */
    public static String getTaskAddingMessage(Task task, int taskListSize) {
        return "Got it. I've added this task:\n"
                + "  "
                + task
                + "\nNow you have "
                + taskListSize
                + " tasks in the list.";
    }

    /**
     * Gives the formatted output for when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param taskListSize The task list size after the task was deleted.
     * @return The formatted output for when a task is deleted.
     */
    public static String getTaskDeletingMessage(Task task, int taskListSize) {
        return String.format("Noted. I've removed this task:\n"
                + "  "
                + task
                + "\nNow you have %d tasks in the list.", taskListSize);
    }

    /**
     * Gives the formatted output for when a task is marked complete.
     *
     * @param task Task that was marked complete.
     * @return The formatted output for when a task is marked complete.
     */
    public static String getTaskMarkingMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "  "
                + task;
    }

    /**
     * Gives the formatted output for when a task is marked incomplete.
     *
     * @param task Task that was marked incomplete.
     * @return The formatted output for when a task is marked incomplete.
     */
    public static String getTaskUnmarkingMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + "  "
                + task;
    }

}
