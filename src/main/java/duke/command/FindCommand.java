package duke.command;

import duke.*;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String tasksFound = tasks.findTasks(this.query);
        if (tasksFound.isEmpty()) {
            ui.printWithLines(Messages.MESSAGE_NO_TASKS_FOUND);
        } else {
            ui.printWithLines(Messages.MESSAGE_TASKS_FOUND + tasksFound);
        }
    }
}
