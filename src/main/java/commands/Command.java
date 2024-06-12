package commands;

import tasks.TaskList;

/**
 * This class encapsulates a command given to the Duke application to run a certain set of instructions.
 */
public interface Command {

    /**
     * Execute the set of instructions based on the Command subclass
     *
     * @param tasks A TaskList object containing all tasks in the task list.
     * @return The message to be displayed upon completion.
     */
    abstract String execute(TaskList tasks);
}
