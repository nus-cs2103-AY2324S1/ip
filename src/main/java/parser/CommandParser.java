package parser;

import enums.Command;
import exception.InvalidInputException;
import exception.MissingArgumentException;
import exception.InvalidCommandException;
import exception.MissingTaskArgumentException;

public class CommandParser {
    public static String getCommandArguments(String input, Command command) throws
            InvalidInputException,
            MissingArgumentException,
            InvalidCommandException {

        int cmdLength = command.getCommandStringLength();
        String args = input.substring(cmdLength);

        if (args.isBlank()) {
            switch (command) {
                case MARK:
                case UNMARK:
                case DELETE:
                    throw new MissingTaskArgumentException();
                default:
                    throw new InvalidCommandException();
            }
        } else if (args.startsWith(" ")){
            return args.substring(1);
        } else {
            throw new InvalidInputException();
        }
    }
}
