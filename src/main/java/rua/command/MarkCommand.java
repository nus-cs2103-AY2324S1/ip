package rua.command;

import rua.common.Storage;
import rua.common.Ui;
import rua.task.TaskList;

/**
 * Represents Command for marking and unmarking a Task.
 */
public class MarkCommand implements Command {
    private final Boolean isMarked;
    private final int index;

    private static final String MESSAGE_MARK = "Nice! I've marked this task as done:\n";
    private static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:\n";

    /**
     * Constructs the MarkCommand.
     *
     * @param index The index to be marked or unmarked.
     * @param isMarked A boolean to indicate whether to mark or unmark.
     */
    public MarkCommand(int index, Boolean isMarked) {
        this.index = index;
        this.isMarked = isMarked;
    }

    /**
     * {@inheritDoc}
     *
     * @return The exit status after this execution.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Sets a Task at given index to be marked/unmarked and returns the updated TaskList.
     *
     * @param tasks Current TaskList.
     * @param ui A UI to show messages to the user.
     * @param storage A Storage to save and load tasks
     * @return The updated TaskList after marking/unmarking the Task.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        assert index <= tasks.getTasks().size() : "Task list now does not have enough tasks";
        String message = isMarked ? MESSAGE_MARK : MESSAGE_UNMARK;
        ui.showMessage(message);
        TaskList newTasks = isMarked ? tasks.mark(index) : tasks.unmark(index);
        ui.showMessage("    " + tasks.getTasks().get(index - 1));
        storage.save(tasks);
        return newTasks;
    }

    /**
     * {@inheritDoc}
     *
     * @param o Another object to be compared with.
     * @return A boolean indicating whether they are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof MarkCommand)) {
            return false;
        }

        MarkCommand c = (MarkCommand) o;
        return c.isMarked.equals(this.isMarked) && c.index == this.index;
    }
}
