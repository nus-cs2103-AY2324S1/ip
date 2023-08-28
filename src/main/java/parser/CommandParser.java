package parser;

import enums.Command;
import exception.MissingArgumentException;
import exception.InvalidCommandException;
import exception.MissingTaskArgumentException;

public class CommandParser {

    public static String getCommandArguments(String input) throws
            MissingArgumentException,
            InvalidCommandException {

        String firstWord = getFirstWord(input);
        Command command;

        try {
            System.out.println(firstWord);
            command = Command.valueOf(firstWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }

        String args = input.substring(command.getCommandStringLength() + 1);

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
            return args;
        }
    }

    public static String getFirstWord(String input) {
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            return input;
        }
        return input.substring(0, spaceIndex);
    }
}
