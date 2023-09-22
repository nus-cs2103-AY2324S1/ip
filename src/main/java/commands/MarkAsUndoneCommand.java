package commands;

import components.Storage;
import components.Ui;
import tasks.TaskList;

/**
 * Represents a mark as undone command.
 */
public class MarkAsUndoneCommand extends Command {

    /**
     * The index of the task to be marked as undone.
     */
    private Integer index;

    /**
     * Constructs a mark as undone command.
     *
     * @param index The index of the task to be marked as undone.
     */
    public MarkAsUndoneCommand(Integer index) {
        super();
        this.index = index;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return list.markAsUndone(index, storage);
    }
}
