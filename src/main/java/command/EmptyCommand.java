package command;

import duke.Ui;
import storage.FileHandler;
import storage.TaskList;

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
     *
     * @return   The string representation of the empty message.
     */
    @Override
    public String execute(TaskList t, Ui ui, FileHandler f) {
        return "Please enter something!";
    }
}
