package duke.parser;

import java.util.regex.Matcher;

import duke.commands.Command;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;

/**
 * Represents a parser that parses user input into a Command object.
 */
public class SimpleCommandParser extends CommandParser {

    /**
     * Creates a CommandParser object.
     * @param commandName The name of the command.
     */
    public SimpleCommandParser(String commandName) {
        super(commandName, "^" + commandName + "$");
    }

    @Override
    protected void validate(Matcher matcher) throws UnknownCommandException {
        // This is a no op command since no extra validation is needed
    }

    @Override
    protected Command createCommand(Matcher matcher)
            throws MissingDescriptionException, IncorrectCommandFormatException {
        switch (commandName) {
        case "list":
            return new duke.commands.ListCommand();
        default:
            throw new UnsupportedOperationException("Unimplemented method 'createCommand'");
        }
    }
}
