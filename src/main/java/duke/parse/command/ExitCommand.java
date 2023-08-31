package duke.parse.command;

import duke.Duke;

/**
 * Represents an exit command.
 */
public class ExitCommand implements Command {
    /**
     * Instantiates an exit command.
     */
    public ExitCommand() {
    }

    /**
     * Commands the bot to exit the interaction.
     * @param bot the bot to execute the command
     * @return false, as this means the user will be exited out of the programme
     */
    @Override
    public boolean execute(Duke bot) {
        bot.exit();
        return false;
    }

    /**
     * Checks whether this exit command is the same as another, for testing purposes.
     * They are the same as long as the other object is also an exit command.
     * @param another the object to compare with
     * @return whether this exit command is the same as another
     */
    @Override
    public boolean equals(Object another) {
        return another instanceof ExitCommand;
    }
}
