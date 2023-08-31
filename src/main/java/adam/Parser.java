package adam;
import adam.command.*;
import adam.exception.AdamException;
public class Parser {
    public static Command parse(String input) {
        Command command ;
        String[] tokens = input.split(" ");
        int length = tokens[0].length();
        String item = input.substring(length, input.length());
        if(tokens.length > 1) {
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
