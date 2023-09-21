package rua.command;

import rua.common.Storage;
import rua.common.Ui;
import rua.task.TaskList;

/**
 * Represents Command for deleting existing Task at a given index.
 */
public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
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
     * Removes the task at a given index and return the updated TaskList.
     *
     * @param tasks Current TaskList.
     * @param ui A UI to show messages to the user.
     * @param storage A Storage to save and load tasks
     * @return The TaskList after removing the Task.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        assert index <= tasks.getTasks().size() : "Task list now does not have enough tasks";
        final String removeTaskMessage = " Noted. I've removed this task:\n";
        final String removingTaskInfo = "    " + tasks.getTasks().get(index - 1) + "\n";
        TaskList newTasks = tasks.delete(index);
        final String totalNumberTaskMessage = "Now you have " + tasks.getTasks().size()
                + " tasks in the list.\n";
        ui.showMessage(removeTaskMessage);
        ui.showMessage(removingTaskInfo);
        ui.showMessage(totalNumberTaskMessage);
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

        if (!(o instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand c = (DeleteCommand) o;
        return c.index == this.index;
    }
}
