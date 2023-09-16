package sillybot.commands;

import sillybot.Storage;
import sillybot.Ui;
import sillybot.tasks.TaskList;

/**
 * Represents an abstract Command class that deals with the commands.
 */
public abstract class Command {
    protected boolean isExit;

    /**
     * Creates a Command object.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Gets the isExit attribute of the Command object.
     *
     * @return The isExit attribute of the Command object.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the Command object based on the type of Command object.
     *
     * @return The String representation of the Command object.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
}
