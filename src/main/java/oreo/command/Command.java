package oreo.command;

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
    public abstract void execute(Ui ui, TaskList tasks);

    /**
     * Checks if command is bye
     *
     * @return true if command is ByeCommand
     */
    public boolean isExit() {
        return this instanceof ByeCommand;
    }
}
