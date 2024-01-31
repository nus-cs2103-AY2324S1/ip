package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * A class that represents the user command to mark a task.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * A method that executes the command that user gave.
     * @params tasks TaskList containing all existing Task objects.
     * @params ui UI interface that is used to print messages to the terminal.
     * @params storage Storage object that houses database of the program.
     * @throws DukeException exception thrown if marking task is not able to be done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markTaskAsDone(index);
            String out = ui.markTask(index, tasks);
            storage.update(tasks);
            return out;
        } catch (DukeException e) {
            throw e;
        }
    }
}
