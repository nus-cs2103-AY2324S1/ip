package bellcurvegod.command;

import bellcurvegod.task.Task;
import bellcurvegod.ui.Ui;

/**
 * Encapsulates the markCommand.
 */
public class MarkCommand implements Runnable {
    /**
     * Marks the given task as done.
     *
     * @param task task to be marked as done.
     */
    public static void run(Task task) {
        task.markAsDone();
        Ui.showMarkMessage(task);
    }
}
