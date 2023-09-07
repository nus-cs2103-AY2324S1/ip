package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;


/**
 * Represents a command to delete a task.
 * This command allows deleting a task from the task list.
 */
public class Delete extends Command {
    /**
     * Constructs a Delete command with the given input string.
     *
     * @param s The input string containing the task number.
     */
    public Delete(String s) {
        super(s);
    }

    /**
     * Executes the Delete command.
     * Deletes a task from the task list, updates the user interface,
     * and updates the storage accordingly.
     *
     * @param lst The task list containing the tasks.
     * @param io The user interface handling input and output.
     * @param storage The storage handler for storing task data.
     * @throws DukeException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList lst, UI io, Storage storage) throws DukeException {
        try {
            int index = CommonMethods.getIndex(s);
            Task t = lst.delete(index);
            storage.changeFile(lst);
            return io.delete(t);
        } catch (IOException iE) {
            throw new DukeException(iE.getMessage());
        }
    }
}
