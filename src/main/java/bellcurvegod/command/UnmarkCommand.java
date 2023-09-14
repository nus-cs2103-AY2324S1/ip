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
        task.markAsNotDone();
        Ui.showUnmarkMessage(task);
    }
}
