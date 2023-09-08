package command;

import storage.FileHandler;
import storage.TaskList;

import duke.Ui;

/**
 * Represents a miscellaneous command.
 */
public class MiscCommand extends Command {

    /**
     * Constructs a `MiscCommand` object.
     */
    public MiscCommand() {
    }

    /**
     * Executes the miscellaneous command, displaying a message to the user
     * indicating that the command is not understood.
     *
     * @param t  The task list (not used in this command).
     * @param ui The user interface to display the message.
     * @param f  The file handler (not used in this command).
     *
     * @return   A string representation of miscellaneous message.
     */
    @Override
    public String execute(TaskList t, Ui ui, FileHandler f) {
        return "I don't really understand what you mean.";
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
