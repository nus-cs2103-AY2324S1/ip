package rua.command;

import rua.common.Storage;
import rua.common.Ui;
import rua.task.TaskList;

public class MarkCommand implements Command {
    private final Boolean marked;
    private final int index;

    public MarkCommand(int index, Boolean marked) {
        this.index = index;
        this.marked = marked;
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
        if (marked) {
            ui.showMessage("Nice! I've marked this task as done:\n");
        } else {
            ui.showMessage("OK, I've marked this task as not done yet:\n");
        }
        TaskList newTasks = marked ? tasks.mark(index) : tasks.unmark(index);
        ui.showMessage("    " + tasks.getTasks().get(index - 1));
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
        return c.marked.equals(this.marked) && c.index == this.index;
    }
}
