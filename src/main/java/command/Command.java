package command;


import java.time.format.DateTimeFormatter;

import duke.Duke;
import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;

/**
 * The `Command` class is an abstract class representing a command in the application.
 * All specific command classes should inherit from this class and implement the `execute` method.
 */
public abstract class Command {
    /**
     * The raw command entered by the user.
     */
    private final String rawCommand;

    /**
     * Constructs a new `Command` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */

    public Command(String rawCommand) {
        this.rawCommand = rawCommand;
    }

    /**
     * Executes the command. Subclasses must implement this method to define their behavior.
     *
     * @param taskList The task list to perform the command on.
     */
    public abstract void execute(TaskList taskList);

    public DateTimeFormatter getDateTimeformatter() {
        return Duke.getDateTimeFormatter();
    }

    /**
     * Gets the raw command entered by the user.
     *
     * @return The raw command string.
     */
    public String getRawCommand() {
        return this.rawCommand;
    }

    /**
     * Checks if the command is a "bye" command.
     *
     * @return `true` if it's a "bye" command, `false` otherwise.
     */
    public boolean isByeCommand() {
        return CommandWord.commandWordToValueMap(Parser.getArgs(rawCommand)[0]).equals(CommandWord.BYE);
    }
}
