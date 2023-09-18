package bellcurvegod.command;

import bellcurvegod.gui.Gui;
import bellcurvegod.task.Task;

/**
 * Encapsulates the unmarkCommand.
 */
public class UnmarkCommand {
    /**
     * Marks the given task(s) as not done.
     *
     * @param tasks task(s) to be marked as not done.
     * @return UnmarkMessage.
     */
    public static String run(Task... tasks) {
        for (Task t : tasks) {
            t.markAsNotDone();
        }
        return Gui.getUnmarkMessage(tasks);
    }
}
