package chatbot.alain.commands;

import chatbot.alain.AlainException;
import chatbot.alain.TaskList;

/**
 * Represents an abstract command that can be executed. This serves as the base class
 * for various specific command implementations in the system.
 *
 * <p>Each command is associated with a {@code TaskList} to keep track of tasks,
 * a textual representation or description of the command itself, and a {@code Storage}
 * instance for persistence concerns.</p>
 */
public abstract class Command {
    protected TaskList list;
    protected String text;
    /**
     * Constructs a new Command with the given task list, text, and storage mechanism.
     *
     * @param list    the task list associated with this command
     * @param text    the text representation or description of this command
     */
    public Command(TaskList list, String text) {
        this.list = list;
        this.text = text;
    }
    /**
     * Processes and executes this command.
     *
     * @return a String response after processing the command
     * @throws AlainException if any error occurs during command processing
     */
    public abstract String processCommand() throws AlainException;
}
