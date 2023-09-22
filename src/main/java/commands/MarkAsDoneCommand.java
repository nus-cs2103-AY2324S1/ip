package commands;

import components.DukeException;
import components.Storage;
import components.Ui;
import tasks.TaskList;

/**
 * Represents a mark as done command.
 */
public class MarkAsDoneCommand extends Command {

    /**
     * The index of the task to be marked as done.
     */
    private Integer index;

    /**
     * Constructs a mark as done command.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkAsDoneCommand(Integer index) {
        super();
        this.index = index;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        try {
            return list.markAsDone(index, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
