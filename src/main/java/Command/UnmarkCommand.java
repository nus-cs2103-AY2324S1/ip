package Command;

import Exception.*;
import Helper.*;
import Task.*;

/**
 * Represents a Command that specifically marks a Task at a given Index in the TaskList
 * to be uncompleted.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

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