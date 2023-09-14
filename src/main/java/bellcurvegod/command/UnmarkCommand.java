package bellcurvegod.command;

import bellcurvegod.gui.Gui;
import bellcurvegod.task.Task;

/**
 * Encapsulates the unmarkCommand.
 */
public class UnmarkCommand implements Runnable {
    /**
     * Marks the given task as not done.
     *
     * @param task task to be marked as not done.
     */
    public static String run(Task task) {
        task.markAsNotDone();
        return Gui.getUnmarkMessage(task);
    }
}
