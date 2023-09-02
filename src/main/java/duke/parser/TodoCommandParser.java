package duke.parser;

import java.util.regex.Matcher;

import duke.commands.Command;
import duke.commands.TaskCommands.TodoCommand;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;

public class TodoCommandParser extends CommandParser {

    public TodoCommandParser() {
        super("todo", "^(?<command>todo)(?<description>.*)?$");
    }

    @Override
    protected void validate(Matcher matcher) throws UnknownCommandException, MissingDescriptionException,
            IncorrectCommandFormatException, InvalidIndexException {
        String description = matcher.group("description");

        if (description == null || description.trim().isEmpty()) {
            throw new MissingDescriptionException("Todo");
        }
    }

    @Override
    protected Command createCommand(Matcher matcher)
            throws MissingDescriptionException, IncorrectCommandFormatException {
        return new TodoCommand(matcher);
    }
}