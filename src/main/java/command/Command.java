package command;

import exceptions.ThorndikeException;
import task.TaskList;

/**
 * The abstract base class for all commands that is executed by the chatbot.
 * Subclasses of this class represent specific actions that can be performed
 * by the user.
 *
 * @author Ho Khee Wei
 */
public abstract class Command {

    /**
     * Executes the specific action associated with this command.
     *
     * @param taskList The task list to operate on.
     * @return response to the user.
     * @throws ThorndikeException if there an error occurs during execution.
     */
    public abstract String execute(TaskList taskList) throws ThorndikeException;

}
