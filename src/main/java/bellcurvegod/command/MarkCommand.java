package bellcurvegod.command;

import bellcurvegod.gui.Gui;
import bellcurvegod.task.Task;

/**
 * Encapsulates the markCommand.
 */
public class MarkCommand implements Runnable {
    /**
     * Marks the given task as done.
     *
     * @param task task to be marked as done.
     */
    public static String run(Task task) {
        task.markAsDone();
        return Gui.getMarkMessage(task);
    }
}
