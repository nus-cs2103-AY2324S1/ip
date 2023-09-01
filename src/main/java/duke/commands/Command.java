package duke.commands;

import duke.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
    protected Matcher matcher;

    public Command() {
    }

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
            }
        } catch (IllegalArgumentException e) {
            throw new CommandException("I'm sorry, but I don't know what that means...");
        }
        return null;
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

    public enum Verb {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE
    }
}
