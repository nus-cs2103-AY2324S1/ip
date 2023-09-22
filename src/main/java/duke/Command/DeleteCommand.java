package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Class that handles the delete command.
 */
public class DeleteCommand extends Command {
    private String input;
    /**
     * Constructor for delete command.
     * @param input input passed in.
     */
    public DeleteCommand(String input) {
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
        int pos = Integer.parseInt(input.substring(7).trim());
        if (pos > tasks.getSize() || pos == 0) {
            throw new DukeException("This number is out of bounds! ");
        }
        Task element = tasks.getTask(pos - 1);
        tasks.deleteTask(pos - 1);
        storage.writeTasksToFile(tasks);
        return ui.printDeleteTasks(tasks, element);
    }
}
