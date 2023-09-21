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
     * @param bot The bot to execute the command.
     * @return true, as the user can continue with the programme afterwards.
     */
    @Override
    public boolean execute(Duke bot) {
        bot.notifyEmpty();
        return true;
    }

    /**
     * Checks if this empty command is the same as another.
     * They are the same as long as both are empty commands.
     * @param another The object to compare with.
     * @return Whether the object is the same as this command.
     */
    @Override
    public boolean equals(Object another) {
        return another instanceof EmptyCommand;
    }
}
