package duke.parser;

import java.util.regex.Matcher;

import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;

public class IndexCommandParser extends CommandParser {

    public IndexCommandParser(String commandName) {
        super(commandName, "^(delete|mark|unmark) (?<index>-?\\d+)$");
    }

    @Override
    protected void validate(Matcher matcher)
            throws UnknownCommandException, MissingDescriptionException, IncorrectCommandFormatException, InvalidIndexException {
        int index = 0;
        try {
            index = Integer.parseInt(matcher.group("index")) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(commandName + " index must be a number");
        }
        if (index < 0) {
            throw new InvalidIndexException(commandName + " index cannot be less than 0");
        }
    }

    @Override
    protected Command createCommand(Matcher matcher)
            throws MissingDescriptionException, IncorrectCommandFormatException {
        switch (commandName) {
        case "delete":
            return new DeleteCommand(matcher);
        case "mark":
            return new MarkCommand(matcher);
        case "unmark":
            return new UnmarkCommand(matcher);
        default:
            throw new UnsupportedOperationException("Unimplemented method " + commandName);
        }
    }

}