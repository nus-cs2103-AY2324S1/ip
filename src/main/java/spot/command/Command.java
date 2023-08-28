package spot.command;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException;

    public abstract boolean isExit();

}
