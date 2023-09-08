package commands;
import components.DukeException;
import components.Storage;
import components.Ui;
import tasks.TaskList;

public abstract class Command {

    public Command() {

    }

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
