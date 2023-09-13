package adam;

import adam.command.Command;
import adam.command.AddCommand;
import adam.command.FindCommand;
import adam.command.SingleCommand;
import adam.command.EditCommand;
import adam.exception.AdamException;

/**
 * This class is used to parse the user input and make sense of it.
 */
public class Parser {

    /**
     * Returns a Command object that specifies what kind of command the user inputs.
     *
     * @param input User input.
     * @return Command object.
     */
    public static Command parse(String input) {
        Command command ;
        String[] tokens = input.split(" ");
        int length = tokens[0].length();
        String item = input.substring(length, input.length());
        if (tokens.length > 1) {
            item = input.substring(length + 1, input.length());
        }
        switch (tokens[0]) {
        case "bye":
        case "list":
            command = new SingleCommand(tokens, tokens[0]);
            break;
        case "todo":
        case "deadline":
        case "event":
            command = new AddCommand(tokens, item, tokens[0]);
            break;
        case "mark":
        case "unmark":
        case "delete":
            command = new EditCommand(tokens, tokens[0]);
            break;
        case "find":
            command = new FindCommand(tokens, item, tokens[0]);
            break;
        default:
                throw new AdamException();
        }
        return command;
    }
}
