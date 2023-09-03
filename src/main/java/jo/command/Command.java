package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;

public abstract class Command {

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException;
    abstract public boolean isExit();
}
