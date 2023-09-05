package main.java.command;

import main.java.tasklist.TaskList;
import main.java.ui.Ui;

/**
 * The `Command` class is an abstract class for different command implementations.
 * Subclasses of this class represent specific actions that can be executed on a task list.
 */
public abstract class Command {

    public abstract String execute(TaskList taskList, Ui ui);
}
