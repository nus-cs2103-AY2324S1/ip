package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.exception.IllegalDateTimeException;
import oreo.task.Task;
import oreo.task.TaskList;
import oreo.ui.Ui;

/**
 * Implements abstract command class, parent class of specific commands
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public abstract class Command {
    /**
     * Executes the command
     *
     * @param ui Ui of the chatbot
     * @param tasks Tasklist of the chatbot
     */
    public abstract String execute(TaskList tasks);

    public abstract String executeEditMode(TaskList tasks, int index, Task oldTask)
            throws IllegalDateTimeException;

    /**
     * Checks if command is bye
     *
     * @return true if command is ByeCommand
     */
    public boolean isExit() {
        return this instanceof ByeCommand;
    }

    public boolean isEdit() {
        return this instanceof EditCommand;
    }

    public boolean isExitMode() {
        return this instanceof ExitModeCommand;
    }
}
