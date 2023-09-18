package bellcurvegod.command;

import bellcurvegod.gui.Gui;
import bellcurvegod.task.Task;

/**
 * Encapsulates the markCommand.
 */
public class MarkCommand implements Runnable {
    /**
     * Marks the given task(s) as done.
     *
     * @param tasks task(s) to be marked as done.
     * @return MarkMessage.
     */
    public static String run(Task... tasks) {
        for (Task t : tasks) {
            t.markAsDone();
        }
        return Gui.getMarkMessage(tasks);
    }
}
