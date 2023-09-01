package duke.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.TaskList;

/**
 * Represents a command of the program.
 */
public abstract class Command {
    protected Matcher matcher;

    /**
     * Constructor for a command with no arguments.
     */
    public Command() {
    }

    /**
     * Constructor for a command with arguments.
     *
     * @param s The entire command string, including the verb and arguments.
     * @param p A regex pattern representing the command format.
     * @throws CommandException If the command is of an invalid format.
     */
    public Command(String s, Pattern p) throws CommandException {
        matcher = p.matcher(s);
        if (!matcher.matches()) {
            throw new CommandException(getInvalidFormatMessage());
        }
    }

    /**
     * Parses a string into a Command. The string should start with a supported verb.
     *
     * @param s A string representing a command.
     * @return A command parsed from the string.
     * @throws CommandException If the verb is unsupported.
     */
    public static Command parse(String s) throws CommandException {
        String[] tokens = s.split(" ", 2);

        try {
            Verb verb = Verb.valueOf(tokens[0].toUpperCase());
            switch (verb) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case FIND:
                return new FindCommand(s);
            case TODO:
                return new TodoCommand(s);
            case DEADLINE:
                return new DeadlineCommand(s);
            case EVENT:
                return new EventCommand(s);
            case MARK:
                return new MarkCommand(s);
            case UNMARK:
                return new UnmarkCommand(s);
            case DELETE:
                return new DeleteCommand(s);
            default:
                return null;
            }
        } catch (IllegalArgumentException e) {
            throw new CommandException("I'm sorry, but I don't know what that means...");
        }
    }

    /**
     * Returns whether the program should exit after the command finishes executing.
     *
     * @return true If the program should exit after its execution.
     */
    public boolean shouldExit() {
        return false;
    }

    protected String getInvalidFormatMessage() {
        return null;
    }

    /**
     * Executes the command on the given task list.
     *
     * @param tasks A list of tasks to execute the command on.
     * @return The result from executing the command.
     * @throws CommandException If an error occurs while executing the command.
     */
    public abstract CommandResult run(TaskList tasks) throws CommandException;

    /**
     * Represents a command verb.
     */
    public enum Verb {
        BYE, LIST, FIND, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE
    }
}
