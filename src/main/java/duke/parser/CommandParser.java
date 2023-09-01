package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.Commands;
import duke.TaskListStorage;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.IncorrectCommandFormatException;

public abstract class CommandParser {
    protected String commandName;
    protected Pattern regexPattern;

    public CommandParser(String commandName, String regexPattern) {
        this.commandName = commandName;
        this.regexPattern = Pattern.compile(regexPattern);
    }

    public Commands parse(String input) throws UnknownCommandException, MissingDescriptionException, IncorrectCommandFormatException {
        Matcher matcher = this.regexPattern.matcher(input);
        if (matcher.matches()) {
            validate(matcher);
        } else {
            throw new IncorrectCommandFormatException("Command format is incorrect");
        }
        return Commands.valueOf(this.commandName);
    }

    protected abstract void validate(Matcher matcher) throws UnknownCommandException, MissingDescriptionException, IncorrectCommandFormatException;
}
