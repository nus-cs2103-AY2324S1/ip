package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {
    protected String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
