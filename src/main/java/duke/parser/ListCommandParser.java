package duke.parser;

import java.util.regex.Matcher;

import duke.exceptions.UnknownCommandException;

public class ListCommandParser extends CommandParser {

    public ListCommandParser(String commandName, String regexPattern) {
        super(commandName, regexPattern);
    }

    @Override
    protected void validate(Matcher matcher) throws UnknownCommandException {
        // This is a no op command since no extra validation is needed
    }
}
