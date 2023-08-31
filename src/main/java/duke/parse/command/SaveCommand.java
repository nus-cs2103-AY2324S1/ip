package duke.parse.command;

import duke.Duke;

/**
 * Represents a command to save data to disk.
 */
public class SaveCommand implements Command {
    /**
     * Instantiates a save command.
     */
    public SaveCommand() {}

    /**
     * Command the bot to save its current data to disk.
     * @param bot the bot to execute the command
     * @return true, as it allows user to continue with the programme
     */
    @Override
    public boolean execute(Duke bot) {
        bot.saveData();
        return true;
    }

    /**
     * Checks whether this save command is the same as another, for testing purposes.
     * It is the same as long as the other is also a save command.
     * @param another the object to compare with
     * @return whether this save command is the same as another
     */
    @Override
    public boolean equals(Object another) {
        return another instanceof SaveCommand;
    }
}
