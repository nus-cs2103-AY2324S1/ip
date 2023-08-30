package Command;

import Exception.*;
import Helper.*;
import Task.*;

/**
 * Represents a Command that specifically deletes a Task in the TaskList.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Deletes the Task at the given Index in the TaskList.
     * Throws a DukeException if Index is incorrect.
     * @param list
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String message = list.delete(this.getIndex());
        ui.print(message);
    }
}
