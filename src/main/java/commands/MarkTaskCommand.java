package commands;

import duke.DukeException;
import oop.Storage;
import oop.TaskList;
import oop.Ui;

/**
 * The command that marks a task as done upon execution.
 */
public class MarkTaskCommand implements Command {
    /** The index of the task to be marked as done within the TaskList. */
    private int id;

    /**
     * Constructs a command that marks a task as done when executed.
     * @param id The index of the task to be marked as done.
     */
    public MarkTaskCommand(int id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     * Executes the command to mark the task with the encapsulated id as done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (id >= tasks.getSize()) {
            throw new DukeException("Index out of bounds. There are "
                    + tasks.getSize()
                    + " tasks currently.");
        }
        tasks.markTask(id);
        assert tasks.getTask(id).isDone();
        return ui.getTaskMarkedMessage(tasks.getTask(id));
    }
}
