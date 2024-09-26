package roo.commands;

import roo.TaskList;
import roo.Ui;

/**
 * Represents the commands available in the Roo application.
 */
public abstract class Command {
    public abstract String execute(Ui ui, TaskList tasks);
}
