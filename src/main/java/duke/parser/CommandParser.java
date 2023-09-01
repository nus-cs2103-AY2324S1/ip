package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.commands.Command;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidTimeFormatException;

public abstract class CommandParser {
    protected String commandName;
    protected Pattern regexPattern;

    public CommandParser(String commandName, String regexPattern) {
        this.commandName = commandName;
        this.regexPattern = Pattern.compile(regexPattern);
    }

    public Command parse(String input) throws UnknownCommandException, MissingDescriptionException, IncorrectCommandFormatException, InvalidIndexException, InvalidTimeFormatException {
        Matcher matcher = this.regexPattern.matcher(input);
        if (matcher.matches()) {
            validate(matcher);
        } else {
            throw new IncorrectCommandFormatException("");
        }
        return createCommand(matcher);
    }

    protected abstract void validate(Matcher matcher) throws UnknownCommandException, MissingDescriptionException, IncorrectCommandFormatException, InvalidIndexException;
    protected abstract Command createCommand(Matcher matcher) throws MissingDescriptionException, IncorrectCommandFormatException, InvalidTimeFormatException; 
}