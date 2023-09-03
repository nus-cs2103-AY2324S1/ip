package bellcurvegod.command;

import bellcurvegod.task.Task;
import bellcurvegod.ui.Ui;

/**
 * Encapsulates the unmarkCommand.
 */
public class UnmarkCommand implements Runnable {
    /**
     * Marks the given task as not done.
     *
     * @param task task to be marked as not done.
     */
    public static void run(Task task) {
        Ui.showLine();
        task.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        Ui.showLine();
    }
}
