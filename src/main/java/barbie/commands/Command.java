package barbie.commands;

import java.util.ArrayList;

import barbie.types.Task;

/**
 * Represents all the command abstractly.
 * All commands will inherit from this class.
 */
public abstract class Command {
    protected boolean isExit;

    /**
     * Runs the command of this instance of Command.
     * (eg ExitCommand will contain the logic of exiting the application)
     * @param taskList current list of tasks
     * @return barbie's output in a String format
     */
    public abstract String run(ArrayList<Task> taskList);
}
