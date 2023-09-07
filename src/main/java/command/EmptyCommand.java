package command;

import storage.FileHandler;
import storage.TaskList;

import ui.Ui;

/**
 * An empty command.
 */
public class EmptyCommand extends Command {

    /**
     * Constructs an `EmptyCommand` object.
     */
    public EmptyCommand() {
    }

    /**
     * Executes the empty command and displays a message to ask user for input.
     *
     * @param t  The task list (not used in this command).
     * @param ui The user interface.
     * @param f  The file handler (not used in this command).
     */
    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        ui.empty();
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
