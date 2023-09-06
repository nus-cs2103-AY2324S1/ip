package duke.command;

import duke.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected TaskList tasks;

    /**
     * Sets the context for the command to execute on.
     * It is required to call this method before executing a command that adds a task to the list.
     *
     * @param tasks the list of tasks for the command to run on
     */
    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }

    public boolean isBye() {
        return this instanceof ByeCommand;
    }

    public abstract String[] execute();
}
