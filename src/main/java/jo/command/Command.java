package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JoException;
    public abstract boolean isExit();
}
