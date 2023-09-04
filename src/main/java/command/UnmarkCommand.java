package command;

import exception.DukeException;
import helper.Storage;
import helper.Ui;
import task.TaskList;

/**
 * Represents a Command that specifically marks a Task at a given Index in the TaskList
 * to be uncompleted.
 */
public class UnmarkCommand extends Command {
    /** Command the user starts with to activate the UnmarkCommand. */
    public static final String COMMAND_WORD = "unmark";

    /**
     * Constructs a UnmarkCommand with an Index.
     * @param index
     */
    public UnmarkCommand(int index) {
        super(index);
    }

    /**
     * Marks the Task at the given Index in the TaskList to be uncompleted.
     * @param list
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String message = list.unmark(this.getIndex());
        ui.print(message);
    }
}
