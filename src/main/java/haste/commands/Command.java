package haste.commands;


import haste.data.TaskList;
import haste.exceptions.InvalidCommand;
import haste.exceptions.EmptyTaskException;
import haste.ui.Ui;

import java.time.format.DateTimeParseException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws InvalidCommand, EmptyTaskException;

}
