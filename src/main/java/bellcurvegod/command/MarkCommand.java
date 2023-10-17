package bellcurvegod.command;

import bellcurvegod.gui.Gui;
import bellcurvegod.task.Task;

/**
 * Encapsulates the markCommand.
 */
public class MarkCommand {
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

    public static String getHelpMessage() {
        return "Type 'mark <index>' and enter, the app will mark the task with the given index as done.\n"
            + "You can also mark multiple tasks as done by typing 'mark <index>,<index>,<index>,...' "
            + "with no spaces between the indices and commas.";
    }
}
