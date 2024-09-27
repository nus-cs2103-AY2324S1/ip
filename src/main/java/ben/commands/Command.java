package ben.commands;

import ben.exceptions.EmptyDescriptionException;
import ben.exceptions.InvalidCommandException;
import ben.storage.TaskList;
import ben.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to the chatbot.
 */
public abstract class Command {
    /**
     * Checks whether command causes the chatbot to exit.
     *
     * @return false or true.
     */
    public abstract boolean isExit() ;

    /**
     * Executes the task.
     *
     * @param tasks The taskList
     * @param ui The UI handling user interaction
     * @throws InvalidCommandException Error for invalid commands.
     * @throws EmptyDescriptionException Error for empty description commands.
     * @throws DateTimeParseException Error for when DateTime cannot be parsed.
     */
    public abstract String execute(TaskList tasks, Ui ui) throws InvalidCommandException, EmptyDescriptionException, DateTimeParseException;
}
