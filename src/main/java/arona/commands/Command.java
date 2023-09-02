package arona.commands;

import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents an abstract command that can be executed in the Arona application.
 * Subclasses of Command implement specific actions to be performed when executed.
 */
public abstract class Command {
    protected TaskList taskList;
    protected Ui ui;

    /**
     * Initializes a new instance of the Command class with the specified task list and user interface.
     *
     * @param taskList The task list that the command operates on.
     * @param ui       The user interface for displaying messages and interacting with the user.
     */
    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Executes the command, performing the specific action associated with the command type.
     */
    public abstract void execute();
}
