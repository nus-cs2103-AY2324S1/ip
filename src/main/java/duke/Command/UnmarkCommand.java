package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class that handles the unmark command.
 */
public class UnmarkCommand extends Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }
    /**
     * Executes the command.
     * @param ui the ui class used.
     * @param storage the storage that is used.
     * @param tasks the tasklist that is used.
     * @return String representation of the execution.
     * @throws IOException IOException
     * @throws DukeException DukeException
     */

    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        int taskIndex = Integer.parseInt(input.substring(7));
        if (taskIndex > tasks.getSize()) {
            throw new DukeException("This number is out of bounds!");
        }
        if (tasks.getTask(taskIndex - 1).getStatusIcon() == " ") {
            throw new DukeException("This task has already been marked as not done!");
        }
        tasks.markTaskAsNotDone(taskIndex - 1);
        storage.writeTasksToFile(tasks);
        return ui.printMarkTasksAsNotDone(taskIndex - 1, tasks);
    }

}
