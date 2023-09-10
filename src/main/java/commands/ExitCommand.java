package commands;

import exceptions.DukeException;
import io.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command that exits the program.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand.
     */
    public ExitCommand() {
        super("bye");
    }

    /**
     * Executes the ExitCommand, exiting the program.
     * @param taskList The TaskList object that stores the list of tasks
     * @param ui The Ui object that handles the user interface
     * @param storage The Storage object that handles the saving and loading of tasks
     * @throws DukeException If the command is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
        setExit();
    }
}
