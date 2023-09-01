package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * A class that represents the user command to unmark a task
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * A method that executes the command that user gave
     * @params tasks TaskList containing all existing Task objects
     * @params ui UI interface that is used to print messages to the terminal
     * @params storage Storage object that houses database of the program
     * @throws DukeException exception thrown if unmarking task is not able to be done, 
     * possibly due to erroneous indexing
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markTaskAsNotDone(index);
            ui.unmarkTask(index, tasks);
            storage.update(tasks);
        } catch (DukeException e) {
            throw e;
        }
    }
}
