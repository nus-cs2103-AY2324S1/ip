package haste.commands;


import haste.data.TaskList;
import haste.exceptions.HasteException;
import haste.ui.Ui;

/**
 * Represents a command that contains the steps to be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks TaskList containing the tasks.
     * @param ui Ui that handles interactions.
     * @throws HasteException for invalid command
     */
    public abstract void execute(TaskList tasks, Ui ui) throws HasteException;

}
