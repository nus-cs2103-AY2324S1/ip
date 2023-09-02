package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.commands.Command;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidTimeFormatException;

/**
 * Represents a parser that parses user input into a Command object.
 */
public abstract class CommandParser {
    /**
     * The name of the command.
     */
    protected String commandName;
    /**
     * The regex pattern that the user input must match.
     */
    protected Pattern regexPattern;

    /**
     * Creates a CommandParser object.
     * 
     * @param commandName The name of the command.
     * @param regexPattern The regex pattern that the user input must match.
     */
    public CommandParser(String commandName, String regexPattern) {
        this.commandName = commandName;
        this.regexPattern = Pattern.compile(regexPattern);
    }

    /**
     * Parses the user input into a Command object.
     * 
     * @param input The user input.
     * @return The Command object.
     * @throws UnknownCommandException If the command is unknown.
     * @throws MissingDescriptionException If the description is missing.
     * @throws IncorrectCommandFormatException If the command is in the wrong format.
     * @throws InvalidIndexException If the index provided is invalid.
     * @throws InvalidTimeFormatException If the time provided is invalid.
     */
    public Command parse(String input) throws UnknownCommandException, MissingDescriptionException,
            IncorrectCommandFormatException, InvalidIndexException, InvalidTimeFormatException {
        Matcher matcher = this.regexPattern.matcher(input);
        if (matcher.matches()) {
            validate(matcher);
        } else {
            throw new IncorrectCommandFormatException("");
        }
        return createCommand(matcher);
    }

    /**
     * Validates the user input.
     * 
     * @param matcher The matcher object that contains the user input.
     * @throws UnknownCommandException If the command is unknown.
     * @throws MissingDescriptionException If the description is missing.
     * @throws IncorrectCommandFormatException If the command is in the wrong format.
     * @throws InvalidIndexException If the index provided is invalid.
     * @throws InvalidTimeFormatException If the time provided is invalid.
     */
    protected abstract void validate(Matcher matcher) throws UnknownCommandException, MissingDescriptionException,
            IncorrectCommandFormatException, InvalidIndexException, InvalidTimeFormatException;
    
    /**
     * Creates a Command object from the user input matched to its regex.
     * 
     * @param matcher The matcher object that contains the user input.
     * @return The Command object.
     * @throws MissingDescriptionException If the description is missing.
     * @throws IncorrectCommandFormatException If the command is in the wrong format.
     * @throws InvalidTimeFormatException If the time provided is invalid.
     */

    protected abstract Command createCommand(Matcher matcher)
            throws MissingDescriptionException, IncorrectCommandFormatException, InvalidTimeFormatException;
}