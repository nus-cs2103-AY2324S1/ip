package duke.parser;

import java.util.regex.Matcher;

import duke.commands.Command;
import duke.commands.FindCommand;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;

public class WordCommandParser extends CommandParser {

    public WordCommandParser(String commandName) {
        super(commandName, "^(?<command>" + commandName + ")(?<keyword>.*)?$");
    }

    @Override
    protected void validate(Matcher matcher) throws MissingDescriptionException, IncorrectCommandFormatException {
        // check if keyword is empty or more than 1 word
        String keyword = matcher.group("keyword").trim();
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new MissingDescriptionException(commandName);
        }

        if (keyword.split(" ").length > 1) {
            throw new IncorrectCommandFormatException(commandName + " keyword cannot be more than 1 word");
        }
    }

    @Override
    protected Command createCommand(Matcher matcher)
            throws MissingDescriptionException, IncorrectCommandFormatException, InvalidTimeFormatException {
        switch (commandName) {
            case "find":
                return new FindCommand(matcher.group("keyword").trim());
            default:
                throw new UnsupportedOperationException("Unimplemented method " + commandName);
        }
    }

}
