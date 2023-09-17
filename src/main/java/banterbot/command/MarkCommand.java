package banterbot.command;

import banterbot.exception.DukeException;
import banterbot.helper.Storage;
import banterbot.helper.Ui;
import banterbot.task.TaskList;

/**
 * Represents a Command that specifically marks a Task at a given Index in the TaskList
 * to be completed.
 */
public class MarkCommand extends Command {
    /** Command the user starts with to activate the MarkCommand. */
    public static final String COMMAND_WORD = "mark";

    /**
     * Constructs a MarkCommand with an Index.
     * @param index
     */
    public MarkCommand(int index) {
        super(index);
    }

    /**
     * Marks the Task at the given Index in the TaskList to be completed.
     * @param list
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        assert !list.equals(null) : "list has been initialised";
        String message = list.mark(this.getIndex());
        return message;
    }
}
