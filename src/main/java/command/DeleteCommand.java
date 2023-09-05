package command;

import exception.DukeException;
import helper.Storage;
import helper.Ui;
import task.TaskList;

/**
 * Represents a Command that specifically deletes a Task in the TaskList.
 */
public class DeleteCommand extends Command {
    /** Command the user starts with to activate the DeleteCommand. */
    public static final String COMMAND_WORD = "delete";

    /**
     * Constructs a DeleteCommand with an Index.
     * @param index
     */
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
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String message = list.delete(this.getIndex());
        //      ui.print(message);
        return message;
    }
}
