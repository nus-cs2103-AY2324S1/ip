package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class AddCommand extends Command {

    public AddCommand(String input) {
        super(input);
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
