package rua.command;

import rua.common.Ui;
import rua.common.Storage;
import rua.task.TaskList;

public interface Command {
    /**
     * Runs the corresponding command and returns the updated TaskList after the execution.
     *
     * @param tasks Current TaskList.
     * @param ui A UI to show messages to the user.
     * @param storage A Storage to save and load tasks
     * @return The updated TaskList after execution.
     */
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Returns true for ExitCommand and false for all other cases.
     *
     * @return The exit status after this execution.
     */
    boolean isExit();

    /**
     * Compares the command with other objects and return true if they are the same Deadline task.
     *
     * @param o Another object to be compared with.
     * @return A boolean indicating whether they are equal or not.
     */
    @Override
    public boolean equals(Object o);
}
