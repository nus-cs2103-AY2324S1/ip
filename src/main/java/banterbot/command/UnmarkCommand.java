package banterbot.command;

import banterbot.exception.DukeException;
import banterbot.helper.Storage;
import banterbot.helper.Ui;
import banterbot.task.TaskList;

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
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        assert !list.equals(null) : "list has been initialised";
        String message = list.unmark(this.getIndex());
        return message;
    }
}
