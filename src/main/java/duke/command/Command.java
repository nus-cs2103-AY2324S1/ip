package duke.command;

import duke.task.TaskList;

/**
 * Command encapsulates information for execution to be done
 * according to user's input
 *
 * @author Lian Zhi Xuan
 */
public abstract class Command {

    /**
     * Executes the instruction in command
     *
     * @param list
     */
    public abstract void execute(TaskList list);

}
