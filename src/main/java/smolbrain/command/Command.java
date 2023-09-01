package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.exception.InvalidRangeException;
import smolbrain.task.TaskList;

public interface Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidRangeException;
    public boolean isExit();
    public void setLoading();

}
