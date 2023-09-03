package commands;

import exceptions.JamesBondException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void runCommand(TaskList taskList, Ui ui, Storage storage);

}