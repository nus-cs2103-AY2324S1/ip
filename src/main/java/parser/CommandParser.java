package parser;

import enums.Command;
import exception.MissingArgumentException;
import exception.InvalidCommandException;
import exception.MissingTaskArgumentException;

/**
 * Class to help parse complex strings from user input
 */
public class CommandParser {

    /**
     * Validates if the first word is a valid command and filters it to return the arguments
     * @param input string of a single user input
     * @return command arguments
     * @throws MissingArgumentException if there are no arguments
     * @throws InvalidCommandException if the first word is an invalid command
     */
    public static String getCommandArguments(String input) throws
            MissingArgumentException,
            InvalidCommandException {

        String firstWord = getFirstWord(input);
        Command command;

        try {
            command = Command.valueOf(firstWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }

        String args = input.substring(command.getCommandStringLength());

        if (args.isBlank()) {
            switch (command) {
                case MARK:
                case UNMARK:
                case DELETE:
                    throw new MissingTaskArgumentException();
                default:
                    throw new InvalidCommandException();
            }
        } else {
            return args.substring(1);
        }
    }

    /**
     * Takes in a string of input and returns the first word
     * @param input String of user input
     * @return the first word of the string
     */
    public static String getFirstWord(String input) {
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            return input;
        }
        return input.substring(0, spaceIndex);
    }
}
