package haste.commands;


import haste.data.TaskList;
import haste.exceptions.HasteException;
import haste.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws HasteException;

}
