package dukeapp.commands;

import dukeapp.TaskList;
import dukeapp.Ui;

/**
 * Represents a generic command interface with a run method defining the
 * behaviour of the command.
 */
public interface Command {
    /**
     * Executes the command given an input.
     *
     * @param input The command to be run.
     */
    void run(String input, TaskList taskList, Ui ui);
}
