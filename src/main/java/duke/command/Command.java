package duke.command;

import duke.Duke;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected Duke duke;

    /**
     * Sets the context for the command to execute on.
     * It is required to call this method before executing a command.
     *
     * @param duke the Duke object for the command to run on
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    public boolean isBye() {
        return this instanceof ByeCommand;
    }

    public abstract String[] execute();
}
