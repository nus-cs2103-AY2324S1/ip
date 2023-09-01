package duke.commands;

import duke.DukeException;
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

    public static Command parse(String s) throws DukeException {
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

    public boolean isExit() {
        return false;
    }

    protected String getInvalidFormatMessage() {
        return null;
    }

    public abstract CommandResult run(TaskList tasks) throws CommandException;

    public enum Verb {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE
    }
}
