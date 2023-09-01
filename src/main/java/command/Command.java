package command;

import task.TaskList;
import ui.Ui;

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
     * @param ui       The user interface for displaying messages.
     */
    public abstract void execute(TaskList taskList, Ui ui);

}
