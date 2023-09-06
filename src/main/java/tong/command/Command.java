package tong.command;

import tong.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected TaskList taskList;

    /**
     * Executes the command and returns the result.
     */
    public abstract CommandResult execute();

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }
}
