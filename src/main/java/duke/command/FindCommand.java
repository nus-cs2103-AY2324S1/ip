package duke.command;

import duke.DukeException;
import duke.Messages;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command of querying the task list.
 */
public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String tasksFound = tasks.findTasks(this.query);
        if (tasksFound.isEmpty()) {
            return Messages.MESSAGE_NO_TASKS_FOUND;
        } else {
            return Messages.MESSAGE_TASKS_FOUND + tasksFound;
        }
    }
}
