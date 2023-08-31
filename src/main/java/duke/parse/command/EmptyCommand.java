package duke.parse.command;

import duke.Duke;

/**
 * Represents an empty command.
 */
public class EmptyCommand implements Command {
    /**
     * Instantiates an empty command.
     */
    public EmptyCommand() {}

    /**
     * Does nothing
     * @param bot the bot to execute the command
     * @return true, as the user can continue with the programme afterwards
     */
    @Override
    public boolean execute(Duke bot) {
        return true;
    }

    /**
     * Checks if this empty command is the same as another.
     * They are the same as long as both are empty commands.
     * @param another the object to compare with
     * @return true, as this allows the user to continue with the programme
     */
    @Override
    public boolean equals(Object another) {
        return another instanceof EmptyCommand;
    }
}
