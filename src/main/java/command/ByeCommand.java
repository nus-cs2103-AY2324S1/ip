package command;

import storage.FileHandler;
import storage.TaskList;

import duke.Ui;

/**
 * A command to exit the application.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a `ByeCommand` object.
     */
    public ByeCommand() {
    }

    /**
     * Executes the command to exit the application and displays a farewell message.
     *
     * @param task The task list (not used in this command).
     * @param ui   The user interface.
     * @param f    The file handler for storing tasks (not used in this command).
     *
     * @return     A string representation of goodbye message.
     */
    @Override
    public String execute(TaskList task, Ui ui, FileHandler f) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return `true` because this command exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
