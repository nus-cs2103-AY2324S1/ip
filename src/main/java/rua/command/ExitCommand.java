package rua.command;

import rua.common.Storage;
import rua.common.Ui;
import rua.task.TaskList;


public class ExitCommand implements Command {
    /**
     * {@inheritDoc}
     *
     * @return The exit status after this execution.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Displays goodbye message.
     *
     * @param tasks Current TaskList.
     * @param ui A UI to show messages to the user.
     * @param storage A Storage to save and load tasks
     * @return Current TaskList.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.showGoodbye();
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

        return o instanceof ExitCommand;
    }
}
