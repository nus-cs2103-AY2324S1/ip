package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents an executable command which exits the program.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    };
}
