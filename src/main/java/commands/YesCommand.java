package commands;

import components.DukeException;
import components.Storage;
import components.Ui;
import tasks.TaskList;

/**
 * Represents a yes command.
 */
public class YesCommand extends Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if (Command.tempTask == null) {
            throw new DukeException.InvalidCommandException();
        }
        String result = list.addTask(Command.tempTask, storage);
        Command.tempTask = null;
        return result;
    }
}
