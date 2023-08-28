package Parser;

import Enums.Command;
import Exception.InvalidInputException;
import Exception.MissingArgumentException;
import Exception.InvalidCommandException;
import Exception.MissingTaskArgumentException;

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
