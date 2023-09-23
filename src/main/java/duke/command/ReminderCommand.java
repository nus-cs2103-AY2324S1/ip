package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Command to search for nearest dated task.
 */
public class ReminderCommand implements Command {

    /**
     * Find the nearest dated task.
     * @param tasks The task list that may be modified by the command.
     * @param ui    The user interface for analyzing chat history.
     * @return false as it should not exit.
     * @throws DukeException when error occurs.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws DukeException {
        String output = tasks.findNearest();
        if (output.trim().length() == 0) {
            ui.respond("You don't have any events or deadlines.");
        } else {
            ui.respond("Reminder: your upcoming task:" + "\n" + tasks.findNearest());
        }
        return false;
    }
}
