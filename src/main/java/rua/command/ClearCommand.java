package rua.command;

import rua.task.TaskList;
import rua.common.*;

public class ClearCommand implements Command{
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
     * Clears the current TaskList and return an empty TaskList.
     *
     * @param tasks Current TaskList.
     * @param ui A UI to show messages to the user.
     * @param storage A Storage to save and load tasks
     * @return The empty TaskList after execution.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception{
        ui.showMessage("Task list cleared.\n");
        tasks = new TaskList();
        storage.save(tasks);
        return tasks;
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

        return o instanceof ClearCommand;
    }
}