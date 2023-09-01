package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * A class that represents the user command to list all existing tasks
 */
public class ListCommand extends Command {
    /**
     * A method that executes the command that user gave
     * @params tasks TaskList containing all existing Task objects
     * @params ui UI interface that is used to print messages to the terminal
     * @params storage Storage object that houses database of the program
     * @throws DukeException exception thrown if database cannot be interpreted correctly
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.list(tasks);
    }
}
