package parser;

import enums.Command;
import exception.InvalidCommandException;
import exception.MissingArgumentException;

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

        assert input != null : "string input is null";

        String firstWord = getFirstWord(input);

        //ensure first word is a valid command
        if (!isValidCommand(firstWord)) {
            throw new InvalidCommandException();
        }

        //ensure that arguments exist
        String args = input.substring(firstWord.length());
        if (args.isBlank()) {
            throw new MissingArgumentException();
        }

        return args.substring(1);
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

    private static boolean isValidCommand(String word) {
        try {
            Command.valueOf(word.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
