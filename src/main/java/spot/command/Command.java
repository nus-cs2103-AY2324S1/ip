package spot.command;

import spot.exception.SpotException;
import spot.Storage;
import spot.TaskList;
import spot.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException;

    public abstract boolean isExit();

}
